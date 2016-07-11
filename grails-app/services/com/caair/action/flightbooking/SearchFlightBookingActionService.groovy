package com.caair.action.flightbooking

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.AppUser
import com.caair.entity.PaymentInfo
import com.caair.service.FlightBookingService
import com.caair.service.PaymentInfoService
import grails.transaction.Transactional
import org.apache.log4j.Logger

class SearchFlightBookingActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())
    private String FLIGHT_BOOKING_SEARCH_WITH_SUCCESS = "Search Result Found"
    FlightBookingService flightBookingService

    /**
     * 1. check Validation
     * 2. check required input for search
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            // check Validation
            String errMsg = checkValidation(params, null)
            if (errMsg) {
                return super.setError(params, errMsg)
            }
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @org.springframework.transaction.annotation.Transactional
    public Map execute(Map result) {
        try {
            // Search in db with provided valid input
            List list = flightBookingService.getSearchResult(result)
            result.put("list", list)
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
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
     * 1. put success message
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return super.setSuccess(result, FLIGHT_BOOKING_SEARCH_WITH_SUCCESS)
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }

    /**
     * check input required validation
     * @param paymentInfo -object of PaymentInfo
     * @param user - an object of AppUser
     * @return -a string containing null value or error message depending on duplicate check
     */
    private String checkValidation(Map params, AppUser user) {
        String errMsg
        if ((!params.departureAirport) || (!params.arrivalAirport) || (!params.departureTime)) {
            return ERROR_FOR_INVALID_INPUT
        }
        return null
    }


}

