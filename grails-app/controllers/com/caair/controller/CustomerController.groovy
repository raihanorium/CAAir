package com.caair.controller

import com.caair.action.customer.*

class CustomerController extends BaseController {
    CreateCustomerActionService createCustomerActionService
    UpdateCustomerActionService updateCustomerActionService
    ListCustomerActionService listCustomerActionService
    DeleteCustomerActionService deleteCustomerActionService
    EditCustomerActionService editCustomerActionService
    UpdateCustomerViewActionService updateCustomerViewActionService

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST", delete: "POST", list: "POST", updateCustomerView: "POST"
    ]

    /**
     * Show create Customer UI
     */
    def show() {
        render(view: "/customer/create")
    }

    /**
     * Create new Customer
     */
    def create() {
        renderView(createCustomerActionService, params, '/customer/create')
    }

    /**
     * Update customer view
     */
    def updateCustomerView() {
        renderView(updateCustomerViewActionService, params, '/customer/edit')
    }

    /**
     * Update Customer
     */
    def update() {
        renderView(updateCustomerActionService, params, '/customer/edit')
    }

    /**
     * Delete Customer
     */
    def delete() {
        renderView(deleteCustomerActionService, params, '/customer/index')
    }

    /**
     * List & Search Customer
     */
    def list() {
        String view = "/customer/list"
        renderView(listCustomerActionService, params, view)
    }

    /**
     * show edit view of customer update
     */
    def edit() {
        String view = "/customer/edit"
        renderView(editCustomerActionService, params, view)
    }
}

