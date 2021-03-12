package com.vehicletelematics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicletelematics.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
	
	public Trip findById(long id);

}
