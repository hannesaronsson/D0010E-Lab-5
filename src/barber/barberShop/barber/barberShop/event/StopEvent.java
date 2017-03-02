package barber.barberShop.barber.barberShop.event;

import barber.barberShop.EventType;

import barber.simulator.Event;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class StopEvent extends Event {

	private EventType type = EventType.STOP;

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
		// break the simulation 
		//state.emergencyBreak();
	}

	public void runEvent(SimulatorState state, EventQueue eventQueue) {
		emergencyBreak(state);
	}

}


