package com.vehicletelematics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicletelematics.model.Trip;
import com.vehicletelematics.service.TripService;

@RestController
@CrossOrigin()
@RequestMapping("api/v1/trip")
public class TripController {
	
	@Autowired
	TripService tripService;
	
	@PostMapping("saveTrip")
	public Trip saveTrip(@RequestBody Trip trip) {
		return tripService.addTrip(trip);
	}
	
	@PostMapping("editTrip")
	public Trip editTrip(@RequestBody Trip trip) {
		return tripService.editTrip(trip);
	}
	
	@GetMapping("getAllTripByUserId/{userId}")
	public List<Trip> getAllTripByUserId(@PathVariable long userId) {
		return tripService.getAllTrip(userId);
	}

}
