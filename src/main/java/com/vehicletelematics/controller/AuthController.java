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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.auth.request.AuthRequest;
import com.vehicletelematics.model.User;
import com.vehicletelematics.service.UserService;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	private final UserService userService;

	public AuthController(final UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@Valid @RequestBody User authRequest) {
		if(userService.existsByEmail(authRequest.getEmail())) {
			return new ResponseEntity<String>("Email already Registered", HttpStatus.OK);
		}
		User u = new User();
		u.setEmail(authRequest.getEmail());
		u.setPassword(encoder.encode(authRequest.getPassword()));
		User user = userService.save(u);
		if(user == null) {
			return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
		}
		else {
			return new ResponseEntity<String>("User Registered Successfully", HttpStatus.OK);
		}
	}
	
	@PostMapping("/signin")
	public User signIn(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = userService.findByEmail(authRequest.getEmail());
		return user;
	}

}
