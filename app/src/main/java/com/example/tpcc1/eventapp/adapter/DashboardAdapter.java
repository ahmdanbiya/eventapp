package com.example.tpcc1.eventapp.adapter;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tpcc1.eventapp.R;
import com.example.tpcc1.eventapp.model.Dashboard;
import com.example.tpcc1.eventapp.model.Event;
import com.example.tpcc1.eventapp.model.EventResult;

import java.util.List;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {
    Context context;
    List<EventResult> events;

    public DashboardAdapter(Context context, List<EventResult> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dashboard,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtEventName.setText(events.get(position).getEventname());
        holder.textEventDescription.setText(events.get(position).getEventdesc());
        holder.txtEventLocation.setText(events.get(position).getEventlocation());
        holder.txtEventTime.setText(events.get(position).getStarttime() + " s/d " + events.get(position).getEndtime());
        Glide
                .with(context)
                .load(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+
                        events.get(position).getEventpicture())
                .into(holder.imgEvent);
    }

    @Override
    public int getItemCount() {
        return (events != null) ? events.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView txtEventName, textEventDescription,txtEventLocation,txtEventTime;
        private ImageView imgEvent;

        public ViewHolder(View itemView) {
            super(itemView);
            txtEventName = (TextView) itemView.findViewById(R.id.txt_event_name);
            textEventDescription = (TextView) itemView.findViewById(R.id.txt_event_description);
            txtEventLocation = (TextView) itemView.findViewById(R.id.txt_event_location);
            txtEventTime = (TextView) itemView.findViewById(R.id.txt_event_time);
            imgEvent = (ImageView) itemView.findViewById(R.id.img_event);
        }
    }
}
