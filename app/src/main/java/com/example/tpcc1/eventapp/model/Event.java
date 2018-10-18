package com.example.tpcc1.eventapp.model;

public class Event {
    long id;
    String eventName;
    String eventDescription;
    String eventPlace;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public Event(long id, String eventName, String eventDescription, String eventPlace) {
        this.id = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventPlace = eventPlace;
    }
}
