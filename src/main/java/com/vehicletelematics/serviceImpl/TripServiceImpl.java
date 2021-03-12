package com.vehicletelematics.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicletelematics.model.Trip;
import com.vehicletelematics.repository.TripRepository;
import com.vehicletelematics.service.TripService;

@Service
public class TripServiceImpl implements TripService {
	
	@Autowired
	TripRepository tripRepository;

	@Override
	public Trip addTrip(Trip trip) {
		return tripRepository.save(trip);
	}

	@Override
	public Trip editTrip(Trip trip) {
		Trip t = tripRepository.findById(trip.getId());
		t.setEndLat(trip.getEndLat());
		t.setEndLng(trip.getEndLng());
		return tripRepository.save(t);
	}

}
