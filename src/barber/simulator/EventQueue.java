package barber.simulator;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * Created by Mumrik on 2017-02-27.
 */
public class EventQueue {
    private ArrayList<Event> eventQueue = new ArrayList<>();


    /**
     * Used to add an event in the eventQueue.
     *
     * @param event The event to be added to the list.
     */
    public void addEvent(Event event) {
        eventQueue.add(event);
        sortEvents();
    }

    /**
     * Used to get the first element in the list eventQueue.
     *
     * @return Returns the first element in the list as an Event.
     */
    public Event getFirstEvent() {
        Event firstEvent = eventQueue.get(0);
        eventQueue.remove(0);
        return firstEvent;
    }


    /**
     * Sorts eventQueue by the time for each event in the list
     * in an descending order.
     */
    private void sortEvents() {
        eventQueue.sort(Comparator.comparing(Event::getTime));
    }


}
