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
	private final EventType START_CUT = EventType.START_CUT;
	private BarberState barberState;
	private Customer customer;
	private double newArrivedTime, newReadyBarberTime, stopTime;
	
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
    	/*
    	 * Can be made to look better, but it's just a draft
    	 */
    	
    	barberState = (BarberState) state;
    	barberState.setCurrentTime(getTime());
    	
    	newArrivedTime = barberState.getTime(ARRIVED); 
    	newHaircutTime = barberState.getTime(START_CUT);
    	stopTime = eventQueue.getEvent(-1).getTime(); // the time in the StopEvent
    			
    	customer = barberState.createCustomer(); // should not return boolean
    	
    	if ( barberState.addCustomer(customer) && newReadyBarberTime < stopTime) { // only runs if the customer can be added to the queue
    		eventQueue.addEvent(new StartHaircutEvent(customer, newHaircutTime));
    		
    	}
    	
    	if (newArrivedTime > stopTime) { // because an event can't be placed after the StopEvent
    		eventQueue.addEvent(new ArrivedEvent(newArrivedTime)); 
    	}
    }
}



