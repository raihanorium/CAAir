package com.caair.action.customer

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Customer
import com.caair.service.CustomerService
import grails.transaction.Transactional
import org.apache.log4j.Logger

/**
 * Customer edit view
 */
class EditCustomerActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String CUSTOMER = "customer"
    private static final String NOT_FOUND = "customer could not be found"

    CustomerService customerService

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
            long customerId = Long.parseLong(result.id.toString())
            Customer customer = customerService.read(customerId)
            String customerExistMsg = isCustomerExist(customer)
            if (customerExistMsg != null) {
                return super.setError(result, NOT_FOUND)
            }
            result.put(CUSTOMER, customer)
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

    /**
     * check customer existence
     * @param customer - customer
     * @return - error message or null depending on validation
     */
    private String isCustomerExist(Customer customer) {
        if (!customer) {
            return NOT_FOUND
        }
        return null
    }
}