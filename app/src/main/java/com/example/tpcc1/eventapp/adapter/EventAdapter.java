package com.example.tpcc1.eventapp.adapter;

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
import com.example.tpcc1.eventapp.DetailEventActivity;
import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.model.Event;
import com.example.tpcc1.eventapp.model.EventResult;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EventResult> events;

    public EventAdapter(Context context, List<EventResult> events){
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_event,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position){
        holder.txtEventName.setText(events.get(position).getEventname());
        holder.textEventDesc.setText(events.get(position).getEventdesc());
        holder.txtEventPlace.setText(events.get(position).getEventlocation());
        holder.txtTime.setText(events.get(position).getStarttime()+"s/d"+events.get(position).getEndtime());
        Glide
                .with(context)
                .load(events.get(position).getEventpicture())
                .into(holder.imgEvent);
        holder.imgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailEventActivity.class);
                intent.putExtra("event",events.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return (events !=null) ? events.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtEventName, textEventDesc, txtEventPlace,txtTime;
        private ImageView imgEvent;

        public ViewHolder(View itemView){
            super(itemView);
            txtEventName = (TextView) itemView.findViewById(R.id.txt_event_name);
            textEventDesc = (TextView) itemView.findViewById(R.id.txt_event_description);
            txtEventPlace = (TextView) itemView.findViewById(R.id.txt_event_place);
            txtTime = (TextView) itemView.findViewById(R.id.txt_time);
            imgEvent = (ImageView) itemView.findViewById(R.id.img_event);
        }
    }
}
