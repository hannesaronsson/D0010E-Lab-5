package barber.random;

import java.util.Random;

/**
 * Created by Mumrik on 2017-02-27.
 */
public class ExponentialRandomStream {

    private Random rand;
    private double lambda;

    public ExponentialRandomStream(double lambda, long seed) {
        rand = new Random(seed);
        this.lambda = lambda;
    }

    public ExponentialRandomStream(double lambda) {
        rand = new Random();
        this.lambda = lambda;
    }

    public double next() {
        return -Math.log(rand.nextDouble())/lambda;
    }
}
