package com.auth.provider.google

import com.auth.model.CustomGrailsUser
import com.github.scribejava.apis.GoogleApi20
import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.converters.JSON
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugin.springsecurity.oauth2.service.OAuth2AbstractProviderService
import grails.plugin.springsecurity.oauth2.token.OAuth2SpringToken
import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class GoogleOAuth2Service extends OAuth2AbstractProviderService{


    @Override
    String getProviderID() {
        return 'google'
    }

    @Override
    Class<? extends DefaultApi20> getApiClass() {
        GoogleApi20.class
    }

    @Override
    String getProfileScope() {
        return 'https://www.googleapis.com/oauth2/v2/userinfo'
    }

    @Override
    String getScopes() {
        return 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
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
        for(String role: ["USER" , "TEST"]){
            println("rolessss L : " + role)
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()));
        }
        
        // if you want to create a user in db then writer your code here
        // if you want to get user principal from spring security userDetailsService then uncomment the code below
        // def userDetailsService = grails.util.Holders.grailsApplication.mainContext.getBean("userDetailsService")
        // def principal  = userDetailsService.loadUserByUsername(user?.email)
        // return new GoogleOauth2SpringToken(accessToken, user.email, providerID , principal, principal.authorities)
        
        def principal = new CustomGrailsUser(user?.email, user?.email+"@123RocK", true, true, true, true,authorities , 0 , "google")

        new GoogleOauth2SpringToken(accessToken, user?.email, providerID , principal, authorities)
    }
}
