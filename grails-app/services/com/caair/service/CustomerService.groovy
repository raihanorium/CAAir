package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.Customer
import org.apache.log4j.Logger

class CustomerService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    @Override
    public void init() {
        domainClass = Customer.class
    }

    /**
     * Pull list of customer object
     * @return - list of customer
     */
    public List<Customer> list() {
        return Customer.list();
    }

/**
 * get count of all customers
 * @return - count number
 */
    public int count() {
        return Customer.count()
    }
    /**
     * Get list of Customer by list of ids
     * @param lstCustomerIds - list of AppGroup.id
     * @return - list of Customer by ids
     */
    public Customer findByEmailId(String emailId) {
        Customer customer = Customer.findByEmail(emailId, [readOnly: true])
        return customer
    }

    /**
     * count by email
     * @param email - email
     * @return - count
     */
    public int countByEmail(String email) {
        int count = Customer.countByEmail(email)
        return count
    }

    /**
     * count by phone
     * @param phone - phone
     * @return - count
     */
    public int countByPhone(String phone) {
        int count = Customer.countByPhone(phone)
        return count
    }

    /**
     * count by email and id not equal
     * @param email - email
     * @param id - customer.id
     * @return - count
     */
    public int countByEmailAndIdNotEqual(String email, long id) {
        int count = Customer.countByEmailAndIdNotEqual(email, id)
        return count
    }

    /**
     * count by phone and id not equal
     * @param phone - phone
     * @param id - customer.id
     * @return - count
     */
    public int countByPhoneAndIdNotEqual(String phone, long id) {
        int count = Customer.countByPhoneAndIdNotEqual(phone, id)
        return count
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            new Customer(name: 'Ripon Rana', email: 'abcxyz001@gmail.com', phone: '01685414386').save()
            new Customer(name: 'MD Shamim', email: 'abcxyz002@gmail.com', phone: '01683199288').save()
            new Customer(name: 'Syed Mesbah Ahmed', email: 'abcxyz003@gmail.com', phone: '01685414386').save()
            new Customer(name: 'Rumana Afroz', email: 'abcxyz004@gmail.com', phone: '01918868682').save()
            new Customer(name: 'Quazi Qutub Mia', email: 'abcxyz005@gmail.com', phone: '01713955862').save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }
}
