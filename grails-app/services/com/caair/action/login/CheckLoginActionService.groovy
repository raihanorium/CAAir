package com.caair.action.login

import com.caair.ActionServiceIntf
import com.caair.BaseService
import grails.transaction.Transactional
import org.apache.log4j.Logger

@Transactional
class CheckLoginActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    /**
     * 1. check required parameters
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map executePreCondition(Map parameters) {
        try {
            if (!parameters.j_username || !parameters.j_password) {
                return super.setError(parameters, ERROR_FOR_INVALID_INPUT)
            }
            return parameters
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * 1. receive customer object from executePreCondition method
     * 2. create new customer
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    Map execute(Map previousResult) {
        return previousResult
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
