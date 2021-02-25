package com.vehicletelematics.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicletelematics.model.SubUser;
import com.vehicletelematics.repository.SubUserRepository;
import com.vehicletelematics.repository.UserRepository;
import com.vehicletelematics.service.SubUserService;

@Service
public class SubUserServiceImpl implements SubUserService {
	
	@Autowired
	SubUserRepository subUserRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public boolean existsByEmail(String email) {
		if(userRepository.existsByEmail(email)) {
			return true;
		}
		else {
			if(subUserRepository.existsByEmail(email)) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public List<SubUser> findAllByUserId(long userId) {
		return subUserRepository.findAllByUserIdOrderByIdAsc(userId);
	}

	@Override
	public boolean deleteById(long id) {
		try{
			subUserRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public String verifySubUserEmail(SubUser sUser) {
		try {
			SubUser subUser = subUserRepository.findByEmail(sUser.getEmail());
			if(subUser.isHasVerified() == true) {
				return "Email already Registered";
			}
			else {
				subUser.setStatus(true);
				subUser.setHasVerified(true);
				subUser.setFirstName(sUser.getFirstName());
				subUser.setLastName(sUser.getLastName());
				subUser.setPassword(sUser.getPassword());
				subUserRepository.save(subUser);
				return "Registration Successful";
			}
		}catch (Exception e) {
			return "Failed to Register";
		}
	}

	@Override
	public SubUser subUserSignin(String email, String password) {
		try {
			return subUserRepository.findByEmailAndPassword(email, password);
		}catch (Exception e) {
			return null;
		}
	}

}
