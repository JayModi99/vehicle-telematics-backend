package com.vehicletelematics.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vehicletelematics.model.User;
import com.vehicletelematics.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
     
	@Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
         
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
         
        return new MyUserDetails(user);
    }

}
