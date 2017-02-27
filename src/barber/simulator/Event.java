package barber.simulator;

import barber.random.Time;

/**
 * Created by Mumrik on 2017-02-27.
 */
public abstract class Event {

    public double time;

    /**
     * Executes the event
     *
     * @param state      The current SimulationSate
     * @param eventQueue The current EventQueue
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
    }
}
