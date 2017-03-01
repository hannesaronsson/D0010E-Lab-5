package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class DissatisfiedEvent extends Event {
    private EventType type = EventType.DISSATISFIED;

    public DissatisfiedEvent(Customer customer) {

	}

	public void runEvent(SimulatorState state, EventQueue eventQueue) {

	}

