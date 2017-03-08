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
    private double totalHairCutTime;


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
        this.URSHaircut = new UniformRandomStream(lowerHairCut, upperHairCut, seed);
        this.URSDissatisfied = new UniformRandomStream(lowerDissatisfied, upperDissatisfied, seed);
    }


    /**
     * Gives the next arrival time for an ArrivedEvent.
     *
     * @return The next time for an arrival event as a double.
     */
    public double nextArrivalTime(double currentTime) {
        double nextEventTime = ERS.next();
        nextEventTime += currentTime;
        return nextEventTime;
    }

    /**
     * Gives the next hair cut time for a StartHaircutEvent.
     *
     * @return The next time for a hair cut.
     */
    public double nextHairCutTime(double currentTime) {
        double nextTime = URSHaircut.next();
        totalHairCutTime += nextTime;
        nextTime += currentTime;
        return nextTime;
    }

    /**
     * Gives the next time for an returning customer that was dissatisfied for a DissatisfiedEvent.
     *
     * @return The next time for a dissatisfied customer arrival.
     */
    public double nextReturnedDissatisfied(double currentTime) {
        double nextTime = URSDissatisfied.next();
        nextTime += currentTime;
        return nextTime;

    }

    public double getTotalHairCutTime() {
        return totalHairCutTime;
    }
}




