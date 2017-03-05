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
public class ReadyBarberEvent extends Event {
    
	private final EventType READY_BARBER = EventType.READY_BARBER;
	private Customer customer;
	private BarberState barberState;
	
	private double newReadyBarberTime, stopTime, probability;
	
	/**
	 * Constructor
	 * 
	 * @param customer
	 * @param time
	 */
	ReadyBarberEvent(Customer customer, double time) {
		this.customer = customer;
		setTime(time);
	}
	
	/**
	 * Decides whether the customer is satisfied or not. 
	 * 
	 * @param chance The probability that the customer will not be satisfied
	 * @return
	 */
	private boolean isSatisfied(double chance) {
		double random = Math.random(); // not sure if Math.random() is allowed
		if (random >= chance) {
			return true;
		} else {
			return false;
		}
	}
	
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
    	
    	barberState = (BarberState) state;
    	barberState.setCurrentTime(getTime());
    	barberState.removeCustomer(customer);
    	probability = barberState.getProbability();
    	
    	newReadyBarberTime = barberState.getTime(READY_BARBER);
    	stopTime = eventQueue.getLast().time();
    	
    	if ( !isSatisfied(probability) && newReadyBarberTime < stopTime) { // should get the probabilty from barberState
    		eventQueue.addEvent(new DissatisfiedEvent(customer, newReadyBarberTime));
    	}
    }

}