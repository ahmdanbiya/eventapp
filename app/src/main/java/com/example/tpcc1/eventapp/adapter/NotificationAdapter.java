package com.example.tpcc1.eventapp.adapter;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tpcc1.eventapp.fragment.NotificationFragment;
import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.model.EventResult;
import com.example.tpcc1.eventapp.model.NotificationResult;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    Context context;
    List<NotificationResult> notifications;

    public NotificationAdapter(Context context, List<NotificationResult> Notifications){
        this.context = context;
        this.notifications = Notifications;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notification,parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, final int position){
        holder.txtTitle.setText(notifications.get(position).getTitle());
        holder.txtMessage.setText(notifications.get(position).getMessage());
        Glide
                .with(context)
                .load(notifications.get(position).getEventpicture())
                .into(holder.imgNotification);

        }


    @Override
    public int getItemCount(){
        return (notifications !=null) ? notifications.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtMessage;
        private ImageView imgNotification;

        public ViewHolder(View itemView){
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtMessage = (TextView) itemView.findViewById(R.id.txt_message);
            imgNotification = (ImageView) itemView.findViewById(R.id.img_notification);
        }
    }
}
