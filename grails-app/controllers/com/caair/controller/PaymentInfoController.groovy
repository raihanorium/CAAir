package com.caair.controller

import com.caair.action.paymentinfo.CreatePaymentInfoActionService
import com.caair.action.paymentinfo.DeletePaymentInfoActionService
import com.caair.action.paymentinfo.EditPaymentInfoActionService
import com.caair.action.paymentinfo.ListPaymentInfoActionService
import com.caair.action.paymentinfo.UpdatePaymentInfoActionService


class PaymentInfoController extends BaseController {

    CreatePaymentInfoActionService createPaymentInfoActionService
    UpdatePaymentInfoActionService updatePaymentInfoActionService
    ListPaymentInfoActionService listPaymentInfoActionService
    DeletePaymentInfoActionService deletePaymentInfoActionService
    EditPaymentInfoActionService editPaymentInfoActionService

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST", delete: "POST", list: "POST", edit: "POST"
    ]

    /**
     * Show PaymentInfo UI
     */
    def show() {
        render(view: "/paymentinfo/show")
    }

    /**
     * Create new PaymentInfo
     */
    def create() {
        renderView(createPaymentInfoActionService, params, "/paymentinfo/show")
    }

    /*
     * load data & display for update
     * */

    def edit() {
        renderView(editPaymentInfoActionService, params, "/paymentinfo/edit")
    }

    /**
     * Update PaymentInfo
     */
    def update() {
        renderView(updatePaymentInfoActionService, params, "/paymentinfo/edit")
    }

    /**
     * Delete PaymentInfo
     *
     */
    def delete() {
        renderAsJSON(deletePaymentInfoActionService, params)
    }

    /**
     * List & Search PaymentInfo
     */
    def list() {
        renderView(listPaymentInfoActionService, params, "/paymentinfo/list")
    }
}

