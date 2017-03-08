package barber.simulator;

import java.util.Observer;

/**
 * Created by Mumrik on 2017-02-27.
 */
public abstract class SimulatorPrint implements Observer {
    private SimulatorState simulatorState;

    protected SimulatorPrint(SimulatorState simulatorState) {
        this.simulatorState = simulatorState;
    }
    
    /**
     * Prints information before the simulation starts.
     */
    public abstract void firstPrint();
    
    /**
     * Prints information after the simulation ends.
     */
    public abstract void lastPrint();
}
