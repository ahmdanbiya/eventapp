package com.example.tpcc1.eventapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.api.ApiInterface;
import com.example.tpcc1.eventapp.adapter.EventAdapter;
import com.example.tpcc1.eventapp.api.ApiClient;
import com.example.tpcc1.eventapp.model.EventResult;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mListEvent;
    private List<EventResult> events;
    private EventAdapter adapter;
    private ApiInterface api;
    private ProgressDialog mProgressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mListEvent = (RecyclerView) view.findViewById(R.id.list_event);
        api = ApiClient.getClient().create(ApiInterface.class);
        getData();
        return view;
    }

    private void getData() {
        Call<List<EventResult>> call = api.getEvents();
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("loading");
        mProgressDialog.show();
        call.enqueue(new retrofit2.Callback<List<EventResult>>() {
            @Override
            public void onResponse(Call<List<EventResult>> call, Response<List<EventResult>> response) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                events = response.body();
                if (events != null) {
                    adapter = new EventAdapter(getContext(), events);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mListEvent.setLayoutManager(layoutManager);
                    mListEvent.setAdapter(adapter);
                }else {
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<EventResult>> call, Throwable t){
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
            }
        });
    }
}
