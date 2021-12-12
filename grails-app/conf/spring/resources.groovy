import com.auth.provider.okta.OktaOAuth2Service
import com.auth.provider.google.GoogleOAuth2Service

// Place your Spring DSL code here
beans = {
    googleOAuth2Service(GoogleOAuth2Service)
    oktaOAuth2Service(OktaOAuth2Service)


}
