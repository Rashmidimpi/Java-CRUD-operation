package com.mb.demo.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mb.demo.business.service.UserService;
import com.mb.demo.persistance.entity.User;
import com.mb.demo.web.model.UserModel;

@RestController
@RequestMapping(path = "user")
	
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	public String saveUser(@RequestBody UserModel userModel) {
		
		return userService.saveUserInformation(userModel);
	}

	@PostMapping("/update")
	public String updateUser(@RequestBody UserModel userModel) {
		
		return userService.updateUserInformation(userModel);
	}
	
	@GetMapping("/get")
	public List<User> getUsers() {
		
		return userService.getUserInformation();
	}
	
	@DeleteMapping("/delete")
	public String deleteUsers(@RequestParam Integer id) {
		
		return userService.deleteUserInformation(id);
	}
	
	@PostMapping("/softdelete")
	public void softDeleteUsers(@RequestBody UserModel userModel) {
		
		userService.softDeleteUserInformation(userModel);
	}

}
