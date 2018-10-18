package com.example.tpcc1.eventapp.fragment;

import android.app.Notification;
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
import com.example.tpcc1.eventapp.adapter.EventAdapter;
import com.example.tpcc1.eventapp.adapter.NotificationAdapter;
import com.example.tpcc1.eventapp.api.ApiClient;
import com.example.tpcc1.eventapp.api.ApiInterface;
import com.example.tpcc1.eventapp.model.EventResult;
import com.example.tpcc1.eventapp.model.NotificationResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private RecyclerView mListNotification;
    private List<NotificationResult> notifications;
    private NotificationAdapter adapter;
    private ApiInterface api;
    private ProgressDialog mProgressDialog;
    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        mListNotification = (RecyclerView) view.findViewById(R.id.list_notification);
        api = ApiClient.getClient().create(ApiInterface.class);
        getData();
        return view;
    }

    private void getData() {
        Call<List<NotificationResult>> call = api.getNotifications();
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("loading");
        mProgressDialog.show();
        call.enqueue(new retrofit2.Callback<List<NotificationResult>>() {
            @Override
            public void onResponse(Call<List<NotificationResult>> call, Response<List<NotificationResult>> response) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
                notifications = response.body();
                if (notifications != null) {
                    adapter = new NotificationAdapter(getContext(), notifications);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    mListNotification.setLayoutManager(layoutManager);
                    mListNotification.setAdapter(adapter);
                }else {
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<NotificationResult>> call, Throwable t){
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                if (mProgressDialog.isShowing()){
                    mProgressDialog.dismiss();
                }
            }
        });
    }
}
