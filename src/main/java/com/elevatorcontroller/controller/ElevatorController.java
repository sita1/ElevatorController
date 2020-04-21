package com.elevatorcontroller.controller;

import java.util.List;

import javax.inject.Inject;

import com.elevatorcontroller.model.LiftModel;
import com.elevatorcontroller.service.LiftService;

public class ElevatorController {
	
	@Inject
	LiftService liftService;
	
	
	public LiftModel getLiftByElevatorIdAndDirection(Integer pressedFloor, Integer elevatorId,String direction) {
		LiftModel liftModel = liftService.getLiftByElevatorIdAndDirection(pressedFloor,elevatorId,direction);
		
		return liftModel;
		
	}
	
	
	public void initializeLiftsWithElevators(List<Integer> elevatorIds) {
		LiftModel liftModel = liftService.initializeLiftsWithElevators(elevatorIds);
		
		
	}

}
