package org.example.model;

public class Event {
    private String id;
    private String event_name;
    private String prize_name;
    private String date;

    public Event(String id, String event_name, String prize_name, String date) {
        this.id = id;
        this.event_name = event_name;
        this.prize_name = prize_name;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getPrize_name() {
        return prize_name;
    }

    public void setPrize_name(String prize_name) {
        this.prize_name = prize_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
