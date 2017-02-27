package barber.random;


import java.util.Vector;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Time {

    private ExponentialRandomStream ERS;
    private UniformRandomStream URSHaircut;
    private UniformRandomStream URSDissatisfied;
    private Vector<Double> timeList = new Vector<>();
    private double nextEventTime = 0;
    private double totalIdleTime;
    private double totalQueueTime;


    /**
     * Constructor
     *
     * @param lambda            Constant that indicates how many costumers that enters every hour on average.
     * @param seed              Seed for random generator.
     * @param lowerHairCut      The lower bound of the time it takes for a haircut.
     * @param upperHairCut      The upper bound of the time it takes for a haircut.
     * @param lowerDissatisfied The lower bound of the time it takes for a dissatisfied customer to return.
     * @param upperDissatisfied The upper bound of the time it takes for a dissatisfied customer to return.
     */
    public Time(double lambda, long seed, double lowerHairCut, double upperHairCut,
                double lowerDissatisfied, double upperDissatisfied) {
        this.ERS = new ExponentialRandomStream(lambda, seed);
        this.URSHaircut = new UniformRandomStream(lowerHairCut, upperHairCut);
        this.URSDissatisfied = new UniformRandomStream(lowerDissatisfied, upperDissatisfied);
        timeList.add((double) 0);
    }

    /**
     * Gives the next arrival time for an arrival event.
     *
     * @return The next time for an arrival event as a double.
     */
    public double nextArrivalTime() {
        double timeToNextEvent = ERS.next();
        totalIdleTime += timeList.lastElement() + timeToNextEvent;
        nextEventTime += timeToNextEvent;
        timeList.add(nextEventTime);
        return nextEventTime;
    }

    /**
     * Gives the total idle time of the simulation.
     *
     * @return Total idle time.
     */
    public double getTotalIdleTime() {
        return totalIdleTime;
    }

    /**
     * Gives the total queue time for the simulation.
     *
     * @return Total queue time.
     */
    public double getTotalQueueTime() {
        return totalQueueTime;
    }

    /**
     * Gives the next hair cut time for a hair cut event.
     *
     * @return The next time for a hair cut.
     */
    public double nextHairCutTime() {
        double timeToNextEvent = URSHaircut.next();
        totalIdleTime += timeList.lastElement() + timeToNextEvent;
        nextEventTime += timeToNextEvent;
        timeList.add(nextEventTime);
        return nextEventTime;
    }

    /**
     * Gives the next time for an returning customer that was dissatisfied for a dissatisfied event.
     *
     * @return The next time for a dissatisfied customer arrival.
     */
    public double nextReturnedDissatisfied() {
        double timeToNextEvent = URSDissatisfied.next();
        totalIdleTime += timeList.lastElement() + timeToNextEvent;
        nextEventTime += timeToNextEvent;
        timeList.add(nextEventTime);
        return nextEventTime;
    }

    //TODO: QueueTime


}
