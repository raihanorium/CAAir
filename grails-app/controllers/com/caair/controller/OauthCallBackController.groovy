package com.caair.controller

import com.caair.action.appuser.CreateAppUserActionService
import com.caair.action.flight.SearchFlightActionService
import com.caair.entity.AppUser
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils
import org.scribe.model.Token
import uk.co.desirableobjects.oauth.scribe.holder.RedirectHolder

class OauthCallBackController extends BaseController {

    SpringSecurityService springSecurityService
    def oauthService
    CreateAppUserActionService createAppUserActionService
    SearchFlightActionService searchFlightActionService

    public static final String SPRING_SECURITY_OAUTH_TOKEN = 'springSecurityOAuthToken'

    def index() {
        def config = SpringSecurityUtils.securityConfig
        if (springSecurityService.isLoggedIn()) {
            if (session.loggedInBy) {
                renderView(searchFlightActionService, params, "/flight/search")
            } else {
                redirect uri: config.successHandler.defaultTargetUrl
            }
        } else {
            render view: '/index'
        }
    }

    def twitter() {
        if (session[oauthService.findSessionKeyForAccessToken('twitter')]) {
            flash.message = "Twitter authentication successful. Use top navigation menu for more action."
        }
        redirect action: 'index'
    }

    def linkedin() {
        if (session[oauthService.findSessionKeyForAccessToken('linkedin')]) {
            flash.message = "LinkedIn authentication successful. Use top navigation menu for more action."
        }
        redirect action: 'index'
    }

    def google() {
        if (session[oauthService.findSessionKeyForAccessToken('google')]) {
            Token googleAccessToken = (Token) session[oauthService.findSessionKeyForAccessToken('google')]
            params.put("googleAccessToken", googleAccessToken)
            Map result = getServiceResponse(createAppUserActionService, params)
            AppUser appUser = (AppUser) result.appuser
            session.img = result.img
            session.loggedInBy = "google"
            session.appUser = appUser
            Map uriMap = (Map) RedirectHolder.getRedirect();
            if (uriMap && uriMap.uri != "/") {
                String[] temp = uriMap.uri.split("/");
                redirect(controller: temp[0], action: temp[1])
            } else {
                redirect action: 'index'
            }
        }
    }

    def failure() {
        flash.error = "Error."
        render view: '/index'
    }

    def revoke() {
        if (params.id && session[oauthService.findSessionKeyForAccessToken(params.id)]) {
            println session
            session[oauthService.findSessionKeyForAccessToken(params.id)] = null
            flash.message = "Token revoked successfully."
        }
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
    }
}
