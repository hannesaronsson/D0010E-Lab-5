package barber.barberShop;

import barber.barber.customerFactory.Customer;
import barber.barber.customerFactory.CustomerFactory;
import barber.random.Time;
import barber.simulator.Event;
import barber.simulator.SimulatorState;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * The state of the barber shop.
 *
 * @author hannesaronsson
 */
public class BarberState extends SimulatorState {

    final int queueCapacity = 2;
    final long seed = 1116;
    final double p = 0.5;
    final double lambda = 1.2;
    final double lowerHairCut = 1;
    final double upperHairCut = 2;
    final double lowerDissatisfied = 1;
    final double upperDissatisfied = 2;
    final double closeTime = 7;

    int chairs = 2;
    int numberOfHairCuts = 0;
    int numberOfLostCustomers = 0;
    int numberOfReturnedCustomers = 0;
    int largestQueue = 0;
    int numberOfCustomers = 0;

    double totalCuttingTime = 0;
    double totalIdleTime;
    double totalQueueTime;

    private boolean closed = false;
    private double currentTime;
    private PriorityQueue<Customer> barberQueue = new PriorityQueue<>(queueCapacity,
            Comparator.comparing(Customer::getSatisfied).thenComparing(Customer::getEnterShop));

    private Time barberTime = new Time(lambda, seed, lowerHairCut, upperHairCut, lowerDissatisfied, upperDissatisfied);
    private CustomerFactory customerFactory = new CustomerFactory();
    private Random dissatisfiedChance = new Random(seed);

    /**
     * Constructor.
     */
    public BarberState() {
        setSimulating(true);
    }

    /**
     * Returns the current customer queue size.
     *
     * @return barberQueue size.
     */
    public int getCurrentQueueSize() {
        if (barberQueue.size() > largestQueue) largestQueue = barberQueue.size();
        return barberQueue.size();
    }

    /**
     * Returns the probability for dissatisfied customers.
     *
     * @return p.
     */
    public double getP() {
        return p;
    }

    /**
     * Returns the time for a specific event.
     *
     * @param eventType The type of event that requests a time.
     * @return Returns the time for a specific event.
     */
    public double getTime(EventType eventType) {
        switch (eventType) {
            case ARRIVED:
                return barberTime.nextArrivalTime(currentTime);
            case RETURNED:
                return barberTime.nextReturnedDissatisfied(currentTime);
            case HAIR_CUT:
                return barberTime.nextHairCutTime(currentTime);
            case CURRENT_TIME:
                return currentTime;
            default:
                return 0;
        }
    }

    /**
     * Returns the close time for the barber shop.
     *
     * @return The close time.
     */
    public double getCloseTime() {
        return closeTime;
    }

    /**
     * Returns a number between 0 and 1.
     *
     * @return double 0-1.
     */
    public double randomDissatisfiedChance() {
        return dissatisfiedChance.nextDouble();
    }

    /**
     * Returns true if the barberQueue is full and false otherwise.
     *
     * @return true if full, false if not full.
     */
    public boolean isQueueFull() {
        return barberQueue.size() == queueCapacity;
    }

    /**
     * Returns true if there are customers in barberQueue.
     *
     * @return true if customers are in barberQueue.
     */
    public boolean customerInQueue() {
        return !barberQueue.isEmpty();
    }

    /**
     * Returns true if the shop is closed and false if the shop is open.
     *
     * @return true if close, false if open.
     */
    public boolean getClosed() {
        return closed;
    }

    /**
     * Returns true if there are available chairs in the saloon false otherwise.
     *
     * @return true if chairs available, false if not.
     */
    public boolean availableChairs() {
        return chairs > 0;
    }

    /**
     * Returns the first customer in barberQueue.
     *
     * @return first customer from barberQueue.
     */
    public Customer getFirstCustomer() {
        return barberQueue.poll();
    }

    /**
     * Returns the last customer in barberQueue.
     *
     * @return last customer from barberQueue.
     */
    public Customer getLastCustomer() {
        return (Customer) barberQueue.toArray()[barberQueue.size() - 1];
    }

    /**
     * Creates a new customer from CustomerFactory.
     *
     * @return new customer.
     */
    public Customer createCustomer() {
        return customerFactory.newCustomer();

    }

    /**
     * Sets the current time for the simulation.
     *
     * @param newCurrentTime the new time for the simulation.
     */
    public void setCurrentTime(double newCurrentTime) {
        totalIdleTime += chairs * (newCurrentTime - currentTime);
        totalQueueTime += getCurrentQueueSize() * (newCurrentTime - currentTime);
        currentTime = newCurrentTime;
    }
    
    /**
     * Removes the customer from barberQueue and removes the queue time from that customer.
     *
     * @param customer the customer to be removed.
     */
    public void removeCustomer(Customer customer) {
        removeQueueTime(customer);
        barberQueue.remove(customer);

    }

    /**
     * Updates the view by notifying the observers.
     *
     * @param event The event that is to be printed.
     */
    public void updateView(Event event) {
        totalCuttingTime = barberTime.getTotalHairCutTime();
        setChanged();
        notifyObservers(event);
    }

    /**
     * Increase the numberOfReturnedCustomers by one.
     */
    public void addReturnedCustomer() {
        numberOfReturnedCustomers++;
    }

    /**
     * Increase the numberOfLostCustomers by one.
     */
    public void addLostCustomer() {
        numberOfLostCustomers++;
    }

    /**
     * Closes the shop.
     */
    public void close() {
        closed = true;
    }

    /**
     * Increase the numberOfCustomers by one.
     */
    public void newCustomer() {
        numberOfCustomers++;
    }

    /**
     * Adds the customer to barberQueue.
     *
     * @param customer the customer to be added.
     */
    public void addCustomer(Customer customer) {
        barberQueue.add(customer);
    }

    /**
     * Occupies one chair in the barberQueue by decreasing chairs by one.
     */
    public void occupyChair() {
        chairs--;
    }

    /**
     * Increases chair by one.
     */
    public void newChairAvailable() {
        chairs++;
        numberOfHairCuts++;
    }

    private void removeQueueTime(Customer customer) {
        totalQueueTime -= currentTime - customer.getEnterShop();
    }

}
