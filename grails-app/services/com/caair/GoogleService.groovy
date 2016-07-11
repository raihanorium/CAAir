package com.caair

import grails.converters.JSON
import grails.transaction.Transactional
import org.scribe.model.Token

@Transactional(readOnly = true)
class GoogleService {

    def grailsApplication
    def oauthService
    def springSecurityService

    def getCurrentUserDetails(Token googleAccessToken) {
        if (!googleAccessToken) {
            throw new RuntimeException('Token not found.')
        }

        String url = "https://www.googleapis.com/oauth2/v1/userinfo"
        def googleResource = oauthService.getGoogleResource(googleAccessToken, url)
        def googleResponse = JSON.parse(googleResource?.getBody())
        /*******************************************Custom code*********************************/
//        println googleResponse.provider
//        if (!googleResponse.provider) {
//            renderError 400, "The Spring Security OAuth callback URL must include the 'provider' URL parameter."
//            return
//        }
//
//        def sessionKey = oauthService.findSessionKeyForAccessToken(googleResponse.provider)
//        if (!session[sessionKey]) {
//            renderError 500, "No OAuth token in the session for provider '${googleResponse.provider}'!"
//            return
//        }

//        // Create the relevant authentication token and attempt to log in.
//        OAuthToken oAuthToken = createAuthToken(googleResponse.provider, session[sessionKey])
//
//        if (oAuthToken.principal instanceof GrailsUser) {
//            authenticateAndRedirect(oAuthToken, defaultTargetUrl)
//        }

        /************************************END*******************************************/
        return googleResponse
    }

//    private OAuthToken createAuthToken(providerName, scribeToken) {
//        def providerService = grailsApplication.mainContext.getBean("${providerName}SpringSecurityOAuthService")
//        OAuthToken oAuthToken = providerService.createAuthToken(scribeToken)
//
//        def oAuthID = OAuthID.findByProviderAndAccessToken(oAuthToken.providerName, oAuthToken.socialId)
//        if (oAuthID) {
//            updateOAuthToken(oAuthToken, oAuthID.user)
//        }
//
//        return oAuthToken
//    }
}
