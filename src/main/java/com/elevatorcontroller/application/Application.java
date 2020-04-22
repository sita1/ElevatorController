package com.elevatorcontroller.application;

import com.elevatorcontroller.module.AppModule;
import com.elevatorcontroller.module.StartElevator;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Application {
	public static void main(String ab[])
	{
		Injector injector = Guice.createInjector(new AppModule());
	    StartElevator startElevator =injector.getInstance(StartElevator.class);
	    startElevator.startElevators();
	}
}
