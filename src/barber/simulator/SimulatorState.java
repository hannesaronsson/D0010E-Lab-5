package barber.simulator;

import java.util.Observable;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class SimulatorState extends Observable {

    boolean simulating;
    
    /**
     * Sets this state's simulation. Either on or off.s
     * 
     * @param simulating Boolean to assign
     */
    public void setSimulating(boolean simulating) {
        this.simulating = simulating;
    }
    
    /**
     * Checks if this state is simulating.
     * 
     * @return simulating Boolean
     */
    public boolean isSimulating() {
        return simulating;
    }
}
