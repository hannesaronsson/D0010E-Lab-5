package barber.barberShop.barber.barberShop.event;

import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StopEvent extends BarberEvent {
  
	/**
     * Constructor.
     *
     * @param time Assigns the time to this object.
     */
    public StopEvent(double time) {
        type = EventType.STOP;
        setTime(time);
    }
    
    /**
     * Executes this event
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        this.barberState = (BarberState) state;

        emergencyBreak(state);
        barberState.updateView(this);
    }
    
    /*
     * Breaks the simulation in state
     */
    private void emergencyBreak(SimulatorState state) {
        state.setSimulating(false);
    }
}


