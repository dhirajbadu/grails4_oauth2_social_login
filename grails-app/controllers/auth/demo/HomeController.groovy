package auth.demo

import com.github.scribejava.core.model.OAuth2AccessToken
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class HomeController {

    SpringSecurityService springSecurityService

    @Secured("permitAll")
    def index() {
        if(springSecurityService?.isLoggedIn()){
            redirect(action:'googleUser')
            return
        }else{
            render(view: '/index')
        }
    }

    @Secured("ROLE_USER")
    def googleUser() {
        def principle = springSecurityService.principal
        println(principle)
        println springSecurityService?.isLoggedIn()
        println springSecurityService.getAuthentication()?.authorities
        render "hello ${principle.username} : you are google user"
    }

    @Secured("ROLE_USERS")
    def oktaUser() {
        def principle = springSecurityService.principal
        println(principle)
        println springSecurityService?.isLoggedIn()
        println springSecurityService.getAuthentication()?.authorities
        render "hello ${principle.username} : you are okta user"
    }
}
