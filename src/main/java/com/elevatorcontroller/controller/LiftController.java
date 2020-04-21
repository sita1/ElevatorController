package com.elevatorcontroller.controller;

import javax.inject.Inject;

import com.elevatorcontroller.service.LiftService;

public class LiftController {
	
	@Inject
	LiftService liftService;
	
	public String capacityCheckonWhenLiftStop(Integer personEntering,Integer elevatorId,Integer liftId)
	{
		Boolean message = liftService.capacityCheckonWhenLiftStop(personEntering,elevatorId,liftId);
		
		if(message==false)
		return "overload";
		
		return "ok";
	}
	
	public void pressFloor(Integer liftId, Integer pressedFloor)
	{
		liftService.pressFloor(liftId,pressedFloor);
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
}
