package com.vehicletelematics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.model.StringResponse;
import com.vehicletelematics.model.SubUser;
import com.vehicletelematics.service.SubUserService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/subUser")
public class SubUserController {
	
	@Autowired
	SubUserService subUserService;
	
	@GetMapping("existsByEmail/{email}")
	public boolean existsByEmail(@PathVariable String email) {
		return subUserService.existsByEmail(email);
	}
	
	@GetMapping("findAll/{userId}")
	public List<SubUser> findAll(@PathVariable long userId){
		return subUserService.findAllByUserId(userId);
	}
	
	@DeleteMapping("deleteSubUser/{id}")
	public boolean deleteById(@PathVariable long id) {
		return subUserService.deleteById(id);
	}
	
	@PostMapping("verifySubUserEmail")
	public ResponseEntity<StringResponse> verifySubUserEmail(@RequestBody SubUser subUser){	
		StringResponse response = new StringResponse(subUserService.verifySubUserEmail(subUser));
		return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
	}
	
	@PostMapping("subUserSignin")
	public SubUser subUserSignin(@RequestBody SubUser subUser){
		return subUserService.subUserSignin(subUser.getEmail(), subUser.getPassword());
	}

}
