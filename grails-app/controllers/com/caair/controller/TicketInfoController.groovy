package com.caair.controller

import com.caair.action.ticketinfo.CancelTicketActionService
import com.caair.action.ticketinfo.CreateTicketInfoActionService
import com.caair.action.ticketinfo.DeleteTicketInfoActionService
import com.caair.action.ticketinfo.EditTicketInfoActionService
import com.caair.action.ticketinfo.ListTicketInfoActionService
import com.caair.action.ticketinfo.UpdateTicketInfoActionService

class TicketInfoController extends BaseController {

    CreateTicketInfoActionService createTicketInfoActionService
    UpdateTicketInfoActionService updateTicketInfoActionService
    ListTicketInfoActionService listTicketInfoActionService
    DeleteTicketInfoActionService deleteTicketInfoActionService
    EditTicketInfoActionService editTicketInfoActionService
    CancelTicketActionService cancelTicketActionService

    static allowedMethods = [show: "POST", create: "POST", update: "POST", delete: "POST", list: "POST", edit: "POST", cancelTicket: "POST"]

    /**
     * Show TicketInfo UI
     */
    def show() {
        render(view: "/ticketinfo/show")
    }

    /**
     * Create new TicketInfo
     */
    def create() {
        renderView(createTicketInfoActionService, params, "/ticketinfo/show")
    }

    /*
      * load data & display for update
      * */

    def edit() {
        renderView(editTicketInfoActionService, params, "/ticketinfo/edit")
    }

    /**
     * Update TicketInfo
     */
    def update() {
        renderView(updateTicketInfoActionService, params, "/ticketinfo/edit")
    }

    /**
     * Delete TicketInfo
     *
     */
    def delete() {
        renderAsJSON(deleteTicketInfoActionService, params)
    }

    /**
     * List & Search TicketInfo
     */
    def list() {
        renderView(listTicketInfoActionService, params, "/ticketInfo/list")
    }

    /**
     * Cancel TicketInfo object
     */
    def cancelTicket() {
        renderAsJSON(cancelTicketActionService, params)
    }
}

