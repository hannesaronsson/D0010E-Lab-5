package barber.barberShop;

import java.util.ArrayList;
import barber.barber.customerFactory.Customer;
import barber.barber.customerFactory.CustomerFactory;
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
    final double simulationStop = 25;

    private double currentTime = 0;
    CustomerFactory customerFactory = new CustomerFactory();

    Time barberTime = new Time(lambda, seed, lowerHairCut, upperHairCut, lowerDissatisfied, upperDissatisfied);
    ArrayList<Customer> barberQueue = new ArrayList<Customer>();
    ArrayList<Customer> dissatisfiedQueue = new ArrayList<Customer>();

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
		//Om kunden är missnöjd
		if (!newCustomer.getSatisfied()) {
			if (barberQueue.size() >= queueCapacity) {
				int  dissatisfiedAmount = 0;
				for (int i = 0; i < barberQueue.size(); i++)
					if (!barberQueue.get(i).getSatisfied())
						dissatisfiedAmount++;
				
				if (dissatisfiedAmount >= barberQueue.size())
					return false;
				
				ArrayList<Customer> tempQueue = new ArrayList<Customer>();
				
				for (int i = 0; i < dissatisfiedAmount; i++)
					tempQueue.add(barberQueue.get(i));
				
				tempQueue.add(newCustomer);
				
				for (int i = dissatisfiedAmount; i < barberQueue.size(); i++)
					if (tempQueue.size() < queueCapacity)
						tempQueue.add(barberQueue.get(i));
				
				barberQueue = tempQueue;
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
				return true;
			}
		}
		
		//Om kunden är nöjd
		else {
			if (currentTime >= simulationStop)
				return false;
			
			else if (barberQueue.size() >= queueCapacity) {
				return false;
			}
			
			else
				return barberQueue.add(newCustomer);
		}
	}

	public boolean removeCustomer(Customer customer)
	{
		return barberQueue.remove(customer);
	}

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
	
	public Customer createCustomer() {
		return customerFactory.newCustomer();
	}
}
