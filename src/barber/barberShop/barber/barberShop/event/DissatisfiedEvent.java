package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.EventQueue;
import barber.simulator.SimulatorState;

/**
 * Creates an event for a dissatisfied customer.
 *
 * @author hannesaronsson
 */
public class DissatisfiedEvent extends BarberEvent {

    /**
     * Constructor
     *
     * @param customer The customer that is dissatisfied
     * @param time     The time this customer will return at
     */
    DissatisfiedEvent(Customer customer, double time) {
        this.customer = customer;
        setTime(time);
        type = EventType.RETURNED;
    }

    /**
     *
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {
        barberState = (BarberState) state;
        barberState.setCurrentTime(getTime());

        Customer lastCustomer;

        /**
         * If there are any available chairs start a hair cut event right now.
         * If the barberQueue is not full, add the customer to the queue.
         * if the barberQueue is full check if the last customer in the queue is a dissatisfied customer,
         * then return later. Else kick the other customer out.
         */
        this.customer.setEnterShop(getTime());
        if (barberState.availableChairs()) {
            barberState.updateView(this);
            eventQueue.addEvent(new StartHaircutEvent(customer, barberState.getTime(EventType.CURRENT_TIME)));
        } else if (!barberState.isQueueFull()) {
            barberState.updateView(this);
            barberState.addCustomer(customer);
        } else {
            lastCustomer = barberState.getLastCustomer();
            if (!lastCustomer.getSatisfied()) {
                eventQueue.addEvent(new DissatisfiedEvent(this.customer, barberState.getTime(EventType.ARRIVED)));
                return;
            } else {
                barberState.updateView(this);
                barberState.removeCustomer(lastCustomer);
                barberState.addCustomer(this.customer);
            }
        }


    }
}

