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

    public EventType getType() {
        return type;
    }

    public Customer getCustomer() {
        return customer;
    }

}
