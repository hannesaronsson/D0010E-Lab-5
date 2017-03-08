package barber.simulator;

import java.util.ArrayList;
import java.util.Collections;
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
     * Used to get an element at a certain index in the eventQueue.
     * 
     * @param index The index
     * @return Returns element at index
     */
    public Event getEvent(int index) {
    	return eventQueue.get(index);
    }
    
    /**
     * Used to get the size of the eventQueue
     * 
     * @return Size of the list as an int
     */
    public int getSize() {
    	return eventQueue.size();
    }

    /**
     * Sorts eventQueue by the time for each event in the list
     * in an descending order.
     */
    void sortEvents() {
        Collections.sort(eventQueue, new Comparator<Event>() {
            @Override
            public int compare(Event eventOne, Event eventTwo) {
                if (eventTwo.getTime() > eventOne.getTime()) return -1;
                if (eventTwo.getTime() > eventOne.getTime()) return 1;
                else return 0;

            }
        });
    }


}
