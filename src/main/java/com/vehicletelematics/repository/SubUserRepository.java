package com.vehicletelematics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicletelematics.model.SubUser;

@Repository
public interface SubUserRepository extends JpaRepository<SubUser, Long> {
	
	public boolean existsByEmail(String email);
	
	public List<SubUser> findAllByUserIdOrderByIdAsc(long userId);
	
	public SubUser findByEmail(String email);
	
	public SubUser findByEmailAndPassword(String email, String password);

}
