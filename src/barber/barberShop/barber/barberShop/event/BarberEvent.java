package barber.barberShop.barber.barberShop.event;

import barber.barber.customerFactory.Customer;
import barber.barberShop.BarberState;
import barber.barberShop.EventType;
import barber.simulator.Event;

/**
 * An abstract class that extends the normal Event class. All other barber events extends this class.
 *
 * @author hannesaronsson
 */
abstract public class BarberEvent extends Event {
    EventType type;
    Customer customer;
    BarberState barberState;
    
    /**
     * Gets the EventType type of this event.
     * 
     * @return The type of this event.
     */
    public EventType getType() {
        return type;
    }
    
    /**
     * Gets the customer stored in this event.
     * 
     * @return The customer
     */
    public Customer getCustomer() {
        return customer;
    }

}
