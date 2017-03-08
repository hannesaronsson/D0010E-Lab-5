package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */	
public class ArrivedEvent extends Event {
    
	private final EventType ARRIVED = EventType.ARRIVED;
	private final EventType START_HAIRCUT = EventType.START_HAIRCUT;
	private BarberState barberState;
	private Customer customer;
	
	/**
	 * Constructor
	 * 
	 * @param time
	 */
    ArrivedEvent(double time) {
    	setTime(time);
    }
    
    
	/**
	 * Executes this event. 
	 * 
	 * @param state 		The state to affect.
	 * @param eventQueue	The queue to store events in.
	 */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
    	
    	barberState = (BarberState) state;
    	barberState.setCurrentTime(getTime());
    	
    	int lastIndex = eventQueue.getSize() - 1;
    	double newArrivedTime = barberState.getTime(ARRIVED); 
    	double newHaircutTime = barberState.getTime(START_HAIRCUT);
    	double stopTime = eventQueue.getEvent(lastIndex).getTime(); // the time in the StopEvent
    			
    	customer = barberState.createCustomer(); // doesn't return a customer
    	
    	if ( barberState.addCustomer(customer) && newHaircutTime < stopTime) {
    		eventQueue.addEvent(new StartHaircutEvent(customer, newHaircutTime));
    	}
    	
    	if (newArrivedTime < stopTime) { // because an event can't be placed after the StopEvent
    		eventQueue.addEvent(new ArrivedEvent(newArrivedTime)); 
    	}
    }
}



