package com.elevatorcontroller.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.elevatorcontroller.model.Direction;
import com.elevatorcontroller.model.LiftModel;

public class LiftServiceImpl implements LiftService {
	
	Set<LiftModel> liftList = new HashSet<LiftModel>();

	public LiftModel getLiftByElevatorIdAndDirection(Integer pressedFloor,  Integer elevatorId, String direction) {
		if(liftList.size()>0)
		{
		List<LiftModel> liftModelList = liftList.stream().filter(i-> i.getElevatorId().equals(elevatorId)).filter(i->i.getCurrentFloor()<pressedFloor).collect(Collectors.toList());
		if(direction.equals(Direction.UP))
		{
		Long k = liftModelList.stream().filter(i->i.getDirection().equals(direction) && i.getCurrentFloor()<pressedFloor).count();
		
		if(k==liftModelList.size())
		{
			 Comparator<LiftModel> comparator = Comparator.comparing( LiftModel::getCurrentFloor );
		    
		    LiftModel maxCurrentfloor = liftModelList.stream().max(comparator).get();
		    return maxCurrentfloor;
		}
		else
		{
			
		}
		}
		else// for downward pressO
		{
			Long k = liftModelList.stream().filter(i->i.getDirection().equals(direction) && i.getCurrentFloor()>pressedFloor).count();
			
			if(k==liftModelList.size())
			{
				 Comparator<LiftModel> comparator = Comparator.comparing( LiftModel::getCurrentFloor );
			    
			    LiftModel minCurrentfloor = liftModelList.stream().min(comparator).get();
			    return minCurrentfloor;
			}
			else
			{
				
			}
		}
		}
		
		
		
		
		return null;
	}

	@Override
	public Boolean capacityCheckonWhenLiftStop(Integer personEntering, Integer elevatorId, Integer liftId) {
		
	LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
	if((liftModel.getCurrentCapacityAdded()+personEntering)<liftModel.getMaxCapacity())
		return false;
		
		
		
		return true;
	}

	@Override
	public void pressFloor(Integer liftId, Integer pressedFloor) {
		
		LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
		
		List<Integer> floorsAdded = liftModel.getFloorsAdded();
	
		floorsAdded.add(pressedFloor);
		Collections.sort(floorsAdded);
		liftModel.setFloorsAdded(floorsAdded);
		liftList.add(liftModel);
	}


	@Override
	public void removeFloorFromLift(Integer liftId, Integer floorToRemove) {
		
		LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
		
		List<Integer> floorsAdded = liftModel.getFloorsAdded();
		floorsAdded.removeAll(Arrays.asList(floorToRemove));
		
		liftModel.setFloorsAdded(floorsAdded);
		
		liftList.add(liftModel);
	
	}

	@Override
	public void goUp(Integer liftId) {
		LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
	     
        do
        {liftModel.setCurrentFloor(liftModel.getFloorsAdded().get(0));
        	System.out.println("Lift Going to floor : "+liftModel.getCurrentFloor());
        	
        	  removeVisitingFloor(liftModel.getFloorsAdded());
        	  try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }while(liftModel.getFloorsAdded().size()>0);
        

		if (liftModel.getFloorsAdded().size() > 0) {
			if (liftModel.getCurrentFloor() < liftModel.getFloorsAdded().get(0)) {
				liftModel.setDirection(Direction.UP);
				goUp(liftModel.getId());
			} else {
				liftModel.setDirection(Direction.DOWN);
				//goDown(liftModel.getId());
			}
		} else {
			if (liftModel != null)
				liftModel.setDirection(Direction.NONE);
		}	
	}

	private void removeVisitingFloor(List<Integer> list) {
		list.remove(0);
		
	}


	@Override
	public void goDown(Integer liftId) {
		LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
        do
        {
        	liftModel.setCurrentFloor(liftModel.getFloorsAdded().get(0));
        	  removeVisitingFloor(liftModel.getFloorsAdded());
        	  System.out.println("Lift Going down to floor :" + liftModel.getCurrentFloor());
        	  try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }while(liftModel.getFloorsAdded().size()>0);
        
        if (liftModel.getFloorsAdded().size()>0) {
        	liftModel.setDirection(Direction.UP); 
        	goUp(liftModel.getId());
        } else {
        	liftModel.setDirection(Direction.NONE); 
        }
		
	}
	
	@Override
	public LiftModel initializeLiftsWithElevators(List<Integer> elevatorIds) {
		// TODO Auto-generated method stub
		return null;
	}


}
