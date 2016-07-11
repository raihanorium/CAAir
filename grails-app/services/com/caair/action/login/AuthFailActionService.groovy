package com.caair.action.login

import com.caair.ActionServiceIntf
import com.caair.BaseService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.apache.log4j.Logger
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import javax.servlet.http.HttpSession

/**
 * Auth fail
 * For details please go through use case named 'AuthFailActionService'
 */
class AuthFailActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String SESSION = "session"
    private static final String FAILED_TO_LOGIN = "Failed to login. Try again"

    /**
     * 1. return same map as received
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map executePreCondition(Map parameters) {
        return parameters
    }

    /**
     * 1. get error message if exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map execute(Map previousResult) {
        previousResult.put(IS_ERROR, Boolean.TRUE)
        try {

            HttpSession session = (HttpSession) previousResult.get(SESSION)
            String message = ""
            def exception = session[AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
            if (exception) {
                message = SpringSecurityUtils.securityConfig.errors.login.fail
                previousResult.put(MESSAGE, message)
                return previousResult
            }
            previousResult.put(IS_ERROR, Boolean.FALSE)
            return previousResult
        } catch (Exception ex) {
            log.error(ex.getMessage())
            previousResult.put(IS_ERROR, Boolean.TRUE)
            previousResult.put(MESSAGE, FAILED_TO_LOGIN)
            return previousResult
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map executePostCondition(Map previousResult) {
        return previousResult
    }

    /**
     * 1. put success message if required
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map buildSuccessResultForUI(Map executeResult) {
        return executeResult
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map buildFailureResultForUI(Map executeResult) {
        return executeResult
    }
}
