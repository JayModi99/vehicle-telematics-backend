package com.vehicletelematics.service;

import com.vehicletelematics.model.User;

public interface UserService {
	
	public boolean existsByEmail(String email);
	
	public User findById(long id);
	
	public User save(User user);
	
	public User findByEmail(String email);
	
	public User findByEmailAndPassword(String email, String password);

}
