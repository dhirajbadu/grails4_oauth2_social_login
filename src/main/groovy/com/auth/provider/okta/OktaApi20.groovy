package com.auth.provider.okta

import com.github.scribejava.apis.GoogleApi20
import com.github.scribejava.apis.openid.OpenIdJsonTokenExtractor
import com.github.scribejava.core.builder.api.DefaultApi20
import com.github.scribejava.core.extractors.TokenExtractor
import com.github.scribejava.core.model.OAuth2AccessToken
import grails.util.Holders

class OktaApi20 extends DefaultApi20 {

    protected OktaApi20() {
    }

    private static class InstanceHolder {
        private static final OktaApi20 INSTANCE = new OktaApi20();
    }

    public static OktaApi20 instance() {
        return OktaApi20.InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return Holders.config.grails.plugin.springsecurity.oauth2.providers.okta.tokenUrl
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return Holders.config.grails.plugin.springsecurity.oauth2.providers.okta.authorizeUrl
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return OpenIdJsonTokenExtractor.instance();
    }

    @Override
    public String getRevokeTokenEndpoint() {
        return Holders.config.grails.plugin.springsecurity.oauth2.providers.okta.revokeUrl
    }
}
