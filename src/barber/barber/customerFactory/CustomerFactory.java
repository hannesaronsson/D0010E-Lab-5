package barber.barber.customerFactory;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class CustomerFactory {
    private int numberOfCustomers = 0;

    /**
     * Creates a new customer with an unique ID
     *
     * @return A new customer with an ID
     */
    public Customer newCustomer() {
        numberOfCustomers++;
        return new Customer(numberOfCustomers);
    }

    /**
     * Get the number of costumers in the current simulation
     *
     * @return Number of customers
     */
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }


}
