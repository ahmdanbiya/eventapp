package com.example.tpcc1.eventapp.model;

public class Dashboard {
    long id;
    String eventName, notes;

    public Dashboard(long id, String eventName, String notes) {
        this.id = id;
        this.eventName = eventName;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventname(String eventName) {
        this.eventName = eventName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
