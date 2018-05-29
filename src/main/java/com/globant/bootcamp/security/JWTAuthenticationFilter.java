package com.globant.bootcamp.security;

import com.globant.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter that is added to the security filter chain to
 * validates the token and loads the user details associated with that token
 */
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired private JWTTokenProvider tokenProvider;

	@Autowired private UserService userService;

	/**
	 * The filter itself:
	 * - Validate token
	 * - Load user details and set it into the Security context
	 *
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);

			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				Long userId = tokenProvider.getUserIdFromJWT(jwt);

				UserDetails userDetails = userService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			System.err.println("Could not set user authentication in security context");
		}

		filterChain.doFilter(request, response);
	}

	/**
	 * This method get the JWT token from the request
	 *
	 * @param request
	 * @return
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String jwt = null;
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			jwt = bearerToken.substring(7, bearerToken.length());
		}
		return jwt;
	}

}
