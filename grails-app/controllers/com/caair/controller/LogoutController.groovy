package com.caair.controller

import grails.plugin.springsecurity.SpringSecurityUtils


class LogoutController {
    def oauthService


    def index() {
        if (params.id && session[oauthService.findSessionKeyForAccessToken(params.id)]) {
            session[oauthService.findSessionKeyForAccessToken(params.id)] = null
            flash.message = "Token revoked successfully."
        }

        // @todo call google logout web service to permanently logout from google account

        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
    }
}
