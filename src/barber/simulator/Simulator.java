package barber.simulator;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Simulator {

    private EventQueue eventQueue;
    private SimulatorState state;

    public Simulator(EventQueue eventQueue, SimulatorState state){
        this.eventQueue = eventQueue;
        this.state = state;

    }

    public void run(){
        Event currentEvent;
        while (state.simulating){
            eventQueue.sortEvents();
            currentEvent = eventQueue.getFirstEvent();
            currentEvent.runEvent();
        }
    }
}
