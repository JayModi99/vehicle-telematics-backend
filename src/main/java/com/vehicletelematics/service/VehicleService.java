package com.vehicletelematics.service;

import java.util.List;

import com.vehicletelematics.model.Vehicle;

public interface VehicleService {
	
	public Vehicle findByVehicleNumberAndUserId(String vehicleNumber, long userId);
	
	public List<Vehicle> addVehicles(List<Vehicle> vehicleList);
	
	public List<Vehicle> findAllVehicles(long userId);
	
	public boolean deleteVehicle(long id);

}
