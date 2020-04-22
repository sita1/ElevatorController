package com.elevatorcontroller.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.inject.Inject;

import com.elevatorcontroller.controller.ElevatorController;
import com.elevatorcontroller.controller.LiftController;
import com.elevatorcontroller.model.Direction;
import com.elevatorcontroller.model.LiftModel;
import com.elevatorcontroller.service.LiftService;

public class StartElevator {
	
	@Inject
	ElevatorController elevatorController;
	
	@Inject
	LiftController liftController;
	
	public void startElevators()
	{
		Scanner sc =  new Scanner(System.in);
		System.out.println("Enter How Many Elevator You Need");
		int n = sc.nextInt();
		Set<Integer> elevatorIds=elevatorController.initializeElevators(n);
		
		System.out.println(elevatorIds);
		// add lift to elevator making 2 lifts to each elevator
		System.out.println("Adding elevator to Lifts");
		Set<LiftModel> lifts = elevatorController.initializeLiftsWithElevators(elevatorIds);
		lifts.forEach(i->System.out.println(i));
		
		LiftModel liftModel = elevatorController.getLiftByElevatorIdAndDirection(4, 2, Direction.UP);
		System.out.print("lift coming :" +liftModel);
		String message ="overload";
		//till remove people maxcapacity
		while(message.equals("overload"))
		{
		 message = liftController.capacityCheckonWhenLiftStop(3, 2, liftModel.getId(),lifts);
		if(message.equals("overload"))
		{
			System.out.println("Machine Overloaded");
			liftController.removePersonFromLift(liftModel.getId(), 2);
			
			
		}
		}
		
		System.out.println("Press floor");
		List<Integer> inputs = new ArrayList<Integer>();
				
		String str;
		for(int i=0;i<n;i++)
		{
		    while(true)
		   {
		   str=sc.next();
		   if(!str.equalsIgnoreCase(""))
		      break;
		   int val=Integer.parseInt(str);
		   inputs.add(val);
		   }
		 }
		liftController.pressFloor( liftModel.getId(), inputs, lifts);
	}


}
