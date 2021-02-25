package com.vehicletelematics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.model.User;
import com.vehicletelematics.service.UserService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("saveUserProfile")
	public User saveUserProfile(@RequestBody User user) {
		User u = userService.findById(user.getId());
		u.setCompany(user.getCompany());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setMobileNumber(user.getMobileNumber());
		u.setProfileSet(user.getProfileSet());
		return userService.save(u);
	}
	
	@GetMapping("isProfileSet/{userId}")
	public Boolean isProfileSet(@PathVariable long userId) {
		User user = userService.findById(userId);
		if(user.getProfileSet().equals("true")) {
			return true;
		}
		else {
			return false;
		}
	}

}
