package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Creates an event for a hair cut.
 *
 * @author hannesaronsson
 */
public class StartHaircutEvent extends BarberEvent {

    /**
     * Constructor
     *
     * @param customer The customer that will have their hair cut.
     * @param time     The expected time when it will happen.
     */
    StartHaircutEvent(Customer customer, double time) {
        setTime(time);
        this.customer = customer;
        type = EventType.HAIR_CUT;
    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        barberState = (BarberState) state;
        barberState.setCurrentTime(getTime());

        barberState.occupyChair();

        /**
         * Creates a FinsihedEvent for when the customer is done cutting his hair.
         * If the customer is a new customer, increase one to numberOfCustomers.
         */
        eventQueue.addEvent(new FinishedEvent(this.customer, barberState.getTime(EventType.HAIR_CUT)));


    }

}
