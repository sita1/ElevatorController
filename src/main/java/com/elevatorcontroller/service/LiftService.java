package com.elevatorcontroller.service;

import java.util.List;
import java.util.Set;

import com.elevatorcontroller.model.Direction;
import com.elevatorcontroller.model.LiftModel;

public interface LiftService {

	// one elevator connected with 2 lift
	LiftModel getLiftByElevatorIdAndDirection(Integer pressedFloor, Integer elevatorId, Direction direction);

	Boolean capacityCheckonWhenLiftStop(Integer personEntering, Integer elevatorId, Integer liftId);

	void pressFloor(Integer liftId, Integer pressedFloor);

	void removeFloorFromLift(Integer liftId, Integer floorToRemove);

	void goUp(Integer liftId);

	Set<LiftModel> initializeLiftsWithElevators(Set<Integer> elevatorIds);

	void goDown(Integer liftId);

	

}
