package com.caair.controller

import com.caair.action.login.AuthFailActionService
import com.caair.action.login.CheckLoginActionService
import com.caair.action.login.LoginSuccessActionService
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils


class LoginController {

    private static final String SESSION = 'session'

    SpringSecurityService springSecurityService
    CheckLoginActionService checkLoginActionService
    LoginSuccessActionService loginSuccessActionService
    AuthFailActionService authFailActionService

    /**
     * login authentication url
     */
    def auth() {
        def config = SpringSecurityUtils.securityConfig

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }
        render view: '/index'
    }

    /**
     * loginSuccess url will be fired after Spring security successful verification
     */
    def loginSuccess() {
        params.put(SESSION, session)
        Map result = (Map) loginSuccessActionService.executePreCondition(params)
        boolean isError = (boolean) result.isError
        if (isError) {
            render view: '/index', model: [message: result.message]
            return
        }
        result = (Map) loginSuccessActionService.execute(result)
        if (isError) {
            render view: '/index', model: [message: result.message]
            return
        }
        redirect(uri: result.postUrl)
    }

    /**
     * check login using Spring Security Core plugin
     */
    def checkLogin() {
        Map result = (Map) checkLoginActionService.executePreCondition(params)
        boolean isError = (boolean) result.isError
        if (isError) {
            render view: '/index', model: [message: result.message]
            return
        }
        def model = [j_username: params.j_username, j_password: params.j_password]
        session['j_username'] = params.j_username

        // This is a quick implementation and Spring Security Core is used.
        // In real implementation, Spring Security LDAP checking will be used

//      @todo add you logic to integrate with active directory
        redirect(uri: "/j_spring_security_check", params: model)
    }

    /**
     * in case of authentication fail
     */
    def authfail() {
        params.put(SESSION, session)
        Map result = (Map) authFailActionService.execute(params)
        render view: '/index', model: [message: result.message]
    }
}
