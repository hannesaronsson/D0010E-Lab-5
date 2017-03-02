package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/* 
 * När hårklippningen börjar 
 */
public class StartHaircutEvent extends Event {
	
	private Customer customer;
	
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
		/* 
		 * Should check if it is this customer's turn, if not maybe it
		 * should create a new StartHaircutEvent with a new calculated time?
		 * 
		 */
	}

}
