package com.elevatorcontroller.service;

import java.util.List;

import com.elevatorcontroller.model.LiftModel;

public interface LiftService {

	// one elevator connected with 2 lift
	LiftModel getLiftByElevatorIdAndDirection(Integer pressedFloor, Integer elevatorId, String direction);

	Boolean capacityCheckonWhenLiftStop(Integer personEntering, Integer elevatorId, Integer liftId);

	void pressFloor(Integer liftId, Integer pressedFloor);

	void removeFloorFromLift(Integer liftId, Integer floorToRemove);

	void goUp(Integer liftId);

	LiftModel initializeLiftsWithElevators(List<Integer> elevatorIds);

	void goDown(Integer liftId);
	

}
