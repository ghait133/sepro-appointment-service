package com.sepro.appointmentservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
//@PropertySource("classpath:reddit.properties")
public class ResourceConfiguration {

    //@Value("${accessTokenUri}")
    private String accessTokenUri = "http://localhost:8072/oauth/token";

    //@Value("${userAuthorizationUri}")
    private String userAuthorizationUri = "http://localhost:8072/oauth/authorize";

    //@Value("${clientID}")
    private String clientID = "USER_CLIENT_APP";

    //@Value("${clientSecret}")
    private String clientSecret = "password";

    @Bean
    public OAuth2ProtectedResourceDetails reddit() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("reddit");
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.isClientOnly();
        return details;
    }

  /*  @Bean
    public OAuth2ProtectedResourceDetails trusted() {
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setGrantType("password");
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setUsername("admin");
        details.setPassword("password");
        details.isClientOnly();
        return details;
    }
*/
    @LoadBalanced
    @Bean
    public OAuth2RestTemplate redditRestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext clientContext) {
        /*ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        details.setClientId(clientID);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setGrantType("password");
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setUsername("admin");
        details.setPassword("password");
        details.isClientOnly();
        final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setClientId("USER_CLIENT_APP");
        resourceDetails.setClientSecret("password");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails
                .setAccessTokenUri("http://localhost:8072/oauth/token");
        resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);*/
        OAuth2RestTemplate template = new OAuth2RestTemplate(reddit(),clientContext);


        return template;
    }


}
