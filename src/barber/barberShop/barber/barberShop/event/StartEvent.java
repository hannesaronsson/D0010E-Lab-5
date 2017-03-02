package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StartEvent extends Event {	
	
	private EventType type = EventType.START;
	private ArrivedEvent firstEvent;
	private BarberState barberState;
	
	public StartEvent() {
		setTime(0.0); // because this is the startevent, it starts at 0.0
	}
	
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		barberState = (BarberState) state;
		firstEvent = new ArrivedEvent(barberState.getTime(type)); // creates a new ArrivedEvent and sets time
		eventQueue.addEvent(firstEvent); // and adds it to the queue
	}
}
