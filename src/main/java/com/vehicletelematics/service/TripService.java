package com.vehicletelematics.service;

import java.util.List;

import com.vehicletelematics.model.Trip;

public interface TripService {
	
	public Trip addTrip(Trip trip);
	
	public Trip editTrip(Trip trip);
	
	public List<Trip> getAllTrip(long userId);

}
