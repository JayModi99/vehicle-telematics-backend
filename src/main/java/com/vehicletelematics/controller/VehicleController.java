package com.vehicletelematics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.model.Vehicle;
import com.vehicletelematics.service.VehicleService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/vehicle")
public class VehicleController {
	
	@Autowired
	VehicleService vehicleService;
	
	@GetMapping("findByVehicleNumber/{vehicleNumber}/{userId}")
	public Vehicle findByVehicleNumber(@PathVariable String vehicleNumber, @PathVariable long userId) {
		return vehicleService.findByVehicleNumberAndUserId(vehicleNumber, userId);
	}
	
	@PostMapping("addVehicles")
	public List<Vehicle> addVehicles(@RequestBody List<Vehicle> vehicleList){
		return vehicleService.addVehicles(vehicleList);
	}
	
	@GetMapping("findAllVehicles/{userId}")
	public List<Vehicle> findAllVehicles(@PathVariable long userId){
		return vehicleService.findAllVehicles(userId);
	}
	
	@DeleteMapping("deleteVehicle/{vehicleId}")
	public boolean deleteVehicle(@PathVariable long vehicleId) {
		if(vehicleService.deleteVehicle(vehicleId)) {
			return true;
		}
		else {
			return false;
		}
	}

}
