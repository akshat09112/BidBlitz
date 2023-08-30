package org.example.service;

import org.example.model.Event;

import java.util.HashMap;
import java.util.HashSet;

public class EventService {
    private static HashMap<String, Event> events;
    private static HashSet<String> dates;

    private static EventService instance;
    private EventService(){
        events=new HashMap<>();
        dates=new HashSet<>();
    }
    public static HashMap<String, Event> getEvents(){
        if(events==null){
            events=new HashMap<>();
        }
        return events;
    }
    public static HashSet<String> getDates(){
        if(dates==null){
            dates=new HashSet<>();
        }
        return dates;
    }
    public static EventService getInstance(){
        if(instance==null){
            return new EventService();
        }
        return instance;
    }

    public String addEvent(String id,String name,String prize,String date){
        if(events.get(id)==null){
            if(dates.contains(date)){
                return "Event already there with date "+date;
            }
            Event event=new Event(id,name,prize,date);
            events.put(id,event);
            return name+" added successfully.";
        }else {
            return "event already added";
        }

    }
}
