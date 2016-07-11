package com.caair.controller

import com.caair.action.flightbooking.SearchFlightBookingActionService


class FlightBookingController extends BaseController {

    SearchFlightBookingActionService searchFlightBookingActionService;

    /**
     * Show Search window
     * */
    def show() {
        render(view: "search")
    }

    /**
     * Show search result
     */
    def searchResult() {
        renderView(searchFlightBookingActionService, params, '/flightBooking/result')
    }

}

