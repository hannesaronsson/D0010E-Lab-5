package barber.barber.customerFactory;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Customer {
    private int customerID;
    private boolean satisfied;
    private double cutTime;


    public void setCutTime(double cutTime) {
        this.cutTime = cutTime;
    }

    public double getCutTime() {
        return cutTime;
    }

    Customer(int ID) {
        customerID = ID;
    }

    /**
     * Get the customer ID of this customer.
     *
     * @return Customer ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Checks if the customer was saisfied with their haircut.
     *
     * @return True if satisifed false if dissatisfied.
     */
    public boolean getSatisfied() {
        return satisfied;
    }

    /**
     * Sets satisfied.
     *
     * @param satisfied True or false depending on if the customer was satisfied.
     */
    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

}
