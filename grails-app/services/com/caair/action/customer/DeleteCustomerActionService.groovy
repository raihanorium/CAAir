package com.caair.action.customer

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Customer
import com.caair.service.CustomerService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

/**
 * Delete customer
 */
class DeleteCustomerActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String DELETE_CUSTOMER_SUCCESS_MESSAGE = "Customer has been deleted successfully"
    private static final String CUSTOMER = "customer"
    private static final String ENTITY_NOT_FOUND_ERROR_MESSAGE = "Customer not found"
    private static final String CUSTOMER_LIST = "customers"
    private static final String CUSTOMER_COUNT = "customerInstanceCountList"
    private static final String HAS_ASSOCIATION_PAYMENT_INFO = " payment info has association with customer"
    private static final String HAS_ASSOCIATION_TICKET_INFO = " ticket info has association with customer"

    CustomerService customerService

    /**
     * 1. Check Validation
     * 2. Association check for customer with different domains
     *
     * @param parameters - serialize parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            //Check Validation
            String errMsg = checkValidation(params)
            if (errMsg) {
                return super.setError(params, errMsg)
            }
            //association check for customer with different domains
            String associationMsg = hasAssociation(params)
            if (associationMsg) {
                return super.setError(params, associationMsg)
            }
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * Delete customer object from DB
     * 1. get the customer object from map
     * 2. delete from db
     * This function is in transactional boundary and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {
            Customer customer = (Customer) result.get(CUSTOMER)
            // delete from database
            customerService.delete(customer)
            List<Customer> customers = customerService.list();
            int customeCount = customerService.count;
            result.put(CUSTOMER_LIST, customers)
            result.put(CUSTOMER_COUNT, customeCount)
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
     * @param result - map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return super.setSuccess(result, DELETE_CUSTOMER_SUCCESS_MESSAGE)
    }

    /**
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result - map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        return result
    }

    /**
     * 1.check required parameter
     * 2.check for customer existence
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkValidation(Map params) {
        // check required parameter
        String errMsg = checkRequiredParams(params)
        if (errMsg) return errMsg
        // check for customer existence
        errMsg = checkCustomerExistence(params)
        if (errMsg) return errMsg
        return null
    }

    /**
     * check required parameter
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkRequiredParams(Map params) {
        if (!params.id) {
            return ERROR_FOR_INVALID_INPUT
        }
        return null
    }

    /**
     * check for customer existence
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkCustomerExistence(Map params) {
        long customerId = Long.parseLong(params.id.toString())
        Customer customer = (Customer) customerService.read(customerId)
        if (!customer) {
            return ENTITY_NOT_FOUND_ERROR_MESSAGE
        }
        params.put(CUSTOMER, customer)
        return null
    }

    /**
     * 3. check association with paymentInfo
     * 4. check association with ticketInfo
     *
     * @param params - a map from caller method
     * @return - specific association message
     */
    private String hasAssociation(Map params) {
        Customer customer = (Customer) params.get(CUSTOMER)
        // check association with paymentInfo
        int count = countPaymentInfo(customer.id)
        if (count > 0) {
            return count.toString() + HAS_ASSOCIATION_PAYMENT_INFO
        }
        // check association with paymentInfo
        count = countTicketInfo(customer.id)
        if (count > 0) {
            return count.toString() + HAS_ASSOCIATION_TICKET_INFO
        }
        return null
    }

    private static final String COUNT_PAYMENT_INFO = """
        SELECT COUNT(id) AS count
        FROM payment_info
        WHERE customer_id = :customerId
    """

    /**
     * count association with paymentInfo
     * @param customerId - Customer.id
     * @return - number of payment count
     */
    private int countPaymentInfo(long customerId) {
        Map queryParams = [
                customerId: customerId
        ]
        List results = executeSelectSql(COUNT_PAYMENT_INFO, queryParams)
        int count = results[0].count
        return count
    }

    private static final String COUNT_TICKET_INFO = """
        SELECT COUNT(id) AS count
        FROM ticket_info
        WHERE customer_id = :customerId
    """

    /**
     * count association with ticketInfo
     * @param customerId - Customer.id
     * @return - number of ticket count
     */
    private int countTicketInfo(long customerId) {
        Map queryParams = [
                customerId: customerId
        ]
        List results = executeSelectSql(COUNT_TICKET_INFO, queryParams)
        int count = results[0].count
        return count
    }
}
