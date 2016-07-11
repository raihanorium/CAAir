package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.Customer
import com.caair.entity.PaymentInfo
import org.apache.log4j.Logger


class PaymentInfoService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    CustomerService customerService

    @Override
    public void init() {
        domainClass = PaymentInfo.class
    }

    /**
     * Pull paymentInfo object
     * @return - list of paymentInfo
     */
    @Override
    public List<PaymentInfo> list() {
        return PaymentInfo.list()
    }

    public PaymentInfo findByTransactionNo(String transactionNo) {
        return PaymentInfo.findByTransactionNo(transactionNo, [readOnly: true])
    }

    /**
     * create default data
     * this method is called from the BootstrapActionService class
     * @return - true if default data is loaded successfully
     */
    @Override
    boolean createDefaultData() {
        try {
            Customer customer = customerService.findByEmailId('abcxyz001@gmail.com')
            new PaymentInfo(customerId: customer.id, cardType: 'Master Card', cardNo: '564654986549898', cardExpiredDate: '04-12-2020', transactionNo: '555', transactionDate: new Date(), price: 500, paymentStatus: 'PAID').save()
            new PaymentInfo(customerId: customer.id, cardType: 'Master Card', cardNo: '564654986549898', cardExpiredDate: '04-12-2020', transactionNo: '556', transactionDate: new Date(), price: 700, paymentStatus: 'PAID').save()
            new PaymentInfo(customerId: customer.id, cardType: 'Master Card', cardNo: '564654986549898', cardExpiredDate: '04-12-2020', transactionNo: '557', transactionDate: new Date(), price: 1200, paymentStatus: 'PAID').save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
