package com.caair.action.customer

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Customer
import com.caair.service.CustomerService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

/**
 * Create new customer
 */
class CreateCustomerActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String CUSTOMER = "customer"
    private static final String CUSTOMER_SAVE_SUCCESS_MESSAGE = "Customer has been saved successfully"
    private static final String CUSTOMER_INVALID_EMAIL = "Email address already exists"
    private static final String CUSTOMER_INVALID_PHONE = "Phone number already exists"

    CustomerService customerService

    /**
     * 1. check Validation
     * 2. build customer object
     * This method is in transactional readOnly block and will roll back in case of any exception
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            // check Validation
            String errMsg = checkValidation(params)
            if (errMsg) {
                return super.setError(params, errMsg)
            }
            // build customer object
            Customer customer = getCustomer(params)
            params.put(CUSTOMER, customer)
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * 1. receive customer object from executePreCondition method
     * 2. create new customer
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {
            Customer customer = (Customer) result.get(CUSTOMER)
            // save new customer object in DB
            customerService.create(customer)
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
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        return super.setSuccess(result, CUSTOMER_SAVE_SUCCESS_MESSAGE)
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
     * Build customer object
     *
     * @param params - serialize parameters from UI
     * @param user - an object of AppUser
     * @return - customer object
     */
    private Customer getCustomer(Map params) {
        Customer customer = new Customer(params)
        return customer
    }

    /**
     * 1. check for necessary parameters
     * 2. check duplicate email
     * 3. check duplicate phone
     *
     * @param params - parameter map from precondition
     * @return - error message or null depending on validation
     */
    private String checkValidation(Map params) {
        // check for necessary parameters
        String errMsg = checkRequiredParams(params)
        if (errMsg) return errMsg
        // check duplicate email
        errMsg = checkDuplicateEmail(params)
        if (errMsg) return errMsg
        // check duplicate phone
        errMsg = checkDuplicatePhone(params)
        if (errMsg) return errMsg
        return null
    }

    /**
     * check for necessary parameters
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkRequiredParams(Map params) {
        if (!params.name || !params.email || !params.phone) {
            return ERROR_FOR_INVALID_INPUT
        }
        return null
    }

    /**
     * check duplicate email
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkDuplicateEmail(Map params) {
        String emailId = params.email.toString()
        int count = customerService.countByEmail(emailId);
        if (count > 0) {
            return CUSTOMER_INVALID_EMAIL
        }
        return null
    }

    /**
     * check duplicate phone
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkDuplicatePhone(Map params) {
        String phone = params.phone.toString()
        int count = customerService.countByPhone(phone)
        if (count > 0) {
            return CUSTOMER_INVALID_PHONE
        }
        return null
    }

}

