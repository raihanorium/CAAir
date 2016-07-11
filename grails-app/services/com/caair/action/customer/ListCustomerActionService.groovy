package com.caair.action.customer

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Customer
import com.caair.service.CustomerService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

/**
 * list customer
 */
class ListCustomerActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String CUSTOMER_LIST = "customers"
    private static final String CUSTOMER_COUNT = "customerInstanceCountList"

    CustomerService customerService
    /**
     * No pre conditions required for searching customer domains
     *
     * @param params - Request parameters
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePreCondition(Map params) {
        return params
    }

    /**
     * 1. pull all customer list from database
     * 2. pull all customer count from database
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map execute(Map result) {
        try {
            List<Customer> customers = customerService.list();
            int customerCount = customerService.count();
            result.put(CUSTOMER_LIST, customers)
            result.put(CUSTOMER_COUNT, customerCount)
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
        return result;
    }

    /**
     * Since there is no success message return the same map
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
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }
}
