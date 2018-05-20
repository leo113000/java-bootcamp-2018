package com.globant.bootcamp.controller;


import com.globant.bootcamp.service.UserService;
import com.globant.bootcamp.util.StringResponse;
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

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json") public StringResponse register(
			@RequestParam String email, @RequestParam String username, @RequestParam String password) {
		return new StringResponse(this.userService.register(email,username,password));
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json") public StringResponse login(
			@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
		String result="Login failed";
		if (request.getSession().getAttribute("token") == null){
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			String token = this.userService.login(username, password);
			if (token != null) {
				request.getSession().setAttribute("token", token);
				response.setStatus(HttpStatus.ACCEPTED.value());
				result = "Login success!";
			}
		}else{
			response.setStatus(HttpStatus.CONFLICT.value());
			result = "You're already logged";
		}
		return new StringResponse(result);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json") private StringResponse logout(@SessionAttribute(value = "token",required = false) String token, HttpServletRequest request) {
		StringResponse response = null;
		if(token != null){
			request.getSession().removeAttribute("token");
			this.userService.logout(token);
			response = new StringResponse("Logout successful");
		}
		return response;
	}
}
