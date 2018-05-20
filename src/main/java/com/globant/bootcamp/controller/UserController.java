package com.globant.bootcamp.controller;


import com.globant.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController public class UserController {

	@Autowired private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain") public String register(
			@RequestParam String email, @RequestParam String username, @RequestParam String password) {
		return this.userService.register(email,username,password);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json") public void login(
			@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("token") == null){
			response.setStatus(HttpStatus.UNAUTHORIZED.value()); String token = this.userService.login(username, password);
			if (token != null) {
				request.getSession().setAttribute("token", token);
				response.setStatus(HttpStatus.ACCEPTED.value());
			}
		}else{
			response.setStatus(HttpStatus.CONFLICT.value());
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json") public void logout(@CookieValue("token") String token, HttpServletRequest request) {
		request.getSession().invalidate();
		this.userService.logout(token);
	}
}
