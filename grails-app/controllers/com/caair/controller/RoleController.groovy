package com.caair.controller

import com.caair.action.role.CreateRoleActionService
import com.caair.action.role.DeleteRoleActionService
import com.caair.action.role.EditRoleActionService
import com.caair.action.role.ListRoleActionService
import com.caair.action.role.UpdateRoleActionService

class RoleController extends BaseController {

    static allowedMethods = [
            show: "POST", create: "POST", update: "POST", delete: "POST", list: "POST", edit: "POST"
    ]

    ListRoleActionService listRoleActionService
    CreateRoleActionService createRoleActionService
    EditRoleActionService editRoleActionService
    UpdateRoleActionService updateRoleActionService
    DeleteRoleActionService deleteRoleActionService

    def show() {
        render(view: '/role/create')
    }

    /**
     * List & Search Role
     */
    def list() {
        renderView(listRoleActionService, params, "/role/list")
    }

    /**
     * create new Role object
     */
    def create() {
        renderView(createRoleActionService, params, "/role/create")
    }

    /**
     * show data in edit mode
     */
    def edit() {
        renderView(editRoleActionService, params, '/role/edit')
    }

    /**
     * Update Role object
     */
    def update() {
        renderView(updateRoleActionService, params, '/role/edit')
    }

    /**
     * Delete Role object
     */
    def delete() {
        renderView(deleteRoleActionService, params, '/role/index')
    }
}
