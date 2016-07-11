package com.caair.service

import com.caair.BaseService
import com.caair.model.SearchFlightActionServiceModel

class SearchFlightActionServiceModelService extends BaseService {

    public List<SearchFlightActionServiceModel> list() {
        return SearchFlightActionServiceModel.list()
    }

    public void createDefaultSchema() {
        executeSql(SearchFlightActionServiceModel.SQL_SEARCH_FLIGHT_MODEL)
    }

    public List search(String from, String to) {
        return SearchFlightActionServiceModel.findAllByDepartureAirPortIlikeAndArrivalAirPortIlike(from, to)
    }
}