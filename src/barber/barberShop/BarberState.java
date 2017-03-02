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
		//För dissatisfied customers, fixa sen
		if (!newCustomer.getSatisfied()) {
			if (barberQueue.size() >= queueCapacity) {
				//kolla hur många andra kunder som också är missnöjda i kön
				//skapa en temp arraylist
				//lägg in alla missnöjda kunder som var där tidigare
				//lägg in den nya missnödja kunden OM det finns plats, annars ska kunden ta en runda och återkomma senare
				//lägg in dom tidigare kunderna som ej var missnödja OM det finns plats, släng ut dom annars
				return false;
			}
			
			else
				return barberQueue.add(newCustomer);
		}
		
		else
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
				return barberTime.nextHairCutTime(currentTime);
			case DISSATISFIED:
				return barberTime.nextReturnedDissatisfied(currentTime);
		}
		//Fel om detta nås (eclipse kräver en return här)
		return 0;
	}
	
	public boolean createCustomer() {
		//Kan inte hämta från factory, måste fixas
		if (addCustomer(CustomerFactory.newCustomer()))
			return true;
		return false;
	}
}
