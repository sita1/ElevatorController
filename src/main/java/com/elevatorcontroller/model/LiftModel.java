package com.elevatorcontroller.model;

import java.util.ArrayList;
import java.util.List;

public class LiftModel {
	
	private Integer id;
	
	private Integer elevatorId;
	
	private Integer currentFloor;
	
	private Integer maxCapacity;
	
	private Integer currentCapacityAdded;
	
	private Direction direction;
	
	private List<Integer> floorsAdded= new ArrayList<Integer>();;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(Integer elevatorId) {
		this.elevatorId = elevatorId;
	}

	public Integer getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(Integer currentFloor) {
		this.currentFloor = currentFloor;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Integer> getFloorsAdded() {
		return floorsAdded;
	}

	public void setFloorsAdded(List<Integer> floorsAdded) {
		this.floorsAdded = floorsAdded;
	}

	public Integer getCurrentCapacityAdded() {
		return currentCapacityAdded;
	}

	public void setCurrentCapacityAdded(Integer currentCapacityAdded) {
		this.currentCapacityAdded = currentCapacityAdded;
	}

	@Override
	public String toString() {
		return "LiftModel [id=" + id + ", elevatorId=" + elevatorId + ", currentFloor=" + currentFloor
				+ ", maxCapacity=" + maxCapacity + ", currentCapacityAdded=" + currentCapacityAdded + ", direction="
				+ direction + ", floorsAdded=" + floorsAdded + "]";
	}
	
	

}
