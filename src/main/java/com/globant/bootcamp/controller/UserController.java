package com.globant.bootcamp.controller;

import com.globant.bootcamp.model.User;
import com.globant.bootcamp.service.UserService;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping( method= RequestMethod.POST, produces= "application/json" )
	public String add ( @RequestParam String email, @RequestParam String name, @RequestParam String username, @RequestParam String password){
		return this.userService.register(email, name, username, password);
	}
	@RequestMapping(method= RequestMethod.DELETE , produces= "application/json" )
	public void delete (@RequestParam Long id){
		this.userService.deleteById(id);
	}
	@RequestMapping(value = "/{id}", method= RequestMethod.PUT , produces= "application/json" )
	public User updateById (@PathVariable Long id, @RequestParam String email, @RequestParam String name, @RequestParam String username, @RequestParam String password){
		this.userService.updateById(id,email,name, username, password);
		return this.userService.getById(id);
	}
	@RequestMapping(value = "/name/{name}",method= RequestMethod.GET , produces= "application/json" )
	public User findByName (@PathVariable String name){
		return this.userService.getByName(name);
	}
	@RequestMapping(value = "/{username}",method= RequestMethod.GET , produces= "application/json" )
	public User findByUsername (@PathVariable("username") String userName){
		User u = this.userService.getByUsername(userName);
		return u;
	}

}
