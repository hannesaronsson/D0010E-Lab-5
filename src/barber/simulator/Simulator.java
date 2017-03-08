package barber.simulator;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Simulator {

    private EventQueue eventQueue;
    private SimulatorState state;
    private SimulatorPrint simulatorPrint;

    /**
     * Constructor
     *
     * @param eventQueue The queue for the events for the simulation.
     * @param state      The simulation state.
     */
    public Simulator(EventQueue eventQueue, SimulatorState state, SimulatorPrint simulatorPrint) {
        this.eventQueue = eventQueue;
        this.state = state;
        this.simulatorPrint = simulatorPrint;
    }

    /**
     * Runs the simulation until the simulation state is changed to stop.
     */
    public void run() {
        Event nextEvent;
        simulatorPrint.firstPrint();
        while (state.simulating) {
            nextEvent = eventQueue.getFirstEvent();
            nextEvent.runEvent(state, eventQueue);
        }
        simulatorPrint.lastPrint();
    }
}
