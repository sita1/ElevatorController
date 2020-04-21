package com.elevatorcontroller.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.elevatorcontroller.model.Direction;
import com.elevatorcontroller.model.Elevator;
import com.elevatorcontroller.model.LiftModel;
import com.elevatorcontroller.service.LiftService;

public class ElevatorController {
	
	@Inject
	LiftService liftService;
	
	Set<Elevator> elevators = new HashSet<Elevator>();
	Set<Integer> elevatorIds = new HashSet<Integer>();
	
	
	public LiftModel getLiftByElevatorIdAndDirection(Integer pressedFloor, Integer elevatorId,Direction up) {
		LiftModel liftModel = liftService.getLiftByElevatorIdAndDirection(pressedFloor,elevatorId,up);
		
		return liftModel;
		
	}
	
	
	public Set<Integer> initializeElevators(int n) {
	
		
		/// add n elevator
		Elevator elevator = new Elevator();
		for(int i=0 ; i<n;i++)
		{
		elevator.setId(i);
		elevator.setElevatorName(elevator.getId().toString());
		elevators.add(elevator);
		elevatorIds.add(elevator.getId());
		}
		return elevatorIds;
		
	}
	
	public Set<LiftModel> initializeLiftsWithElevators(Set<Integer> elevatorIds) {
		return liftService.initializeLiftsWithElevators(elevatorIds);
		
		
	}

}
