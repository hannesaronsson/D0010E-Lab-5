package barber.barberShop.barber.barberShop.event;

import barber.simulator.*;
import barber.barberShop.BarberState;

import barber.barberShop.EventType;

/**
 * Creates an arrive event for a new customer
 *
 * @author hannesaronsson
 */
public class ArrivedEvent extends BarberEvent {

    /**
     * Constructor.
     *
     * @param time
     */
    ArrivedEvent(double time) {
        type = EventType.ARRIVED;
        setTime(time);
    }

    /**
     * Adds a customer to the store, creates a new ArrivedEvent at a random time
     */
    public void runEvent(SimulatorState state, EventQueue eventQueue) {

        barberState = (BarberState) state;

        //If the shop is closed, do nothing.
        if (barberState.getClosed()) return;
        barberState.setCurrentTime(getTime());

        this.customer = barberState.createCustomer();
        this.customer.setEnterShop(getTime());
        //Updates the view.
        barberState.updateView(this);

        /*
         * If there are any available chairs create a StartHairCutEvent right now.
         * If there are no available chairs add the customer to the barberQueue, if the
         * queue is full leave the shop and add one to the numberOfLostCustomers.
         */
        if (barberState.availableChairs()) {
            eventQueue.addEvent(new StartHaircutEvent(customer, barberState.getTime(EventType.CURRENT_TIME)));
       
        } else if (!barberState.isQueueFull()) {
            barberState.addCustomer(customer);

        } else if (barberState.isQueueFull()) {
            barberState.addLostCustomer();
        }

        eventQueue.addEvent(new ArrivedEvent(barberState.getTime(type))); // adds a new ArrivedEvent to the EventQueue

    }
}



