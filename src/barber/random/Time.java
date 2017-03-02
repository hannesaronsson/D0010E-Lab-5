package barber.random;


/**
 * This object returns times for specific events using Exponential and Uniform random streams.
 *
 * @author Hannes Aronsson on 2017-02-27.
 */
public class Time {

    private ExponentialRandomStream ERS;
    private UniformRandomStream URSHaircut;
    private UniformRandomStream URSDissatisfied;
    private double lastArrivalTime;
    private double lastHairCutTime;
    private double lastDissatisfiedTime;
    private double totalIdleTime;

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
        resetTime();
    }


    /**
     * Gives the next arrival time for an arrival event.
     *
     * @return The next time for an arrival event as a double.
     */
    public double nextArrivalTime(double currentTime) {
        double nextEventTime = ERS.next();

        /**
         * If the current time is greater then the time from the last event
         * add the time for the next event time to the current time
         */
        if (currentTime > lastArrivalTime) {
            nextEventTime += currentTime;
            totalIdleTime += nextEventTime - lastArrivalTime;
            lastArrivalTime = nextEventTime;
        } else {
            nextEventTime += lastArrivalTime;
            totalIdleTime += nextEventTime - lastArrivalTime;
            lastArrivalTime = nextEventTime;
        }
        return nextEventTime;
    }

    /**
     * Gives the total queue time for the simulation.
     *
     * @return Total queue time.
     * TODO
     */
    //public double getTotalQueueTime() {return totalQueueTime;}

    /**
     * Gives the next hair cut time for a hair cut event.
     *
     * @return The next time for a hair cut.
     */
    public double nextHairCutTime(double currentTime) {
        double nextEventTime = URSHaircut.next();

        /**
         * If the current time is greater then the time from the last event
         * add the time for the next event time to the current time
         */
        if (currentTime > lastHairCutTime) {
            nextEventTime += currentTime;
            totalIdleTime += nextEventTime - lastHairCutTime;
            lastHairCutTime = nextEventTime;
        } else {
            nextEventTime += lastHairCutTime;
            totalIdleTime += nextEventTime - lastHairCutTime;
            lastHairCutTime = nextEventTime;
        }
        return nextEventTime;
    }

    /**
     * Gives the total idle time of the simulation.
     *
     * @return Total idle time.
     * TODO
     */
    public double getTotalIdleTime() {
        return totalIdleTime;
    }

    /**
     * Gives the next time for an returning customer that was dissatisfied for a dissatisfied event.
     *
     * @return The next time for a dissatisfied customer arrival.
     */
    public double nextReturnedDissatisfied(double currentTime) {
        double nextEventTime = URSDissatisfied.next();


        /**
         * If the current time is greater then the time from the last event
         * add the time for the next event time to the current time
         */
        if (currentTime > lastDissatisfiedTime) {
            nextEventTime += currentTime;
            totalIdleTime += nextEventTime - lastDissatisfiedTime;
            lastDissatisfiedTime = nextEventTime;
        } else {
            nextEventTime += lastDissatisfiedTime;
            totalIdleTime += nextEventTime - lastDissatisfiedTime;
            lastDissatisfiedTime = nextEventTime;
        }

        return nextEventTime;
    }

    /**
     * Resets the time to 0
     */
    public void resetTime() {
        lastArrivalTime = 0;
        lastDissatisfiedTime = 0;
        lastHairCutTime = 0;
        totalIdleTime = 0;
    }

}




