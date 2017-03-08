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
	private boolean satisfied;
	
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
	
	/*
	 * Returns a boolean randomly. chance decides the probability.
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
    	int index = eventQueue.getSize() - 1;
    	stopTime = eventQueue.getEvent(index).getTime();
    	satisfied = isSatisfied(probability);
    	
    	if (satisfied && newReadyBarberTime < stopTime) { // should get the probabilty from barberState
    		customer.setSatisfied(false);
    		eventQueue.addEvent(new DissatisfiedEvent(customer, newReadyBarberTime));
    	}
    }

}