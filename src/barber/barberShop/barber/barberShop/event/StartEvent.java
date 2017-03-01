package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StartEvent extends Event {

    ArrivedEvent startEvent;

    public StartEvent() {
        setTime(0.0); // because this is the startevent, it starts at 0.0
    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        startEvent = new ArrivedEvent(); // creates a new ArrivedEvent
        eventQueue.addEvent(startEvent);
    }

}
