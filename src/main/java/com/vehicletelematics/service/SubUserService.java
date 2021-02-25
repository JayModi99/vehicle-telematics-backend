package com.vehicletelematics.service;

import java.util.List;

import com.vehicletelematics.model.SubUser;

public interface SubUserService {
	
	public boolean existsByEmail(String email);
	
	public List<SubUser> findAllByUserId(long userId);
	
	public boolean deleteById(long id);
	
	public String verifySubUserEmail(SubUser subUser);
	
	public SubUser subUserSignin(String email, String password);

}
