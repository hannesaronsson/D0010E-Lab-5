package barber;

import barber.barberShop.BarberPrint;
import barber.barberShop.BarberState;
import barber.barberShop.barber.barberShop.event.CloseEvent;
import barber.barberShop.barber.barberShop.event.StartEvent;
import barber.barberShop.barber.barberShop.event.StopEvent;
import barber.simulator.EventQueue;
import barber.simulator.Simulator;

/**
 * Created by Mumrik on 2017-03-02.
 */
class BarberMain {


    public static void main(String[] args){
        EventQueue eventQueue = new EventQueue();
        BarberState barberState = new BarberState();
        BarberPrint barberPrint = new BarberPrint(barberState);

        eventQueue.addEvent(new StartEvent());
        eventQueue.addEvent(new StopEvent(999));
        eventQueue.addEvent(new CloseEvent(barberState.getCloseTime()));

        barberState.addObserver(barberPrint);
        Simulator s = new Simulator(eventQueue, barberState, barberPrint);
        s.run();
    }
}
