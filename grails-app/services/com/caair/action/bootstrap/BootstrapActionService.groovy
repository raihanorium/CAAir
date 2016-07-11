package com.caair.action.bootstrap

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.service.*
import grails.transaction.Transactional
import org.apache.log4j.Logger

/**
 * load default data
 * For details please go through use case named 'BootstrapActionService'
 */
class BootstrapActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String IS_DEFAULT_DATA = "isDefaultData"

    AirPlaneService airPlaneService
    AppUserRoleService appUserRoleService
    AppUserService appUserService
    CustomerService customerService
    FlightService flightService
    OauthIdService oauthIdService
    PaymentInfoService paymentInfoService
    RequestMapService requestMapService
    RoleService roleService
    TicketInfoService ticketInfoService
    ShowFlightActionServiceModelService showFlightActionServiceModelService
    SearchFlightActionServiceModelService searchFlightActionServiceModelService

    /**
     * 1. check is default data is already loaded
     * This method is in transactional readOnly block and will roll back in case of any exception
     *
     * @param params - parameters from caller
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    @Transactional(readOnly = true)
    Map executePreCondition(Map parameters) {
        try {
            // check is default data is already loaded
            boolean isDefaultData = checkIfDefaultDataLoaded()
            parameters.put(IS_DEFAULT_DATA, isDefaultData)
            return parameters
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * 1. load default data
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Override
    @Transactional
    Map execute(Map previousResult) {
        try {
            boolean isDefaultData = (boolean) previousResult.get(IS_DEFAULT_DATA)
            if (isDefaultData) {
                // load default data
                loadDefaultData()
                //load default Schema
                loadDefaultSchema()
            }
            return previousResult
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
    @Override
    Map executePostCondition(Map previousResult) {
        return previousResult
    }

    /**
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

    /**
     * check if data is already loaded
     * @return
     */
    private boolean checkIfDefaultDataLoaded() {
        int count = customerService.count()
        if (count > 0) {
            return false
        }
        return true
    }

    /**
     * load default data
     */
    private void loadDefaultData() {
        appUserService.createDefaultData()
        oauthIdService.createDefaultData()
        roleService.createDefaultData()
        appUserRoleService.createDefaultData()
        airPlaneService.createDefaultData()
        flightService.createDefaultData()
        customerService.createDefaultData()
        paymentInfoService.createDefaultData()
        ticketInfoService.createDefaultData()
        requestMapService.createDefaultData()
    }

    /**
     * load default schema
     */
    private void loadDefaultSchema() {
        showFlightActionServiceModelService.createDefaultSchema()
        searchFlightActionServiceModelService.createDefaultSchema()
    }
}
