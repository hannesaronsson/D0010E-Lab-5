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
	
	private final EventType ARRIVED = EventType.ARRIVED;
	private BarberState barberState;
	
	/**
	 * Constructor.
	 * Always sets the time of this event to 0. 
	 * 
	 */
	public StartEvent() {
		setTime(0.0); // because this is the start event, it starts at 0.0
	}
	
	/**
	 * Executes this event. 
	 * 
	 * @param state 		The state to affect.
	 * @param eventQueue	The queue to store events in.
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		barberState = (BarberState) state; // assures that this is of BarberState type
		barberState.setCurrentTime(getTime()); // sets time in state
		ArrivedEvent firstEvent = new ArrivedEvent(barberState.getTime(ARRIVED)); // creates a first ArrivedEvent
		eventQueue.addEvent(firstEvent); 
	}
}
