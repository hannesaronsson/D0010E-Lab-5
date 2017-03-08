package barber.barberShop;

import java.util.ArrayList;
import barber.barber.customerFactory.Customer;
import barber.barber.customerFactory.CustomerFactory;
import barber.random.Time;
import barber.simulator.SimulatorState;

/**
 * This object keeps track of the time and customers waiting in line.
 *
 * @author Kevin Träff on 2017-03-08.
 */

public class BarberState extends SimulatorState {

	final int queueCapacity = 6;
	final double lambda = 5;
	final long seed = 2353253;
	final double lowerHairCut = 2;
	final double upperHairCut = 7;
    final double lowerDissatisfied = 3;
    final double upperDissatisfied = 10;
    final double simulationStop = 25;

    private double currentTime = 0;
    CustomerFactory customerFactory = new CustomerFactory();

    Time barberTime = new Time(lambda, seed, lowerHairCut, upperHairCut, lowerDissatisfied, upperDissatisfied);
    ArrayList<Customer> barberQueue = new ArrayList<Customer>();
    ArrayList<Customer> dissatisfiedQueue = new ArrayList<Customer>();

    /**
     * Gives the current time.
     *
     * @return The current time.
     */
    public double getCurrentTime() {
    	return currentTime;
    }
    
    /**
     * Sets the current time to a new given time.
     *
     */
    public void setCurrentTime(double newCurrentTime) {
    	currentTime = newCurrentTime;
    }

    /**
     * Gives the current amount of customers in the queue.
     *
     * @return The current amount of customers in the queue.
     */
    public int getCurrentQueueSize() {
    	return barberQueue.size();
    }

    /**
     * Adds a new customer to the queue
     *
     * @return If the customer could be added to the queue or not.
     */
	public boolean addCustomer(Customer newCustomer) {
		//Om kunden är missnöjd
		if (!newCustomer.getSatisfied()) {
			if (barberQueue.size() >= queueCapacity) {
				int  dissatisfiedAmount = 0;
				for (int i = 0; i < barberQueue.size(); i++)
					if (!barberQueue.get(i).getSatisfied())
						dissatisfiedAmount++;
				
				if (dissatisfiedAmount >= barberQueue.size()) {
					setChanged();
					notifyObservers();
					return false;
				}
				
				ArrayList<Customer> tempQueue = new ArrayList<Customer>();
				
				for (int i = 0; i < dissatisfiedAmount; i++)
					tempQueue.add(barberQueue.get(i));
				
				tempQueue.add(newCustomer);
				
				for (int i = dissatisfiedAmount; i < barberQueue.size(); i++)
					if (tempQueue.size() < queueCapacity)
						tempQueue.add(barberQueue.get(i));
				
				barberQueue = tempQueue;
				setChanged();
				notifyObservers();
				return true;
			}
			
			else {
				int  dissatisfiedAmount = 0;
				for (int i = 0; i < barberQueue.size(); i++)
					if (!barberQueue.get(i).getSatisfied())
						dissatisfiedAmount++;
				
				ArrayList<Customer> tempQueue = new ArrayList<Customer>();
				
				for (int i = 0; i < dissatisfiedAmount; i++)
					tempQueue.add(barberQueue.get(i));
				
				tempQueue.add(newCustomer);
				
				for (int i = dissatisfiedAmount; i < barberQueue.size(); i++)
					tempQueue.add(barberQueue.get(i));
				
				barberQueue = tempQueue;
				setChanged();
				notifyObservers();
				return true;
			}
		}
		
		//Om kunden är nöjd
		else {
			if (currentTime >= simulationStop) {
				setChanged();
				notifyObservers();
				return false;
			}
			
			else if (barberQueue.size() >= queueCapacity) {
				setChanged();
				notifyObservers();
				return false;
			}
			
			else {
				setChanged();
				notifyObservers();
				return barberQueue.add(newCustomer); 
				}
		}
	}
	
    /**
     * Removes a given customer from the queue.
     *
     * @return If the customer could be removed or not.
     */
	public boolean removeCustomer(Customer customer)
	{
		setChanged();
		notifyObservers();
		return barberQueue.remove(customer);
	}

    /**
     * Gives a specified time depending on which eventType is given.
     *
     * @return A specified time.
     */
	public double getTime(EventType eventType) {
		switch(eventType) {
			case ARRIVED:
				return barberTime.nextArrivalTime(currentTime);
			case READY_BARBER:
				return barberTime.HairCutTime() + currentTime;
			case DISSATISFIED:
				return barberTime.nextReturnedDissatisfied(currentTime);
			case START_HAIRCUT:
				//Behövs metod i Time som ger en tid när klippningen kan börja
				return 0;
		}
		//Fel om detta nås (eclipse kräver en return här)
		return 0;
	}
	
    /**
     * Gives a new customer.
     *
     * @return The new customer.
     */
	public Customer createCustomer() {
		return customerFactory.newCustomer();
	}
}
