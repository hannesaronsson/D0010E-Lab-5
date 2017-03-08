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
	
	private Customer customer;
	private BarberState barberState;
	private double newReadyBarberTime, stopTime;
	private final EventType READY_BARBER = EventType.READY_BARBER;
	
	
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
	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		 
		barberState = (BarberState) state;
		barberState.setCurrentTime(getTime());
		barberState.removeCustomer(customer); // removes the customer from the store queue
		
		newReadyBarberTime = barberState.getTime(READY_BARBER);
		int index = eventQueue.getSize() - 1;
    	stopTime = eventQueue.getEvent(index).getTime(); // gets the time from the StopEvent

		if (newReadyBarberTime < stopTime) {
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
	}

}
