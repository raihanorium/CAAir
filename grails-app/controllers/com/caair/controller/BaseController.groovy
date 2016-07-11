package com.caair.controller

import com.caair.ActionServiceIntf
import grails.converters.JSON
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsParameterMap

/**
 * Base Controller extended by all controller
 *
 */
class BaseController {

    private static final String CLASS_NAME = 'com.athena.mis'
    private static final String BREAK = '<br/>'
    private static final String STACK_TRACE = 'stackTrace'
    private static final String MESSAGE = 'message'
    private static final String IS_ERROR = 'isError'
    private static final String EMPTY_SPACE = ''

    // this method will call when unhandled Exception occurred
    def unhandledError(RuntimeException ex) {
        Map exProperties = ex.getProperties();
        response.setStatus(705);
        List<StackTraceElement> lstStackTraceElement = (List<StackTraceElement>) exProperties.get(STACK_TRACE)
        String classSignature = EMPTY_SPACE
        lstStackTraceElement.each {
            if (it.className.startsWith(CLASS_NAME) && (it.lineNumber > 0)) {
                classSignature = classSignature + it.toString() + BREAK
            }
        }
        Map result = [message: exProperties.get(MESSAGE), classSignature: classSignature]
        String out = result as JSON
        render(out)
    }

    /**
     * Execute the designated action, based on returned result, renders the view with result model
     *
     * @param action -Action being executed
     * @param params -Request parameters
     * @param view -view name to render
     *
     * @see this.getServiceResponse
     */
    protected void renderView(ActionServiceIntf action, GrailsParameterMap params, String view) {
        Map result = this.getServiceResponse(action, params)
        render(view: view, model: result)
    }

    /**
     * Execute the designated action, based on returned result, renders the result as JSON
     *
     * @param action Action being executed
     * @param params Request parameters
     *
     * @see this.getServiceResponse
     */
    protected void renderAsJSON(ActionServiceIntf action, GrailsParameterMap params) {
        Map result = this.getServiceResponse(action, params)
        String output = result as JSON
        render output
    }

    /*
    * Renders byte stream based on mime type
    * */

    protected void renderByteStream(byte[] bytes, String mime, String name) {
        response.contentType = mime
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        OutputStream out = response.getOutputStream();
        out.setBytes(bytes);
        out.flush();
        out.close();

    }

    /**
     * Runs pre-condition of an action, if pre-condition failed, it constitutes a failure result
     * and returns. If pre-condition met, it executes the action, if action succeeded, it constitutes
     * the success result, builds failure result otherwise.
     *
     * Finally returns the result to the caller
     *
     * Please note that every action MUST return a LinkedHashMap containing a key named isError
     * to indicate whether error occurred during the execution teh action;  a FALSE value represents
     * there was no errors and the operation was successful
     *
     * @param action Action to be examined for pre-condition and to execute later
     * @param params Request parameter
     * @return result; based on pre-condition and execution of the action
     */
    protected Map getServiceResponse(ActionServiceIntf action, GrailsParameterMap params) {

        // Set default value of Error flag, turn this on only when Error occurs
        params.put(IS_ERROR, Boolean.FALSE)

        // check to see if pre-conditions are met
        Map preResult = action.executePreCondition(params);
        Boolean isError = (Boolean) preResult.get(IS_ERROR);
        if (isError.booleanValue() == true) {
            return action.buildFailureResultForUI(preResult);
        }

        // executes the action
        Map executeResult = action.execute(preResult);
        isError = (Boolean) executeResult.get(IS_ERROR);
        if (isError.booleanValue() == true) {
            return action.buildFailureResultForUI(preResult);
        }
        // executes the post actions
        Map postResult = action.executePostCondition(executeResult);
        isError = (Boolean) postResult.get(IS_ERROR);
        if (isError.booleanValue() == true) {
            return action.buildFailureResultForUI(postResult);
        }



        return action.buildSuccessResultForUI(postResult);
    }
}
