package barber.simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hannesaronsson on 28/02/17.
 */
class EventQueueTest {
    private EventQueue a = new EventQueue();


    @Test
    void testEventQueue() {
        Event b = new Event() {
                @Override
                public void runEvent(SimulatorState state, EventQueue eventQueue) {
                    time = 1;
                }
            };

        Event c = new Event() {
            @Override
            public void runEvent(SimulatorState state, EventQueue eventQueue) {
                time = 2;
            }
        };

        Event d = new Event() {
            @Override
            public void runEvent(SimulatorState state, EventQueue eventQueue) {
                time = 3;
            }
        };

        Event e = new Event() {
            @Override
            public void runEvent(SimulatorState state, EventQueue eventQueue) {
                time = 4;
            }
        };
        b.runEvent(null,null);
        c.runEvent(null,null);
        d.runEvent(null,null);
        e.runEvent(null,null);

        a.addEvent(e);
        a.addEvent(c);
        a.addEvent(d);
        a.addEvent(b);
        assertEquals(4,a.getFirstEvent().time);
        a.sortEvents();
        assertEquals(1,a.getFirstEvent().time);
    }

}