package com.auth.provider.okta

import com.github.scribejava.core.model.OAuth2AccessToken
import grails.plugin.springsecurity.userdetails.GrailsUser

class OktaOauth2SpringToken extends grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken{

    private String email
    private String providerId

    OktaOauth2SpringToken(OAuth2AccessToken accessToken, String email, String providerId, def principle, def authority) {
        super(accessToken)
        this.email = email
        this.providerId = providerId
        this.principal = principle
        this.authorities = authority
        this.authenticated = true
    }

    @Override
    String getProviderName() {
        return providerId
    }

    @Override
    String getSocialId() {
        return email
    }

    @Override
    String getScreenName() {
        return email
    }
}
