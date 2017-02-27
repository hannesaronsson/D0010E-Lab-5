package barber.random;


import java.util.Random;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class UniformRandomStream {

    private Random rand;
    private double lower, width;

    /**
     * Constructor
     *
     * @param lower
     *      The lower bounds of the uniform distribution.
     * @param upper
     *      The upper bounds of the uniform distribution.
     * @param seed
     *      The seed for the random generator.
     */
    public UniformRandomStream(double lower, double upper, long seed) {
        rand = new Random(seed);
        this.lower = lower;
        this.width = upper-lower;
    }

    /**
     * Constructor
     *
     * @param lower
     *      The lower bounds of the uniform distribution.
     * @param upper
     *      The upper bounds of the uniform distribution.
     */
    public UniformRandomStream(double lower, double upper) {
        rand = new Random();
        this.lower = lower;
        this.width = upper-lower;
    }

    /**
     * Gives the next double using the seed.
     * @return
     *      Next double using the seed.
     */
    public double next() {
        return lower+rand.nextDouble()*width;
    }
}
