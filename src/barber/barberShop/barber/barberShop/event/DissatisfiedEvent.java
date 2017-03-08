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
	 * Executes this event. 
	 * 
	 * @param state 		The state to affect.
	 * @param eventQueue	The queue to store events in.
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		
		barberState = (BarberState) state;
		barberState.setCurrentTime(getTime());
		
		int lastIndex = eventQueue.getSize() - 1;
		boolean canAddToQueue = barberState.addCustomer(customer); // checks if the customer can be added to the store queue
		
		double newReadyBarberTime = barberState.getTime(READY_BARBER);
		double stopTime = eventQueue.getEvent(lastIndex).getTime(); // gets the time from the StopEvent
		double walkingTime = barberState.getTime(DISSATISFIED);
		
		if ( canAddToQueue && newReadyBarberTime < stopTime) { // adds a ReadyBarber event to the event queue if possible
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
		else if ( !canAddToQueue && walkingTime < stopTime) { // if queue is full, the dissatisfied customer will return later
			eventQueue.addEvent(new DissatisfiedEvent(customer, walkingTime));
		}
	}
}

