package com.caair.controller

import com.caair.action.flight.*

class FlightController extends BaseController {

    ShowFlightActionService showFlightActionService
    CreateFlightActionService createFlightActionService
    UpdateFlightActionService updateFlightActionService
    ListFlightActionService listFlightActionService
    DeleteFlightActionService deleteFlightActionService
    EditFlightActionService editFlightActionService
    CancelFlightActionService cancelFlightActionService
    SearchFlightActionService searchFlightActionService
    ShowSearchFlightResultActionService showSearchFlightResultActionService

    static allowedMethods = [
            create: "POST", update: "POST", delete: "POST", list: "POST", edit: "POST", cancelFlight: "POST"
    ]

    /**
     * Show Flight UI
     */
    def show() {
        renderView(showFlightActionService, params, "/flight/show")
    }

    def showAsJSON() {
        renderAsJSON(showFlightActionService, params)
    }

    /**
     * Create new Flight
     */
    def create() {
        renderView(createFlightActionService, params, '/flight/show')
    }

    /*
     * load data & display for update
     * */
    def edit = {
        renderView(editFlightActionService, params, '/flight/edit')
    }

    /**
     * Update Flight
     */
    def update() {
        renderView(updateFlightActionService, params, '/flight/edit')
    }

    /**
     * Delete Flight
     *
     */
    def delete() {
        renderAsJSON(deleteFlightActionService, params)
    }

    /**
     * List & Search Flight
     */
    def list() {
        renderView(listFlightActionService, params, "/flight/list")
    }

    /**
     * Cancel Flight object
     */
    def cancelFlight() {
        renderAsJSON(cancelFlightActionService, params)
    }

    /**
     * Search flight object
     */
    def search() {
        renderAsJSON(searchFlightActionService, params)
    }

    def showSearchResult() {
        renderAsJSON(showSearchFlightResultActionService, params)
    }
}

