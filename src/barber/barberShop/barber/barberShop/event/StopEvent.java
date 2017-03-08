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

	/*
	 * Breaks the simulation.
	 */
	private void emergencyBreak(SimulatorState state) {
		state.breakSimulation();
	}
	
	/**
	 * Executes this event.
	 * 
	 * @param state 		The state to affect.
	 * @param eventQueue	The queue to store events in.
	 */
	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		if ( eventQueue.getSize() == 0) { // has to throw an exception if this is not the last event in the queue, size() method needs to be implemented in EventQueue
			throw new IndexOutOfBoundsException();
		} else {
			emergencyBreak(state);
		}
	}

}


