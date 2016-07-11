package com.caair.action.flight

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.model.SearchFlightActionServiceModel
import com.caair.service.SearchFlightActionServiceModelService
import org.apache.log4j.Logger
import org.springframework.transaction.annotation.Transactional

/**
 *  Search Flight object
 */
class SearchFlightActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    private static final String LIST = "list"

    SearchFlightActionServiceModelService searchFlightActionServiceModelService

    @Override
    Map executePreCondition(Map parameters) {
        return parameters
    }

    @Transactional(readOnly = true)
    Map execute(Map result) {
        try {
            List<SearchFlightActionServiceModel> searchModel = searchFlightActionServiceModelService.list()
            result.put(LIST, searchModel)
            return result
        } catch (Exception ex) {
            log.error(ex.getMessage())
            throw new RuntimeException(ex)
        }
    }

    @Override
    Map executePostCondition(Map previousResult) {
        return previousResult
    }

    @Override
    Map buildSuccessResultForUI(Map executeResult) {
        return executeResult
    }

    @Override
    Map buildFailureResultForUI(Map executeResult) {
        return executeResult
    }
}
