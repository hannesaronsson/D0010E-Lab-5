package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
<<<<<<< HEAD
 */	
public class ArrivedEvent extends Event {
    
	private EventType type = EventType.ARRIVED;
=======
 */
public class ArrivedEvent extends Event {
    Customer customer;
    private EventType type = EventType.ARRIVED;
>>>>>>> origin/master

    public ArrivedEvent() {
        
    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {
<<<<<<< HEAD
    	// call method in state to create new customer, add to fifo queue
=======
        //call method in state to create new customer, add to fifo queue
>>>>>>> origin/master
    }
}



