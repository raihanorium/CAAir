package com.caair.controller

import org.scribe.model.Token

class GoogleController {

    def oauthService
    def googleService

    def index() {
        render view: '/index'
    }

    def me() {
        Token googleAccessToken = (Token) session[oauthService.findSessionKeyForAccessToken('google')]
        try {
            def googleResponse = googleService.getCurrentUserDetails(googleAccessToken)

            Map data = [Id     : googleResponse.id, Name: googleResponse.name, 'Given Name': googleResponse.given_name, 'Family Name': googleResponse.family_name,
                        Gender : googleResponse.gender, Link: g.link(url: googleResponse.link) { 'Profile Link' },
                        Picture: g.img(uri: googleResponse.picture)]

            render view: '/oauthCallBack/showProfile', model: [provider: 'Google + Response', data: data, fullResponse: googleResponse]
        } catch (Exception ce) {
            log.error ce.getMessage()
            flash.error = ce.getMessage()
            render view: '/index'
        }
    }
}
