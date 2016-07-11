package com.caair.action.appuser

import com.caair.BaseService
import com.caair.service.AirPlaneService
import com.caair.ActionServiceIntf
import com.caair.entity.AirPlane
import grails.transaction.Transactional

class ShowAppUserActionService extends BaseService implements ActionServiceIntf {


    private static final String AIR_PLANE = "airPlane"
    private static final String NOT_FOUND = "Air Plane information not found"

    AirPlaneService airPlaneService

    /**
     * 1. check required parameter
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePreCondition(Map params) {
        // check required parameter
        if (!params.id) {
            return super.setError(params, ERROR_FOR_INVALID_INPUT)
        }
        return params
    }

    /**
     * 1. read ElAssignment object from service
     * 2. check ElAssignment object existence
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */

    @Transactional
    public Map execute(Map result) {
        try {
            long airPlaneId = Long.parseLong(result.id.toString())
            AirPlane airPlane = airPlaneService.read(airPlaneId)
            String airPlaneExistMsg = isAirPlaneExist(airPlane)
            if (airPlaneExistMsg != null) {
                return super.setError(result, NOT_FOUND)
            }
            result.put(AIR_PLANE, airPlane)
            return result
        } catch (Exception e) {
            log.error(e.getMessage())
            throw new RuntimeException(e)
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        return result
    }

    /**
     * For show there is no success message
     * since the input-parameter already have "isError:false", just return the same map
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return result
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param obj -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }


    private String isAirPlaneExist(AirPlane airPlane) {
        if (!airPlane) {
            return NOT_FOUND
        }
        return null
    }
}
