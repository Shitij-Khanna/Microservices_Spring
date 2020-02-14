package com.microservice.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.microservice.apigateway.config.JwtConfig;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    
    private AuthorizationFilter authorizationFilter;
    
    @Autowired
  	private JwtConfig jwtConfig;

    @Autowired
    public WebSecurity(Environment environment, AuthorizationFilter authorizationFilter) {
        this.environment = environment;
        this.authorizationFilter = authorizationFilter;
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.csrf().disable(); 
    	http.headers().frameOptions().disable();
    	http.authorizeRequests()
    	.antMatchers(HttpMethod.POST, environment.getProperty("api.registration.url.path")).permitAll()
    	.antMatchers(HttpMethod.POST, environment.getProperty("api.login.url.path")).permitAll().
    	antMatchers(HttpMethod.GET, environment.getProperty("api.users.status.check.path")).permitAll()
    	.anyRequest().authenticated()
    	.and()
    	.addFilter(new AuthorizationFilter(authenticationManager(), environment));
    	
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    	http.addFilterBefore(authorizationFilter, BasicAuthenticationFilter.class);
    }	
	
}
