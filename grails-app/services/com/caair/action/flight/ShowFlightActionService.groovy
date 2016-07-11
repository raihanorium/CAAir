package com.caair.action.flight

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Flight
import com.caair.model.ShowFlightActionServiceModel
import com.caair.service.FlightService
import com.caair.service.ShowFlightActionServiceModelService
import grails.transaction.Transactional
import org.apache.log4j.Logger

/**
 * show all flight
 * For details please go through use case named 'ShowFlightActionService'
 */
class ShowFlightActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String FLIGHT_LIST = "flightList"

    ShowFlightActionServiceModelService showFlightActionServiceModelService

    /**
     * return same map as received
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map executePreCondition(Map parameters) {
        return parameters
    }

    /**
     * 1. get list of flight info for view
     * This method is in transactional readOnly block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    Map execute(Map previousResult) {
        try {
            List<ShowFlightActionServiceModel> flightList = showFlightActionServiceModelService.list()
            previousResult.put(FLIGHT_LIST, flightList)
            return previousResult
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException()
        }
    }

    /**
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map executePostCondition(Map previousResult) {
        return previousResult
    }

    /**
     * 1. put success message if required
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map buildSuccessResultForUI(Map executeResult) {
        return executeResult
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    Map buildFailureResultForUI(Map executeResult) {
        return executeResult
    }
}
