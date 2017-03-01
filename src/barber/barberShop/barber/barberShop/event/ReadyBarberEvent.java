package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class ReadyBarberEvent extends Event{
    EventType type = EventType.READY_BARBER;

    public void runEvent(SimulatorState state, EventQueue eventQueue) {

	}

