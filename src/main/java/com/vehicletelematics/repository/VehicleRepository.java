package com.vehicletelematics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vehicletelematics.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	public Vehicle findByVehicleNumberAndUserId(String vehicleNumber, long userId);
	
	public List<Vehicle> findAllByUserId(long userId);

}
