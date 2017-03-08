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
	 * Adds an dissatisfied customer to the store queue. 
	 * 
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		
		barberState = (BarberState) state;
		barberState.setCurrentTime(getTime());
		
		newReadyBarberTime = barberState.getTime(READY_BARBER);
		int index = eventQueue.getSize() - 1;
		stopTime = eventQueue.getEvent(index).getTime(); // gets the time from the StopEvent
		canAddToQueue = barberState.addCustomer(customer); // checks if the customer can be added to the store queue
		walkingTime = barberState.getTime(DISSATISFIED);
		
		if ( canAddToQueue && newReadyBarberTime < stopTime) { // adds a ReadyBarber event to the event queue if 
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
		else if ( !canAddToQueue && walkingTime < stopTime) { // if queue is full, the dissatisfied customer will return later
			eventQueue.addEvent(new DissatisfiedEvent(customer, walkingTime));
		}
	}
}

