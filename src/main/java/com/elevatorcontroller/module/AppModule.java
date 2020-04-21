package com.elevatorcontroller.module;

import com.elevatorcontroller.service.LiftService;
import com.elevatorcontroller.service.LiftServiceImpl;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LiftService.class).to(LiftServiceImpl.class);
	}

}
