package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */

public class StartEvent extends Event {	
	
	private EventType type = EventType.START;
	private ArrivedEvent firstEvent;
	private BarberState barberState;
	
	/**
	 * Constructor
	 * 
	 */
	public StartEvent() {
		/*
		 * Should this take the arguments at [Chapter 5, p.2] as parameters? 
		 * And change the states final variables accordingly?
		 */
		setTime(0.0); // because this is the start event, it starts at 0.0
	}
	/**
	 * Executes this event. 
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		barberState = (BarberState) state; // assures that this is of BarberState type
		barberState.setCurrentTime(getTime()); // sets time in state
		firstEvent = new ArrivedEvent(barberState.getTime(type)); // creates a new ArrivedEvent and sets time
		eventQueue.addEvent(firstEvent); // and adds it to the queue
	}
}
