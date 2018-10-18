package com.example.tpcc1.eventapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EventResult implements Serializable{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("eventname")
    @Expose
    private String eventname;
    @SerializedName("eventtypeid")
    @Expose
    private String eventtypeid;
    @SerializedName("eventlocation")
    @Expose
    private String eventlocation;
    @SerializedName("eventdesc")
    @Expose
    private String eventdesc;
    @SerializedName("starttime")
    @Expose
    private String starttime;
    @SerializedName("endtime")
    @Expose
    private String endtime;
    @SerializedName("eventlatitude")
    @Expose
    private String eventlatitude;
    @SerializedName("eventlongitude")
    @Expose
    private String eventlongitude;
    @SerializedName("eventpicture")
    @Expose
    private String eventpicture;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("eventquota")
    @Expose
    private String eventquota;
    @SerializedName("typename")
    @Expose
    private String typename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventtypeid() {
        return eventtypeid;
    }

    public void setEventtypeid(String eventtypeid) {
        this.eventtypeid = eventtypeid;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }

    public String getEventdesc() {
        return eventdesc;
    }

    public void setEventdesc(String eventdesc) {
        this.eventdesc = eventdesc;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEventlatitude() {
        return eventlatitude;
    }

    public void setEventlatitude(String eventlatitude) {
        this.eventlatitude = eventlatitude;
    }

    public String getEventlongitude() {
        return eventlongitude;
    }

    public void setEventlongitude(String eventlongitude) {
        this.eventlongitude = eventlongitude;
    }

    public String getEventpicture() {
        return eventpicture;
    }

    public void setEventpicture(String eventpicture) {
        this.eventpicture = eventpicture;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEventquota() {
        return eventquota;
    }

    public void setEventquota(String eventquota) {
        this.eventquota = eventquota;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

}