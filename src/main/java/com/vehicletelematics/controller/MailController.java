package com.vehicletelematics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.service.MailService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@GetMapping("send-mail/{email}/{userId}")
	public boolean send(@PathVariable String email, @PathVariable long userId) {
		return mailService.sendEmail(email, userId);
	}

}
