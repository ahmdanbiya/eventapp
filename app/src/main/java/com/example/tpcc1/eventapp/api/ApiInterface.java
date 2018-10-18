package com.example.tpcc1.eventapp.api;

import com.example.tpcc1.eventapp.model.EventResult;
import com.example.tpcc1.eventapp.model.NotificationResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("event")
    Call<List<EventResult>> getEvents();
    @GET("event/notification")
    Call<List<NotificationResult>> getNotifications();
}
