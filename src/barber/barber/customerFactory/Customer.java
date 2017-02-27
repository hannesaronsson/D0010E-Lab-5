package barber.barber.customerFactory;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Customer {
    private int customerID;
    private boolean satisfied;


    Customer(int ID){
        customerID = ID;
    }

    public int getCustomerID(){
        return customerID;
    }

    public boolean getSatisfied(){
        return satisfied;
    }

    public void setSatisfied(boolean satisfied){
        this.satisfied = satisfied;
    }

}
