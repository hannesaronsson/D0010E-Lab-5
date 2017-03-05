package barber.barberShop.barber.barberShop.event;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StopEvent extends Event {
	
	/**
	 * Constructor. 
	 *
	 * @param time Assigns the time to this object.
	 */
	public StopEvent(double time) {
		setTime(time);
	}

	/**
	 * Breaks the simulation
	 *
	 * @param state
	 */
	private void emergencyBreak(SimulatorState state) {
		state.breakSimulation();
	}

	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		if ( eventQueue.size() != 1) { // has to throw an exception if this is not the last event in the queue, size() method needs to be implemented in EventQueue
			throw new Exception();
		} else {
			emergencyBreak(state);
		}
	}

}


