package com.globant.bootcamp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This class is used to return a
 * 401 unauthorized error to clients that
 * try to access a protected resource without proper authentication.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest,	HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException {
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"You shall not pass!");
	}

}
