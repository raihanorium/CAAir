package com.caair.service

import com.caair.BaseService
import com.caair.model.ShowFlightActionServiceModel
import grails.transaction.Transactional

@Transactional
class ShowFlightActionServiceModelService extends BaseService {

    /**
     * get list of all ShowFlightActionServiceModel
     */
    public List<ShowFlightActionServiceModel> list() {
        return ShowFlightActionServiceModel.list()
    }

    /**
     * create default schema
     */
    public void createDefaultSchema() {
        executeSql(ShowFlightActionServiceModel.SQL_LIST_FLIGHT)
    }
}
