package barber.random;

import java.util.Random;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class ExponentialRandomStream {

    private Random rand;
    private double lambda;

    /**
     * Constructor
     *
     * @param lambda Constant that indicates how many costumers that enters every hour on average.
     * @param seed   Seed for random generator.
     */
    public ExponentialRandomStream(double lambda, long seed) {
        rand = new Random(seed);
        this.lambda = lambda;
    }

    /**
     * @param lambda Constant that indicates how many costumers that enters every hour on average.
     */
    public ExponentialRandomStream(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    /**
     * Gives the next double using the seed.
     *
     * @return Next double for the seed.
     */
    public double next() {
        return -Math.log(rand.nextDouble()) / lambda;
    }
}
