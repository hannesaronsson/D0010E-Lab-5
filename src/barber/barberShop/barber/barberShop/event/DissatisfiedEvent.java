package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class DissatisfiedEvent extends Event {
<<<<<<< HEAD
	
	
	
	public DissatisfiedEvent() {
		
	}
	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		
	}
}
=======
    private EventType type = EventType.DISSATISFIED;

    public DissatisfiedEvent(Customer customer) {

    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {

    }

}
>>>>>>> origin/master
