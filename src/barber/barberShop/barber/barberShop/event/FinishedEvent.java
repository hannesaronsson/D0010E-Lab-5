package barber.barberShop.barber.barberShop.event;

import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;


/**
 * A event that indicates that a hair cut is done.
 *
 * @author hannesaronsson
 */
public class FinishedEvent extends BarberEvent {
    /**
     * Constructor
     *
     * @param customer
     * @param time
     */
    FinishedEvent(Customer customer, double time) {
        setTime(time);
        this.customer = customer;
        type = EventType.FINISHED;
    }

    /**
     * Decides whether the customer is satisfied or not by the hair cut.
     *
     * @return true if satisfied, false if not.
     */
    private boolean isSatisfied() {
        double random = barberState.randomDissatisfiedChance();
        if (random > barberState.getP()) {
            this.customer.setSatisfied(true);
            return true;
        } else {
            this.customer.setSatisfied(false);
            return false;
        }
    }

    public void runEvent(SimulatorState state, EventQueue eventQueue) {

        barberState = (BarberState) state;
        barberState.setCurrentTime(getTime());
        barberState.updateView(this);

        barberState.newChairAvailable();

        /**
         * If the customer is not satisified create a DissatisfiedEvent.
         * If there are customers in the queue, create a StartHaircutEvent for the next customer in the barberQueue.
         */
        if (!isSatisfied()) {
            eventQueue.addEvent(new DissatisfiedEvent(customer, barberState.getTime(EventType.RETURNED)));
        }
        if (barberState.customerInQueue()) {
            Customer newCustomer = barberState.getFirstCustomer();
            eventQueue.addEvent(new StartHaircutEvent(newCustomer, barberState.getTime(EventType.CURRENT_TIME)));
        }


    }

}