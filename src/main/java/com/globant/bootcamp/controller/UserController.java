package com.globant.bootcamp.controller;


import com.globant.bootcamp.exception.ExistingEmailException;
import com.globant.bootcamp.exception.ExistingUsernameException;
import com.globant.bootcamp.payload.ApiResponse;
import com.globant.bootcamp.payload.JWTAuthenticationResponse;
import com.globant.bootcamp.payload.LoginRequest;
import com.globant.bootcamp.payload.RegisterRequest;
import com.globant.bootcamp.security.JWTTokenProvider;
import com.globant.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired UserService userService;


	@Autowired JWTTokenProvider tokenProvider;



	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity registerUser( @RequestBody RegisterRequest request) {

		ResponseEntity response = new ResponseEntity(new ApiResponse(true, "User registered successfully"),HttpStatus.ACCEPTED);

		try {
			this.userService.register(request.getEmail(), request.getUsername(), request.getPassword());
		} catch (ExistingEmailException e) {
			response = new ResponseEntity(new ApiResponse(false,"Email already in use"),HttpStatus.BAD_REQUEST);
		} catch (ExistingUsernameException e) {
			response = new ResponseEntity(new ApiResponse(false,"Username already in use"),HttpStatus.BAD_REQUEST);
		}finally {
			return response;
		}
	}
}
