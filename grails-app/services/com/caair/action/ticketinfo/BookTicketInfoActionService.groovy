package com.caair.action.ticketinfo

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.service.TicketInfoService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

/**
 *  Booking a TicketInfo object
 */
class BookTicketInfoActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    @Autowired
    TicketInfoService ticketInfoService

    /**
     * While booking TicketInfo object this method is called first.
     * In this method check all preconditions before booking a new TicketInfo object (i.e required params existence, TicketInfo object availability for booking)
     * Put this method in transactional(readOnly) block if only DB read operation is needed or transactional(read/write) if both read/write is needed.
     *
     * @param params - serialized parameters from UI
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map executePreCondition(Map params) {
        try {
            // @todo check required params and return corresponding message if not comply i.e return super.setError(params, errMsg)
            // @todo check server side validation process (i.e. TicketInfo object availability for booking)
            // @todo check other logic if you might need before booking TicketInfo
            return params
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method will be called if executePreCondition method has been complied.
     * Book a new TicketInfo object in DB
     * Put this method in transactional(read/write) block so that it will roll back in case of any exception
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional
    public Map execute(Map result) {
        try {
            // @todo build TicketInfo object from result.
            // @todo book new TicketInfo object, such as ticketInfoService.create(TicketInfo)
            // @todo put TicketInfo object in result for future reference.
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method will call after creating an TicketInfo object.
     * Put this method in transactional(readOnly) block if only DB read operation is needed or transactional(read/write) if both read/write is needed.
     *
     * @param result - Previous result of executing the Action
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        // @todo if any functionality needed after create TicketInfo object, add your logic here.
        return result
    }

    /**
     * This method will call after executing executePostCondition method if the method has been complied.
     * Write code to build success message
     *
     * @param result - map from executePostCondition method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        // @todo put success message (i.e return super.setSuccess(result, "Booked successfully."))
        return result
    }

    /**
     * This method will call if executePreCondition or execute or executePostCondition method has not been complied.
     * Write code to build failure message
     * The input-parameter Map will have "isError:true" with corresponding message, just return the map if no other action is required.
     *
     * @param result - Execute result of previously running methods. Previously running
     * method could either be:
     *      executePreCondition @see this.executePreCondition, or
     *      execute @see this.execute, or
     *      execute @see this.executePostCondition
     * @return - A map containing JSON formatted result to be shown in the UI
     */
    public Map buildFailureResultForUI(Map result) {
        // @todo if any functionality needed for handling failure scenario, add your logic here.
        return result
    }
}
