package barber.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mumrik on 2017-03-01.
 */
class TimeTest {

    Time a = new Time(2,123,2,3,4,5);
    double b = a.nextArrivalTime(0);
    double c = a.nextReturnedDissatisfied(b);
    double d = a.nextReturnedDissatisfied(c);
    double e = a.nextArrivalTime(d);
    double f = a.nextHairCutTime(e);
    double g = a.nextHairCutTime(f);
    double h = a.nextArrivalTime(g);





    @Test
    void nextArrivalTime() {
        assertTrue(h>g && g>f && f>e && e>d && d>c && c>b);
    }

}