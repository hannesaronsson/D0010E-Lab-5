package barber.barber.customerFactory;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Customer {
    private int customerID;
    private boolean satisfied = true;
    private double enterShop;
    private boolean firstTime = true;
    
    /**
     * Sets the time when this customer enters the shop.
     * 
     * @param enterShop The time 
     */
    public void setEnterShop(double enterShop) {
        this.enterShop = enterShop;
    }
    
    /**
     * Gets the time when the customer entered the shop.
     * 
     * @return enterShop The time when entered the shop.
     */
    public double getEnterShop() {
        return enterShop;
    }

    /**
     * Returns true if its the customers first hair cut.
     * @return true if its the first hair cut, false otherwise.
     */
    public boolean isFirstTime(){
        return firstTime;
    }

    /**
     *Sets firstTime to false.
     */
    public void notFirstTime() {
        this.firstTime = false;
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
     * Checks if the customer was satisfied with their haircut.
     *
     * @return True if satisfied, false if dissatisfied.
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
