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
	
	/* 
	 * Should check if it is this customer's turn, if not maybe it
	 * should create a new StartHaircutEvent with a new calculated time?
	 * 
	 */	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		 
		barberState = (BarberState) state;
		barberState.setCurrentTime(getTime());
		barberState.removeCustomer(customer);
		
		newReadyBarberTime = barberState.getTime(READY_BARBER);
    	stopTime = eventQueue.getLast().time(); // needs some way to get the time in the StopEvent event

		if (newReadyBarberTime < stopTime) {
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}
	}

}
