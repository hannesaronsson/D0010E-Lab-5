package barber.barber.customerFactory;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class CustomerFactory {
    private int numberOfCustomers = 0;

    public Customer newCustomer(){
        numberOfCustomers++;
        return new Customer(numberOfCustomers);
    }

    public int getNumberOfCustomers(){
        return numberOfCustomers;
    }


}
