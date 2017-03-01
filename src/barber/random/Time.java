package barber.random;


import java.util.LinkedList;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class Time {

    private ExponentialRandomStream ERS;
    private UniformRandomStream URSHaircut;
    private UniformRandomStream URSDissatisfied;
    private LinkedList<TimeEvent> timeList = new LinkedList<>();

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

    }


    /**
     * Gives the next arrival time for an arrival event.
     *
     * @return The next time for an arrival event as a double.
     */
    public double nextArrivalTime(double currentTime) {
        double nextEventTime = ERS.next();

        /**
         * Iterates for every item in the list.
         *
         * If an event is an arrive event add the time of the new
         * arrive event to the last arrive event.
         *
         * If the current time is greater then than the
         * time from the last event add arrive time to the
         * current time instead.
         */

        for (TimeEvent a : timeList) {
            if (a.eventType == kindOfEvent.ARRIVE && a.time > currentTime) {
                nextEventTime += a.time;
                timeList.addFirst(new TimeEvent(kindOfEvent.ARRIVE, nextEventTime));
                return nextEventTime;
            } else if (a.time < currentTime) {
                nextEventTime += currentTime;
                timeList.addFirst(new TimeEvent(kindOfEvent.ARRIVE, nextEventTime));
                return nextEventTime;
            }
        }
        timeList.addFirst(new TimeEvent(kindOfEvent.ARRIVE, nextEventTime));
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
         * Iterates for every item in the list.
         *
         * If an event is a hair cut event add the time of the new
         * hair cut event to the last hair cut event.
         *
         * If the current time is greater then than the
         * time from the last event add hair cut time to the
         * current time instead.
         */
        for (TimeEvent a : timeList) {
            if (a.eventType == kindOfEvent.HAIR_CUT && a.time > currentTime) {
                nextEventTime += a.time;
                timeList.addFirst(new TimeEvent(kindOfEvent.HAIR_CUT, nextEventTime));
                return nextEventTime;
            } else if (a.time < currentTime) {
                nextEventTime += currentTime;
                timeList.addFirst(new TimeEvent(kindOfEvent.HAIR_CUT, nextEventTime));
                return nextEventTime;
            }
        }
        timeList.addFirst(new TimeEvent(kindOfEvent.HAIR_CUT, nextEventTime));
        return nextEventTime;
    }

    /**
     * Gives the total idle time of the simulation.
     *
     * @return Total idle time.
     * TODO
     */
    //   public double getTotalIdleTime() {}

    /**
     * Gives the next time for an returning customer that was dissatisfied for a dissatisfied event.
     *
     * @return The next time for a dissatisfied customer arrival.
     */
    public double nextReturnedDissatisfied(double currentTime) {
        double nextEventTime = URSDissatisfied.next();

        /**
         * Iterates for every item in the list.
         *
         * If an event is a dissatisfied customer event add the time of the new
         * event to the last dissatisfied customer event.
         *
         * If the current time is greater then than the
         * time from the last event add dissatisfied customer time to the
         * current time instead.
         */
        for (TimeEvent a : timeList) {
            if (a.eventType == kindOfEvent.DISSATISFIED && a.time > currentTime) {
                nextEventTime += a.time;
                timeList.addFirst(new TimeEvent(kindOfEvent.DISSATISFIED, nextEventTime));
                return nextEventTime;
            } else if (a.time < currentTime) {
                nextEventTime += currentTime;
                timeList.addFirst(new TimeEvent(kindOfEvent.DISSATISFIED, nextEventTime));
                return nextEventTime;
            }
        }
        timeList.addFirst(new TimeEvent(kindOfEvent.DISSATISFIED, nextEventTime));
        return nextEventTime;
    }

    /**
     * Removes the TimeEvent for a given time
     *
     * @param time The time of the TimeEvent that gets removed.
     */
    public void removeTime(double time) {
        for (TimeEvent a : timeList)
            if (a.time == time) timeList.remove(a);
    }

    private enum kindOfEvent {
        ARRIVE,
        HAIR_CUT,
        DISSATISFIED
    }

    private class TimeEvent {
        kindOfEvent eventType;
        double time;

        TimeEvent(kindOfEvent eventType, double time) {
            this.eventType = eventType;
            this.time = time;
        }
    }


}
