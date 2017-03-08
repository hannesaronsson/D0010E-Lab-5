package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/* 
 * Just a draft.
 */
public class StartHaircutEvent extends Event {
	
	private final EventType READY_BARBER = EventType.READY_BARBER;
	
	private Customer customer;
	private BarberState barberState;

	/**
	 * Constructor
	 * 
	 * @param customer The customer that will have their hair cut
	 * @param time The expected time when it will happen
	 */
	StartHaircutEvent(Customer customer, double time) {
		setTime(time);
		this.customer = customer;
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
		barberState.removeCustomer(customer); // removes the customer from the store queue
		
		double newReadyBarberTime = barberState.getTime(READY_BARBER);
		int lastIndex = eventQueue.getSize() - 1;
    	double stopTime = eventQueue.getEvent(lastIndex).getTime(); // gets the time from the StopEvent

		if (newReadyBarberTime < stopTime) {
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
	}

}
