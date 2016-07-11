package com.caair.action.airplane

import com.caair.BaseService
import com.caair.ActionServiceIntf
import com.caair.service.AirPlaneService
import grails.transaction.Transactional
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired

/**
 *  Get AirPlane object from DB
 */
class EditAirPlaneActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    @Autowired
    AirPlaneService airPlaneService

    /**
     * While getting an AirPlane object this method is called first.
     * In this method check all preconditions before getting an old AirPlane object (i.e required params, existence of AirPlane object)
     * Put this method in transactional(readOnly) block if only DB read operation is needed or transactional(read/write) if both read/write is needed.
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            // @todo check required params and return corresponding message if not comply i.e return super.setError(params, errMsg)
            // @todo read AirPlane object airPlaneService.read(id)
            // @todo check server side validation process (ie AirPlane object existence)
            // @todo put AirPlane object in result for future reference.
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method will be called if executePreCondition method has been complied.
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {
            // @todo get the AirPlane object from result
            // @todo check other logic if you might need before get AirPlane
            return result
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * This method will call after deleting an AirPlane object.
     * Put this method in transactional(readOnly) block if only DB read operation is needed or transactional(read/write) if both read/write is needed.
     *
     * @param result - Previous result of executing the Action
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        // @todo if any functionality needed after get AirPlane object, add your logic here.
        return result
    }

    /**
     * This method will call after executing executePostCondition method if the method has been complied.
     * Since there is no success message return the same map
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        // @todo if any functionality needed after getting Flight object, add your logic here.
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
