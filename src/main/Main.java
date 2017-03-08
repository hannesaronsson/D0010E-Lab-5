package main;

import barber.simulator.*;
import barber.barberShop.barber.barberShop.event.*;
import barber.barberShop.*;

public class Main {

	public static void main(String[] args) {
		
		EventQueue eventQueue = new EventQueue();
		BarberState barberState = new BarberState();
		BarberPrint barberPrint = new BarberPrint();
		
		Event start = new StartEvent();
		Event stop = new StopEvent(100);
		
		eventQueue.addEvent(start);
		eventQueue.addEvent(stop);
		
		
		barberState.addObserver(barberPrint);
		
		Simulator simulator = new Simulator(eventQueue, barberState);
		simulator.run();
		
	}
}
