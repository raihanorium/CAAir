package com.caair.oauth

import com.caair.entity.AppUser
import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class SpringSecuritySigninService extends GormUserDetailsService {

    void signIn(AppUser user) {
        def authorities = loadAuthorities(user, user.username, true)
        def userDetails = createUserDetails(user, authorities)
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities))
    }
}