package com.caair.controller

import com.caair.action.appuser.ListAppUserActionService
import com.caair.action.appuser.RegisterAppUserActionService
import com.caair.entity.AppUser
import com.caair.service.AppUserService

/**
 * Controller for AppUser
 */
class AppUserController extends BaseController {

    AppUserService appUserService

    static allowedMethods = [
            register: "POST", list: "POST"
    ]

    RegisterAppUserActionService registerAppUserActionService
    ListAppUserActionService listAppUserActionService

    /**
     * Register new user in the system
     */
    def register() {
        renderAsJSON(registerAppUserActionService, params)
    }

    /**
     * Get List of AppUser
     */
    def list() {
        renderAsJSON(listAppUserActionService, params)
    }

    /**
     * show profile info
     */
    def showProfile() {
        long userId = session.appUser.id
        AppUser appUser = (AppUser) appUserService.read(userId)
        Map data = [Id     : appUser.id, Name: appUser.username,
                    Gender : appUser.gender,
                    Picture: g.img(uri: appUser.imagePath)]
        render view: '/oauthCallBack/showProfile', model: [data: data]
    }
}
