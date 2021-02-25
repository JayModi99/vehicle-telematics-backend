package com.vehicletelematics.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicletelematics.model.Vehicle;
import com.vehicletelematics.repository.VehicleRepository;
import com.vehicletelematics.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {
	
	@Autowired
	VehicleRepository vehicleRepository;

	@Override
	public Vehicle findByVehicleNumberAndUserId(String vehicleNumber, long userId) {
		return vehicleRepository.findByVehicleNumberAndUserId(vehicleNumber, userId);
	}

	@Override
	public List<Vehicle> addVehicles(List<Vehicle> vehicleList) {
		return vehicleRepository.saveAll(vehicleList);
	}

	@Override
	public List<Vehicle> findAllVehicles(long userId) {
		return vehicleRepository.findAllByUserId(userId);
	}

	@Override
	public boolean deleteVehicle(long id) {
		try {
			vehicleRepository.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
