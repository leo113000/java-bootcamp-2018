package com.globant.bootcamp.security;

import com.globant.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Main class of Spring Security's configuration
 */
@Configuration @EnableWebSecurity @EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true) public class WebSecurity
		extends WebSecurityConfigurerAdapter {

	@Value("${route.login}") private String LOGIN_ROUTE;

	@Value("${route.register}") private String REGISTER_ROUTE;

	@Autowired UserService userService;

	@Autowired private JWTAuthenticationEntryPoint unauthorizedHandler;

	@Bean public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}

	@Override public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean(BeanIds.AUTHENTICATION_MANAGER) @Override public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/swagger-resources/**")
				.permitAll().antMatchers(REGISTER_ROUTE).permitAll().antMatchers(LOGIN_ROUTE).permitAll().antMatchers("/products/**")
				.permitAll().anyRequest().authenticated();

		// Add custom JWT security filter
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Override public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html",
				"/webjars/**");
	}
}
