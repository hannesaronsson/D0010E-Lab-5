package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;
import barber.barberShop.BarberState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */	
public class ArrivedEvent extends Event {
    
	private EventType type = EventType.ARRIVED;
	private BarberState barberState;
	
    public ArrivedEvent(double time) {
    	setTime(time);
    }
    
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
    	// call method in state to create new customer, add to fifo queue
    	barberState = (BarberState) state;
    	barberState.addCustomer(barberState.createCustomer());
    	eventQueue.addEvent(new ArrivedEvent(barberState.getTime(type))); // add a new ArrivedEvent to the EventQueue
    }
}



