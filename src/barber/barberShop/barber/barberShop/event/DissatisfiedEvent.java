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
	private Customer customer;
	private BarberState barberState;
	private double newReadyBarberTime;
	private double stopTime;
	
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
		
		if ( barberState.addCustomer(customer) && newReadyBarberTime < stopTime) { // should add to some sort of priority queue
			eventQueue.addEvent(new ReadyBarberEvent(customer, newReadyBarberTime));
		}	
	}
}

