package com.auth.model

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class CustomGrailsUser extends GrailsUser {

    private String providerId

    CustomGrailsUser(String username, String password, boolean enabled,
                     boolean accountNonExpired, boolean credentialsNonExpired,
                     boolean accountNonLocked,
                     Collection<GrantedAuthority> authorities,
                     long id, String providerId) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities, id);
        this.providerId = providerId?:"System"
    }

}
