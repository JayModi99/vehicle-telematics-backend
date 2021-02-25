package com.vehicletelematics.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vehicletelematics.model.SubUser;
import com.vehicletelematics.model.User;
import com.vehicletelematics.repository.SubUserRepository;
import com.vehicletelematics.repository.UserRepository;
import com.vehicletelematics.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Autowired
	SubUserRepository subUserRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean sendEmail(String email, long userId) {
		
		SubUser subUser = new SubUser();
		
		subUser.setUserId(userId);
		subUser.setEmail(email);
		subUser.setHasVerified(false);
		
		try{			
			User user = userRepository.findById(userId);
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email);
			mail.setSubject("Invitation Link for Telewix Sub-User");
			mail.setText("Hello," + "\n\n" + user.getFirstName() + " " + user.getLastName() + " invited to join Telewix as sub-user." + "\n\n" + 
			"Click the below link to accept invitation and register for Telewix." + "\n" + "http://localhost:4200/sub-user-register" + "\n\n" + 
					"Ignore this email if you don't want to accept invitaion." + "\n\n" + "Thanks & Regards," + "\n\n" + "Telewix Team");
			javaMailSender.send(mail);
			
			subUserRepository.save(subUser);
			return true;
		}
		catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}

}
