package barber.barberShop.barber.barberShop.event;

import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Creates a close event that closes the shop.
 *
 * @author hannesaronsson
 */
public class CloseEvent extends BarberEvent {

    public CloseEvent(double time) {
        setTime(time);
        type = EventType.CLOSED;
    }

    @Override
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        barberState = (BarberState) state;
        barberState.setCurrentTime(getTime());

        barberState.close();
        barberState.updateView(this);
    }
}
