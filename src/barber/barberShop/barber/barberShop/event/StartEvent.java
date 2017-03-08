package barber.barberShop.barber.barberShop.event;

import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;

/**
 * Created by Mumrik on 2017-02-27.
 */

public class StartEvent extends BarberEvent {

    private ArrivedEvent firstEvent;

    /**
     * Constructor
     */
    public StartEvent() {
        setTime(0.0); // because this is the start event, it starts at 0.0
        type = EventType.START;
    }

    /**
     * Executes this event.
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {

        barberState = (BarberState) state; // assures that this is of BarberState type
        barberState.setCurrentTime(getTime()); // sets time in state
        firstEvent = new ArrivedEvent(barberState.getTime(EventType.ARRIVED)); // creates a new ArrivedEvent and sets time
        eventQueue.addEvent(firstEvent); // and adds it to the queue
        barberState.updateView(this);
        
    }
}
