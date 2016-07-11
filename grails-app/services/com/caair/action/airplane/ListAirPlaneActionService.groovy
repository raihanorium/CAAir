package com.caair.action.airplane

import com.caair.BaseService
import com.caair.ActionServiceIntf
import com.caair.entity.AirPlane
import com.caair.service.AirPlaneService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

/**
 *  Show list of all/filtered AirPlane in grid
 */
class ListAirPlaneActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass());

    private static final String LIST = "list"

    @Autowired
    AirPlaneService airPlaneService

    /**
     * While getting list of AirPlane object this method is called first.
     * No pre conditions required for searching AirPlane
     *
     * @param params - Request parameters
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePreCondition(Map params) {
        return params
    }

    /**
     * This method will be called if executePreCondition method has been complied.
     * Pull all AirPlane list from database
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map execute(Map result) {
        try {
            List<AirPlane> lst = airPlaneService.list()
            result.put(LIST, lst)
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method will call after getting list of AirPlane objects.
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        // @todo if any functionality needed after getting list of AirPlane object, add your logic here.
        return result;
    }

    /**
     * This method will call after executing executePostCondition method if the method has been complied.
     * Since there is no success message return the same map
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        // @todo if any functionality needed after getting list of AirPlane object, add your logic here.
        return result
    }

    /**
     * This method will call if executePreCondition or execute or executePostCondition method has not been complied.
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        // @todo if any functionality needed for handling failure scenario, add your logic here.
        return result
    }
}
