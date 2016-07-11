package com.caair.controller

import org.scribe.model.Token

class TwitterController {

    def oauthService
    def twitterService

    def index() {
        render view: '/index'
    }

    def me() {
        Token twitterAccessToken = (Token) session[oauthService.findSessionKeyForAccessToken('twitter')]
        try {
            def twitterResponseDetailed = twitterService.getCurrentUserDetails(twitterAccessToken)

            Map data = [Id             : twitterResponseDetailed.id, 'Screen Name': twitterResponseDetailed.screen_name, Name: twitterResponseDetailed.name,
                        Lang           : twitterResponseDetailed.lang, 'Created At': twitterResponseDetailed.created_at,
                        'Profile Image': g.img(uri: twitterResponseDetailed.profile_image_url)]

            render view: '/oauthCallBack/index', model: [provider: 'Twitter Response', data: data, fullResponse: twitterResponseDetailed]
        } catch (Exception ce) {
            log.error ce.getMessage()
            flash.error = ce.getMessage()
            render view: '/index'
        }
    }
}
