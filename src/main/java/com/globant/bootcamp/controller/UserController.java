package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

	//@Autowired private UserService userService;

	//public UserController(UserService userService) {
	//	this.userService = userService;
	//}

	@RequestMapping( value = "/register" ,method= RequestMethod.POST, produces= "application/json" )
	public void register ( @RequestParam String email, @RequestParam String username, @RequestParam String password){
		//return this.userService.register(email, username, password);
	}

	@RequestMapping(value = "/login",method= RequestMethod.POST , produces= "application/json" )
	public void login (@RequestParam String username, @RequestParam String password){
		//
	}

	@RequestMapping(value = "/logout",method= RequestMethod.GET , produces= "application/json" )
	public void logout (){
		//
	}

}
