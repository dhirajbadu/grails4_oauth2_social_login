package auth.demo

import grails.plugin.springsecurity.oauth2.SpringSecurityOauth2BaseService
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import spring.security.oauth2.google.provider.GoogleOAuth2Service

class BootStrap {

    def init = { servletContext ->
        initProvider()
    }
    def destroy = {
    }

    def initProvider(){
        registerGoogleProvider()

    }

    def registerGoogleProvider(){
        def SpringSecurityOauth2BaseService oAuth2BaseService = grails.util.Holders.grailsApplication.mainContext.getBean('springSecurityOauth2BaseService') as SpringSecurityOauth2BaseService
        def GoogleOAuth2Service googleOAuth2Service = grails.util.Holders.grailsApplication.mainContext.getBean('googleOAuth2Service') as GoogleOAuth2Service
        try {
            oAuth2BaseService.registerProvider(googleOAuth2Service)
        } catch (OAuth2Exception exception) {
            log.error("There was an oAuth2Exception", exception)
            log.error("OAuth2 Google not loaded")
        }
    }

    /*def registerOktaProvider(){
        def SpringSecurityOauth2BaseService oAuth2BaseService = grails.util.Holders.grailsApplication.mainContext.getBean('springSecurityOauth2BaseService') as SpringSecurityOauth2BaseService
        def OktaOAuth2Service oktaOAuth2Service = grails.util.Holders.grailsApplication.mainContext.getBean('oktaOAuth2Service') as OktaOAuth2Service
        try {
            oAuth2BaseService.registerProvider(oktaOAuth2Service)
        } catch (OAuth2Exception exception) {
            log.error("There was an oAuth2Exception", exception)
            log.error("OAuth2 Google not loaded")
        }
    }*/
}
