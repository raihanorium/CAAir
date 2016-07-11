package com.caair.controller

import org.scribe.model.Token

class LinkedinController {

    def oauthService
    def linkedinService

    def index() {
        render view: '/index'
    }

    def me() {
        Token linkedinAccessToken = (Token) session[oauthService.findSessionKeyForAccessToken('linkedin')]
        try {
            def (data, linkedinParsedResponse) = linkedinService.getCurrentUserDetails(linkedinAccessToken)
            render view: '/oauthCallBack/index', model: [provider: 'LinkedIn Response', data: data, fullResponse: linkedinParsedResponse]
        } catch (Exception ce) {
            log.error ce.getMessage()
            flash.error = ce.getMessage()
            render view: '/index'
        }
    }
}
