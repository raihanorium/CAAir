package com.caair.action.flight

import com.caair.ActionServiceIntf
import com.caair.BaseService
import com.caair.service.SearchFlightActionServiceModelService
import grails.gsp.PageRenderer
import org.apache.log4j.Logger

class ShowSearchFlightResultActionService extends BaseService implements ActionServiceIntf {

    private Logger log = Logger.getLogger(getClass())

    PageRenderer groovyPageRenderer
    SearchFlightActionServiceModelService searchFlightActionServiceModelService

    @Override
    Map executePreCondition(Map parameters) {
        return parameters
    }

    @Override
    Map execute(Map previousResult) {
        try {
            String from = previousResult.from
            String to = previousResult.to

            List lst = searchFlightActionServiceModelService.search(from, to)
            String html = groovyPageRenderer.render(template: "/flight/tmplSearchResult", model: [list: lst])
            previousResult.put("html", html)
            return previousResult
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
