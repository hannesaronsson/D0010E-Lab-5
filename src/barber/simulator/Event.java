package barber.simulator;

import barber.random.Time;

/**
 * Created by Mumrik on 2017-02-27.
 */
public abstract class Event {

    private double time;
    
    /**
     * Returns time
     * 
     * @return
     */
    public double getTime() {
    	return time;
    }
    
    /**
     * Sets the time
     * 
     * @param time		The time to assign to the event
     */
    public void setTime(double time) {
    	this.time = time;
    }
    
    /**
     * Executes the event
     *
     * @param state      The current SimulationSate
     * @param eventQueue The current EventQueue
     */
    public abstract void runEvent(SimulatorState state, EventQueue eventQueue);

}
