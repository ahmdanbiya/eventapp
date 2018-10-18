package com.example.tpcc1.eventapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.adapter.DashboardAdapter;
import com.example.tpcc1.eventapp.db.DatabaseHelper;
import com.example.tpcc1.eventapp.model.Dashboard;
import com.example.tpcc1.eventapp.model.EventResult;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private  RecyclerView mListEvent;
    private List<EventResult> events;
    private DashboardAdapter adapter;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getActivity());
        mListEvent = (RecyclerView) view.findViewById(R.id.list_event);
        List<EventResult> events = databaseHelper.getAllEvents();
        Log.d("Events", events.size()+"");
        adapter = new DashboardAdapter(getContext(),events);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mListEvent.setLayoutManager(layoutManager);
        mListEvent.setAdapter(adapter);
        return view;
    }
}
