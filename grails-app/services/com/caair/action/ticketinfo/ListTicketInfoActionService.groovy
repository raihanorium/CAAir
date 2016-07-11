package com.caair.action.ticketinfo

import com.caair.BaseService
import com.caair.ActionServiceIntf
import com.caair.service.TicketInfoService
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


/**
 *  Show list of all/filtered TicketInfo in grid
 */
class ListTicketInfoActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass());

    @Autowired
    TicketInfoService ticketInfoService

    /**
     * While getting list of TicketInfo object this method is called first.
     * No pre conditions required for searching TicketInfo
     *
     * @param params - Request parameters
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePreCondition(Map params) {
        return params
    }

    /**
     * This method will be called if executePreCondition method has been complied.
     * Pull all TicketInfo list from database
     *
     * @param result - parameter from pre-condition
     * @return - same map of input-parameter containing isError(true/false)
     */
    @Transactional(readOnly = true)
    public Map execute(Map result) {
        try {
            // @todo get list of TicketInfo object (i.e. ticketInfoService.list())
            // @todo get count of TicketInfo object (i.e. ticketInfoService.count())
            // @todo put list & count in result
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    /**
     * This method will call after getting list of TicketInfo objects.
     * There is no postCondition, so return the same map as received
     *
     * @param result - resulting map from execute
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map executePostCondition(Map result) {
        // @todo if any functionality needed after getting list of TicketInfo object, add your logic here.
        return result;
    }

    /**
     * This method will call after executing executePostCondition method if the method has been complied.
     * Since there is no success message return the same map
     *
     * @param result -map from execute/executePost method
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildSuccessResultForUI(Map result) {
        // @todo if any functionality needed after getting list of TicketInfo object, add your logic here.
        return result
    }

    /**
     * This method will call if executePreCondition or execute or executePostCondition method has not been complied.
     * The input-parameter Map must have "isError:true" with corresponding message
     *
     * @param result -map returned from previous methods
     * @return - same map of input-parameter containing isError(true/false)
     */
    public Map buildFailureResultForUI(Map result) {
        // @todo if any functionality needed for handling failure scenario, add your logic here.
        return result
    }
}
