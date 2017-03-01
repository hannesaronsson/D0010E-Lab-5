package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barber.customerFactory.CustomerFactory;
import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StartEvent extends Event {
    private EventType type = EventType.START;
	ArrivedEvent startEvent;

	public StartEvent() {
		startEvent = new ArrivedEvent();
	}

	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		eventQueue.addEvent(startEvent);
	}


