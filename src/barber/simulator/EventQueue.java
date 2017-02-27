package barber.simulator;

import barber.barberShop.barber.barberShop.event.ArrivedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Mumrik on 2017-02-27.
 */
public class EventQueue {
    private ArrayList<Event> eventQueue = new ArrayList<>();


    public void addEvent(Event event){
        eventQueue.add(event);

    }

    public Event getFirstEvent(){
        Event firstEvent = eventQueue.get(0);
        eventQueue.remove(0);
        return firstEvent;
    }

    void sortEvents(){
        Collections.sort(eventQueue, new Comparator<Event>() {
            @Override
            public int compare(Event eventOne, Event eventTwo) {
              if (eventTwo.time>eventOne.time) return -1;
              if (eventTwo.time>eventOne.time) return 1;
              else return 0;

            }
        });
    }


}
