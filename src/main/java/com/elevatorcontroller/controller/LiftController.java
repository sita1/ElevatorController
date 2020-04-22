package com.elevatorcontroller.controller;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import com.elevatorcontroller.model.LiftModel;
import com.elevatorcontroller.service.LiftService;

public class LiftController {
	
	@Inject
	LiftService liftService;
	
	public String capacityCheckonWhenLiftStop(Integer personEntering,Integer elevatorId,Integer liftId, Set<LiftModel> lifts)
	{
		Boolean message = liftService.capacityCheckonWhenLiftStop(personEntering,elevatorId,liftId,lifts);
		
		if(message==false)
		return "overload";
		
		return "ok";
	}
	
	public void pressFloor(Integer liftId, List<Integer> pressedFloor,Set<LiftModel> lifts)
	{
		liftService.pressFloor(liftId,pressedFloor,lifts);
	}
	
	public void removeFloorFromLift(Integer liftId, Integer floorToRemove)
	{
		liftService.removeFloorFromLift(liftId,floorToRemove);
	}
	
	public void goUp(Integer liftId)
	{
		liftService.goUp(liftId);
	}
	
	public void goDown(Integer liftId)
	{
		liftService.goDown(liftId);
	}
	
	public void removePersonFromLift(Integer liftId, Integer personRemoved)
	{
		liftService.removePersonFromLift(liftId,personRemoved);
	}
}
