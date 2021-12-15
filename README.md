# Spring security oauth2 google okta login
# grails4 oauth2 social login
# grails4 oauth2 google login
# grails4 oauth2 okta login
# grails4 oauth2 google authentication
# changes
<ul>
<li><a href="https://github.com/dhirajbadu/grails4_oauth2_social_login/tree/main/src/main/groovy/com/auth/provider">Provider</a></li>
<li>Config : <a href="https://github.com/dhirajbadu/grails4_oauth2_social_login/blob/main/grails-app/conf/application.yml">application.yml</a> <a href="https://github.com/dhirajbadu/grails4_oauth2_social_login/blob/main/grails-app/conf/spring/resources.groovy">resources.groovy</a></li>
<li><a href="https://github.com/dhirajbadu/grails4_oauth2_social_login/blob/main/grails-app/init/auth/demo/BootStrap.groovy"> Register provider</a></li>
  <li>Dependencies : compile 'org.grails.plugins:spring-security-core:4.0.3' and compile 'org.grails.plugins:spring-security-oauth2:1.3.0.BUILD-SNAPSHOT'</li>
  </ul>
  
  # Need to change the configs In Application.yml
                  google:
                        api_key: 'you api key'
                        api_secret: 'your api secret'
                        successUri: '/oauth2/google/success'
                        failureUri: '/oauth2/google/failure'
                        callback: '/oauth2/google/callback'
                        scopes: 'https://www.googleapis.com/auth/userinfo.email'
                    okta:
                        api_key: 'your api key'
                        api_secret: 'your api secret'
                        userInfoUrl: '{your okta url}/oauth2/default/v1/userinfo'
                        authorizeUrl: '{your okta url}/oauth2/default/v1/authorize'
                        tokenUrl: '{your okta url}/oauth2/default/v1/token'
                        scopes: 'email profile openid groups'
                        successUri: '/oauth2/okta/success'
                        failureUri: '/oauth2/okta/failure'
                        callback: '/oauth2/okta/callback'
                        revokeUrl: '{your okta url}/oauth2/default/v1/revoke'

