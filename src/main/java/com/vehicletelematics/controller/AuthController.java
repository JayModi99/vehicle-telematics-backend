package com.vehicletelematics.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.auth.request.AuthRequest;
import com.vehicletelematics.model.StringResponse;
import com.vehicletelematics.model.User;
import com.vehicletelematics.service.SubUserService;
import com.vehicletelematics.service.UserService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	SubUserService subUserService;
	
	private final UserService userService;

	public AuthController(final UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value = "signup")
	public ResponseEntity<StringResponse> signUp(@Valid @RequestBody AuthRequest authRequest) {
		if(subUserService.existsByEmail(authRequest.getEmail())) {
			StringResponse response = new StringResponse("Email already Registered");
			return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
		}
		User u = new User();
		u.setEmail(authRequest.getEmail());
		u.setPassword(encoder.encode(authRequest.getPassword()));
		u.setProfileSet("false");
		try {
			userService.save(u);
			StringResponse response = new StringResponse("User Registered Successfully");
			return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
		}
		catch (Exception e) {
			System.out.println(e);
			StringResponse response = new StringResponse("Failed to Register");
			return new ResponseEntity<StringResponse>(response, HttpStatus.OK);
		}
	}
	
	@PostMapping("signin")
	public ResponseEntity<User> signIn(@Valid @RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = userService.findByEmail(authRequest.getEmail());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
