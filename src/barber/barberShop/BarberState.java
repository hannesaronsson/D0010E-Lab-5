package barber.barberShop;

import java.util.PriorityQueue;
import barber.barber.customerFactory.Customer;
import barber.random.Time;
import barber.simulator.SimulatorState;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class BarberState extends SimulatorState {

	final int queueCapacity = 6;
	final double lambda = 5;
	final long seed = 2353253;
	final double lowerHairCut = 2;
	final double upperHairCut = 7;
    final double lowerDissatisfied = 3;
    final double upperDissatisfied = 10;

    private double currentTime = 0;

    Time barberTime = new Time(lambda, seed, lowerHairCut, upperHairCut, lowerDissatisfied, upperDissatisfied);
	PriorityQueue<Customer> barberQueue = new PriorityQueue<Customer>(queueCapacity);

    public double getCurrentTime() {
    	return currentTime;
    }

    public void setCurrentTime(double newCurrentTime) {
    	currentTime = newCurrentTime;
    }

    public int getCurrentQueueSize() {
    	return barberQueue.size();
    }

	public boolean addCustomer(Customer newCustomer) {
		if (!newCustomer.getSatisfied()) {
			//
		}
		return barberQueue.add(newCustomer);
	}

	public boolean removeCustomer(Customer customer)
	{
		return barberQueue.remove(customer);
	}

	public double getTime(EventType eventType) {
		switch(eventType) {
			case ARRIVED:
				return barberTime.nextArrivalTime();
			case START:
				return barberTime.nextArrivalTime();
			case STOP:
				return barberTime.getTotalQueueTime();
			case READY_BARBER:
				return barberTime.nextHairCutTime();
			case DISSATISFIED:
				return barberTime.nextReturnedDissatisfied();
		}
		return 0;
	}

	//??
	public Customer createCustomer() {
		return null;
	}

}
