package com.caair.controller

import com.caair.action.airplane.CreateAirPlaneActionService
import com.caair.action.airplane.DeleteAirPlaneActionService
import com.caair.action.airplane.ListAirPlaneActionService
import com.caair.action.airplane.EditAirPlaneActionService
import com.caair.action.airplane.UpdateAirPlaneActionService

class AirPlaneController extends BaseController {

    CreateAirPlaneActionService createAirPlaneActionService
    UpdateAirPlaneActionService updateAirPlaneActionService
    ListAirPlaneActionService listAirPlaneActionService
    DeleteAirPlaneActionService deleteAirPlaneActionService
    EditAirPlaneActionService editAirPlaneActionService

    static allowedMethods = [
            create: "POST", update: "POST", delete: "POST", list: "POST", edit: "POST"
    ]

    /**
     * Show create airplane UI
     */
    def show() {
        String view = "/airPlane/show"
        renderView(listAirPlaneActionService, params, view)
    }

    /**
     * create new AirPlane
     */
    def create() {
        renderView(createAirPlaneActionService, params, '/airPlane/create')
    }

    /**
     * show data in edit mode
     */
    def edit() {
        renderView(editAirPlaneActionService, params, '/airPlane/edit')
    }

    /**
     * Update AirPlane
     */
    def update() {
        renderView(updateAirPlaneActionService, params, '/airPlane/edit')
    }

    /**
     * Delete AirPlane
     *
     */
    def delete() {
        renderView(deleteAirPlaneActionService, params, '/airPlane/index')
    }

    /**
     * List & Search AirPlane
     */
    def list() {
        String view = "/airPlane/list"
        renderView(listAirPlaneActionService, params, view)
    }
}

