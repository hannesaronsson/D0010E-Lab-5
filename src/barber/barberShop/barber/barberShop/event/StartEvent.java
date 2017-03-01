package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
<<<<<<< HEAD
public class StartEvent extends Event {	
	
	ArrivedEvent startEvent;
	
	public StartEvent() {
		setTime(0.0); // because this is the startevent, it starts at 0.0
	}
	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		startEvent = new ArrivedEvent(); // creates a new ArrivedEvent
		eventQueue.addEvent(startEvent);
	}
=======
public class StartEvent extends Event {
    private EventType type = EventType.START;
    ArrivedEvent startEvent;

    public StartEvent() {
        startEvent = new ArrivedEvent();
    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        eventQueue.addEvent(startEvent);
    }

>>>>>>> origin/master
}
