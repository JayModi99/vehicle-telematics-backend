package com.vehicletelematics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicletelematics.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	
	public Trip findById(long id);
	
	public List<Trip> findByUserIdOrderByIdAsc(long userId);

}
