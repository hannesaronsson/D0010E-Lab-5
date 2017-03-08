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
	private double newArrivedTime, newReadyBarberTime, stopTime, newHaircutTime;
	
	
	/**
	 * Constructor
	 * 
	 * @param time
	 */
    ArrivedEvent(double time) {
    	setTime(time);
    }
    
    
    /**
     * Adds a customer to the store, creates a new ArrivedEvent at a random time
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
    	
    	barberState = (BarberState) state;
    	barberState.setCurrentTime(getTime());
    	
    	newArrivedTime = barberState.getTime(ARRIVED); 
    	newHaircutTime = barberState.getTime(START_HAIRCUT);
    	int index = eventQueue.getSize() - 1;
    	stopTime = eventQueue.getEvent(index).getTime(); // the time in the StopEvent
    			
    	customer = barberState.createCustomer(); // doesn't return a customer
    	
    	if ( barberState.addCustomer(customer) && newReadyBarberTime < stopTime) {
    		eventQueue.addEvent(new StartHaircutEvent(customer, newHaircutTime));
    	}
    	
    	if (newArrivedTime < stopTime) { // because an event can't be placed after the StopEvent
    		eventQueue.addEvent(new ArrivedEvent(newArrivedTime)); 
    	}
    }
}



