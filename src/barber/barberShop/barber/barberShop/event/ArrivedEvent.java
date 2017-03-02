package barber.barberShop.barber.barberShop.event;

import barber.simulator.*;
import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */	
public class ArrivedEvent extends Event {
    
	private EventType type = EventType.ARRIVED;
	private BarberState barberState;
	
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
    	
    	Customer customer = barberState.createCustomer(); // create a new customer and add it to the store's queue
    	barberState.addCustomer(customer);

    	eventQueue.addEvent(new StartHaircutEvent(customer, barberState.getTime(TYPE HERE))); // add HaircutEvent and calculate when haircut starts
    	eventQueue.addEvent(new ArrivedEvent(barberState.getTime(type))); // adds a new ArrivedEvent to the EventQueue
    }
}



