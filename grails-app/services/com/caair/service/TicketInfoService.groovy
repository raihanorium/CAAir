package com.caair.service

import com.caair.BaseDomainService
import com.caair.entity.Customer
import com.caair.entity.Flight
import com.caair.entity.PaymentInfo
import com.caair.entity.TicketInfo
import org.apache.log4j.Logger


class TicketInfoService extends BaseDomainService {

    private Logger log = Logger.getLogger(getClass())

    CustomerService customerService
    FlightService flightService
    PaymentInfoService paymentInfoService

    @Override
    public void init() {
        domainClass = TicketInfo.class
    }

    /**
     * Pull ticketInfo object
     * @return - list of ticketInfo
     */
    @Override
    public List<TicketInfo> list() {
        return TicketInfo.list()
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
            Flight flightOne = flightService.findByArrivalAirPort('Dhaka')
            Flight flightTwo = flightService.findByArrivalAirPort('London')
            Flight flight3 = flightService.findByArrivalAirPort('Canada')
            Flight flight4 = flightService.findByArrivalAirPort('France')
            Flight flight5 = flightService.findByArrivalAirPort('Malaysia')
            Flight flight6 = flightService.findByArrivalAirPort('Dubai')
            Flight flight7 = flightService.findByArrivalAirPort('India')
            Flight flight8 = flightService.findByArrivalAirPort('Mexico')
            Flight flight9 = flightService.findByArrivalAirPort('UK')
            Flight flight10 = flightService.findByArrivalAirPort('Spain')
            Flight flight11 = flightService.findByArrivalAirPort('Italy')
            Flight flight12 = flightService.findByArrivalAirPort('Brazil')
            PaymentInfo paymentInfo = paymentInfoService.findByTransactionNo('555')

            new TicketInfo(customerId: customer.id, flightId: flightOne.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5645').save()
            new TicketInfo(customerId: customer.id, flightId: flightTwo.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5646').save()
            new TicketInfo(customerId: customer.id, flightId: flight3.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5647').save()
            new TicketInfo(customerId: customer.id, flightId: flight4.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5648').save()
            new TicketInfo(customerId: customer.id, flightId: flight5.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5649').save()
            new TicketInfo(customerId: customer.id, flightId: flight6.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5650').save()
            new TicketInfo(customerId: customer.id, flightId: flight7.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5651').save()
            new TicketInfo(customerId: customer.id, flightId: flight8.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5652').save()
            new TicketInfo(customerId: customer.id, flightId: flight9.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5653').save()
            new TicketInfo(customerId: customer.id, flightId: flight10.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5654').save()
            new TicketInfo(customerId: customer.id, flightId: flight11.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5655').save()
            new TicketInfo(customerId: customer.id, flightId: flight12.id, paymentInfoId: paymentInfo.id, price: 10000, ticketNo: 'j5655').save()
            return true
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }


}
