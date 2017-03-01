package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class ArrivedEvent extends Event{
	
	public ArrivedEvent() {
		
	}
	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		// call method in state to create new customer, add to fifo queue
	}
	
}
