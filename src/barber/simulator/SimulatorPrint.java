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

    public abstract void firstPrint();

    public abstract void lastPrint();
}
