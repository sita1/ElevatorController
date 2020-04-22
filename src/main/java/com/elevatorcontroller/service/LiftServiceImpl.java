package com.elevatorcontroller.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.elevatorcontroller.model.Direction;
import com.elevatorcontroller.model.LiftModel;

public class LiftServiceImpl implements LiftService {
	
	Set<LiftModel> liftList = new HashSet<LiftModel>();

	public LiftModel getLiftByElevatorIdAndDirection(Integer pressedFromFloor,  Integer elevatorId, Direction direction) {
		if(liftList.size()>0)
		{
		List<LiftModel> liftModelList = liftList.stream().filter(i-> i.getElevatorId().equals(elevatorId)).filter(i->i.getCurrentFloor()<pressedFromFloor).collect(Collectors.toList());
		
		if(liftModelList.stream().filter(i->i.getDirection().equals(Direction.NONE)).count()==2)
		{
			return liftModelList.get(0);
		}
		if(direction.equals(Direction.UP))
		{
		Long k = liftModelList.stream().filter(i->i.getDirection().equals(direction) && i.getCurrentFloor()<pressedFromFloor).count();
		
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
			Long k = liftModelList.stream().filter(i->i.getDirection().equals(direction) && i.getCurrentFloor()>pressedFromFloor).count();
			
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
	public Boolean capacityCheckonWhenLiftStop(Integer personEntering, Integer elevatorId, Integer liftId,Set<LiftModel> lifts) {
		liftList.addAll(lifts);
		
	List<LiftModel> liftModel =  liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
	System.out.println("hi"+liftModel.size());
	
	System.out.println("hiyyyy"+liftList.size());
	
	if(liftModel!=null && liftModel.size()>0)
	{
	liftModel.get(0).setCurrentCapacityAdded(liftModel.get(0).getCurrentCapacityAdded()+personEntering);
	if((liftModel.get(0).getCurrentCapacityAdded())<liftModel.get(0).getMaxCapacity())
		return true;;
		
		
		 
		
	}
	return false;
	}
	

	@Override
	public void pressFloor(Integer liftId, List<Integer> pressedFloors, Set<LiftModel> lifts) {
		liftList.addAll(lifts);
		LiftModel liftModel = (LiftModel) liftList.stream().filter(i-> i.getId().equals(liftId)).collect(Collectors.toList());
		
		List<Integer> floorsAdded = liftModel.getFloorsAdded();
	
		floorsAdded.addAll(pressedFloors);
		Collections.sort(floorsAdded);
		liftModel.setFloorsAdded(floorsAdded);
		liftList.add(liftModel);
	}
	
	
	public void inputEveryFiveSecond() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if(liftList!=null && liftList.size()>0)
					
				{
					for(LiftModel liftModel : liftList)
					{
						if(liftModel.getDirection().equals(Direction.UP))
								{
							goUp(liftModel.getId());
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
								}
						else if(liftModel.getDirection().equals(Direction.DOWN))
						{
					goDown(liftModel.getId());
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						}
				
						}
				}
			}
		}, 0, 5000);

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
	public Set<LiftModel> initializeLiftsWithElevators(Set<Integer> elevatorIds) {

		
		elevatorIds.forEach(id->
				{
		addLiftForElevator( id);
		addLiftForElevator( id);
		
				}
				);	
			
		return liftList;
	}

	private void addLiftForElevator(Integer id) {
		LiftModel liftModel = new LiftModel();
		if(liftList.size()>0)
		{
			Comparator<LiftModel> comparator = Comparator.comparing(LiftModel::getId);
			LiftModel maxLiftId = liftList.stream().max(comparator).get();
			liftModel.setId(maxLiftId.getId()+1);
		}
		else
			liftModel.setId(1);
		
		liftModel.setCurrentCapacityAdded(0);
		liftModel.setCurrentFloor(0);
		liftModel.setDirection(Direction.NONE);
		liftModel.setElevatorId(id);
		liftModel.setMaxCapacity(10);
		liftList.add(liftModel);
	}

	@Override
	public void removePersonFromLift(Integer liftId, Integer personRemoved) {

		LiftModel liftModel = (LiftModel) liftList.stream().filter(i -> i.getId().equals(liftId))
				.collect(Collectors.toList());
		if (personRemoved > 0) {
			liftModel.setCurrentCapacityAdded(liftModel.getCurrentCapacityAdded() - personRemoved);

			liftList.add(liftModel);
		}

	}
	





}
