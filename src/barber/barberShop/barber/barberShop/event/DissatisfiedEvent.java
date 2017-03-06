package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class DissatisfiedEvent extends Event {
	
	private final EventType READY_BARBER = EventType.READY_BARBER;
	private final EventType DISSATISFIED = EventType.DISSATISFIED;
	private Customer customer;
	private BarberState barberState;
	
	private double newReadyBarberTime, walkingTime, stopTime;
	private boolean canAddToQueue;
	
	/**
	 * Constructor
	 * 
	 * @param customer The customer that is dissatisfied
	 * @param time The time this customer will return at
	 */
	DissatisfiedEvent(Customer customer, double time) {
		this.customer = customer;
		setTime(time);
	}
	
	/**
	 * 
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		
		barberState = (BarberState) state;
		barberState.setCurrentTime(getTime());
		
		newReadyBarberTime = barberState.getTime(READY_BARBER);
		stopTime = eventQueue.getLast().time(); // needs some way to get the time in the StopEvent event
		canAddToQueue = barberState.addCustomer(customer);
		
		if ( canAddToQueue && newReadyBarberTime < stopTime) { // should add to some sort of priority queue
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
		else if ( !canAddToQueue) { // if the dissatisfied customer can't enter the queue, the customer will go for a walk and come back later
			walkingTime = barberState.getTime(DISSATISFIED);
			eventQueue.addEvent(new DissatisfiedEvent(customer, walkingTime));
		}
	}
}

