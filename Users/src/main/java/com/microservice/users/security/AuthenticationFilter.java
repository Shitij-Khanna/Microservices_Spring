package com.microservice.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.users.service.UsersService;
import com.microservice.users.shared.UserDto;
import com.microservice.users.ui.model.LoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UsersService usersService;
	private Environment environment;

	public AuthenticationFilter(UsersService usersService, Environment environment,
			AuthenticationManager authenticationManager) {
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}

	/**
	 * This method is invoked by spring when it is trying to authenticate a user.
	 * Then it calls the loadUserByUsername method in UsersService, if you have
	 * implemented the interface UserDetailsService
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);

			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is invoked by Spring after successful authentication. We can
	 * write the implementation for creating json web token here, and set its
	 * validity, which can later by used by the client app for authentication,
	 * instead of passing username/password with every request
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDto userDetails = usersService.getUserDetailsByEmail(userName);

		String token = Jwts.builder().setSubject(userDetails.getUserId())
				.setExpiration(new Date(
						System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();

		res.addHeader("token", token);
		res.addHeader("userId", userDetails.getUserId());
	}

}
