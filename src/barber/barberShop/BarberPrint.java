package barber.barberShop;

import barber.barberShop.barber.barberShop.event.BarberEvent;
import barber.simulator.SimulatorPrint;
import barber.simulator.SimulatorState;

import java.util.Observable;

/**
 * Prints data in the console.
 *
 * @author hannesaronsson
 */
public class BarberPrint extends SimulatorPrint {
    private BarberState barberState;
    
    /**
     * Constructor.
     * 
     * @param simulatorState
     */
    public BarberPrint(SimulatorState simulatorState) {
        super(simulatorState);
        barberState = (BarberState) simulatorState;
    }

    /**
     * When notified by the observable object, prints information about the current state and the type of event that has
     * been executed.
     */
    public void update(Observable o, Object arg) {
        BarberEvent currentEvent = (BarberEvent) arg;
        if (currentEvent.getType() == EventType.START || currentEvent.getType() == EventType.STOP || currentEvent.getType() == EventType.CLOSED)
            System.out.printf("%6s %-9s%n", formatFloatToString(currentEvent.getTime()), currentEvent.getType());

        else {
            System.out.printf("%6s %-9s %3s %5s %8s %7s %7s %7s %7s %7s%n", formatFloatToString(currentEvent.getTime()), currentEvent.getType(),
                    currentEvent.getCustomer().getCustomerID(), barberState.chairs, formatFloatToString(barberState.totalIdleTime),
                    formatFloatToString(barberState.totalQueueTime), barberState.getCurrentQueueSize(), barberState.numberOfCustomers,
                    barberState.numberOfLostCustomers, barberState.numberOfReturnedCustomers);

        }
    }

    /**
     * Prints information about the barber shop before any events have executed.
     */
    public void firstPrint() {
        System.out.printf(" Close Time = %.2f%n QueueCapacity = %d%n Number of chairs = %d%n" +
                        " Lambda = %.2f%n Seed = %d%n Hair Cut bounds = (%.2f,%.2f)%n " +
                        "Return bounds = (%.2f,%.2f)%n Risk dissatisfied returns = %.2f%n",
                barberState.closeTime, barberState.queueCapacity, barberState.chairs, barberState.lambda,
                barberState.seed, barberState.lowerHairCut, barberState.upperHairCut, barberState.lowerDissatisfied,
                barberState.upperDissatisfied, barberState.getP());
        System.out.println(" ----------------------------------------------------------------------------");
        System.out.printf(" -%4s %3s %7s %7s %7s %7s %7s %7s %7s %7s-%n", "Time", "Event", "ID", "Idle", "TIdle", "TWait", "InQ", "Cut"
                , "Lost", "Ret");


    }

    /**
     * Prints information and statistics about the barber shop after the StopEvent has executed.
     */
    public void lastPrint() {
        System.out.println(" ----------------------------------------------------------------------------");
        System.out.printf(" Hair cuts = %d%n Average cutting time = %.2f%n Average queueing time %.2f%n" +
                        " Largest Queue Size = %d%n Customers not cut = %d%n Dissatisfied customers = %d%n " +
                        "Time chairs were idle %.2f%n",
                barberState.numberOfHairCuts, barberState.totalCuttingTime / barberState.numberOfCustomers,
                barberState.totalQueueTime / barberState.numberOfHairCuts, barberState.largestQueue,
                barberState.numberOfLostCustomers, barberState.numberOfReturnedCustomers, barberState.totalIdleTime);

    }

    private String formatFloatToString(double a) {
        return String.format("%3.2f", a);
    }
}
