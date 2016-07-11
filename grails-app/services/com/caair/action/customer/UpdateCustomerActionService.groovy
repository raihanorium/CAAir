package com.caair.action.customer

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.entity.Customer
import com.caair.service.CustomerService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

/**
 * Update customer
 */
class UpdateCustomerActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String CUSTOMER = "customer"
    private static final String CUSTOMER_UPDATE_SUCCESS_MESSAGE = "Customer has been updated successfully"
    private static final String ENTITY_NOT_FOUND_ERROR_MESSAGE = "Customer not found"
    private static final String CUSTOMER_INVALID_EMAIL = "Email address already exists"
    private static final String VERSION_MISMATCHED = "Version mismatched. Please refresh and try again"
    private static final String CUSTOMER_INVALID_PHONE = "Phone number already exists"

    CustomerService customerService

    /**
     * 1. Check Validation
     * 2. Check un-approve transactions for auto approve
     * 3. Build customer object for update
     *
     * @param params - serialized parameters from UI
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

            // build customer object for update
            Customer customer = getCustomer(params);
            params.put(CUSTOMER, customer)

            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * 1. get the customer object from map
     * 2. Update existing customer in DB
     * This method is in transactional block and will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {
            Customer customer = (Customer) result.get(CUSTOMER)
            customerService.update(customer)
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
        return super.setSuccess(result, CUSTOMER_UPDATE_SUCCESS_MESSAGE)
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
     * Build customer object for update
     *
     * @param params - serialize parameters from UI
     * @return - customer object
     */
    private Customer getCustomer(Map params) {
        Customer oldCustomer = (Customer) params.get(CUSTOMER)
        Customer newCustomer = new Customer(params)
        oldCustomer.email = newCustomer.email;
        oldCustomer.name = newCustomer.name;
        oldCustomer.phone = newCustomer.phone
        // write approval flag holds previous state if user is not config manager

        return oldCustomer;
    }

    /**
     * 1. check for necessary parameters
     * 2. check Customer object existance
     * 3. check version mismatched
     * 4. check duplicate email
     * 5. check duplicate email
     * 6. check duplicate phone
     *
     * @param customer - object of Customer
     * @param params - a map from caller method
     * @return -a string containing null value or error message depending on duplicate check
     */
    private String checkValidation(Map params) {
        // check for necessary parameters
        String errMsg = checkRequiredParams(params)
        if (errMsg) return errMsg
        // check Customer object existance
        errMsg = checkCustomerExistance(params)
        if (errMsg) return errMsg
        // check version mismatched
        errMsg = checkVersionMisMatch(params)
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
     * check Customer object existance
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkCustomerExistance(Map params) {
        long customerId = Long.parseLong(params.id.toString())
        Customer customer = customerService.read(customerId)
        long version = Long.parseLong(params.version.toString())
        if (!customer) {
            return ENTITY_NOT_FOUND_ERROR_MESSAGE
        }
        params.put(CUSTOMER, customer)
        return null
    }

    /**
     * check version mismatched
     *
     * @param params - a map from caller method
     * @return - error message or null depending on validation
     */
    private String checkVersionMisMatch(Map params) {
        Customer customer = (Customer) params.get(CUSTOMER)
        long version = Long.parseLong(params.version.toString())
        if (version != customer.version) {
            return VERSION_MISMATCHED
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
        long customerId = Long.parseLong(params.id.toString())
        String emailId = params.email.toString()
        int count = customerService.countByEmailAndIdNotEqual(emailId, customerId);
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
        long customerId = Long.parseLong(params.id.toString())
        String phone = params.phone.toString()
        int count = customerService.countByPhoneAndIdNotEqual(phone, customerId)
        if (count > 0) {
            return CUSTOMER_INVALID_PHONE
        }
        return null
    }

}
