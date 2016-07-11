package com.caair.action.appuser

import com.caair.BaseService
import com.caair.ActionServiceIntf
import grails.transaction.Transactional
import org.apache.log4j.Logger

/**
 * Register an user in the system
 */
class RegisterAppUserActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    /**
     * 1. get params from UI and check required parameters
     * 2. check validation
     * @param parameters - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    Map executePreCondition(Map parameters) {
        try {
            // check required parameters
            // check validation (uniqueness etc.)
            return parameters
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * 1. build object
     * 2. save object (register in system)
     * This method is in transactional block and will roll back in case of any gopd.exception
     *
     * @param previousResult - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    Map execute(Map previousResult) {
        try {
            // build object
            // register user
            return previousResult
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * Execute post conditions (if any)
     *
     * @param previousResult - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    Map executePostCondition(Map previousResult) {
        try {
            // execute post condition
            return previousResult
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * 1. put success message
     *
     * @param executeResult - map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map buildSuccessResultForUI(Map executeResult) {
        // put success message in map
        return executeResult
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param executeResult - map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map buildFailureResultForUI(Map executeResult) {
        return executeResult
    }
}
