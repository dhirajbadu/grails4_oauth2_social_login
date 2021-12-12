package com.auth.provider.okta

import com.auth.model.CustomGrailsUser
import com.github.scribejava.apis.GoogleApi20
import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.converters.JSON
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.util.Holders
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class OktaOAuth2Service extends OAuth2AbstractProviderService{


    @Override
    String getProviderID() {
        return 'okta'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        OktaApi20.class
    }

    @Override
    String getProfileScope() {
        return Holders.config.grails.plugin.springsecurity.oauth2.providers.okta.userInfoUrl
    }

    @Override
    String getScopes() {
        return 'openid profile email'
    }

    @Override
    String getScopeSeparator() {
        return " "
    }

    @Override
    OAuth2SpringToken createSpringAuthToken(OAuth2AccessToken accessToken) {
        def user
        def response = getResponse(accessToken)
        try {
            println("JSON response body: " + accessToken.rawResponse)
            user = JSON.parse(response.body)
        } catch (Exception exception) {
            println("Error parsing response from " + getProviderID() + ". Response:\n" + response.body)
            throw new OAuth2Exception("Error parsing response from " + getProviderID(), exception)
        }
        if (!user?.email) {
            println("No user email from " + getProviderID() + ". Response was:\n" + response.body)
            throw new OAuth2Exception("No user id from " + getProviderID())
        }
        List<GrantedAuthority> authorities= new ArrayList<>()
        for(String role: user.groups){
            println("rolessss L : " + role)
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
        }
        def principal = new CustomGrailsUser(user?.email, user?.email+"@123RocK", true, true, true, true,authorities , 0 , "okta")

        new OktaOauth2SpringToken(accessToken, user?.email, providerID , principal, authorities)
    }
}
