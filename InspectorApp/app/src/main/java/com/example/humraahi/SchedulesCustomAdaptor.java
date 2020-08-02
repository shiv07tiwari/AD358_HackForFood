package com.example.humraahi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.humraahi.DataModels.ScheduleList;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class SchedulesCustomAdaptor extends RecyclerView.Adapter<SchedulesCustomAdaptor.MyViewHolder> {

    private ScheduleList scheduleList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLocation;
        TextView textViewAssignedDate;
        TextView textViewDeadlineDate;
        TextView textViewProjectId;
        TextView textViewTag;


        public MyViewHolder(View itemView) {
            super(itemView);


            LinearLayout.LayoutParams params = new
                    LinearLayout.LayoutParams(850, ViewGroup.LayoutParams.WRAP_CONTENT);
            itemView.setLayoutParams(params);

            this.textViewProjectId =  itemView.findViewById(R.id.project_id);
            this.textViewLocation =  itemView.findViewById(R.id.location);
            this.textViewAssignedDate = itemView.findViewById(R.id.assigned_date);
            this.textViewDeadlineDate = itemView.findViewById(R.id.deadline);
            this.textViewTag = itemView.findViewById(R.id.tag);
        }
    }

    public SchedulesCustomAdaptor(ScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schedule_card, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewLocationName = holder.textViewLocation;
        TextView textViewAssignedDateName = holder.textViewAssignedDate;
        TextView textViewDeadlineDateName = holder.textViewDeadlineDate;
        TextView textViewProjectIdName = holder.textViewProjectId;
        TextView textViewTagName = holder.textViewTag;

        textViewLocationName.setText(scheduleList.get(listPosition).getLocation());
        textViewAssignedDateName.setText(scheduleList.get(listPosition).getAssignedDate());
        textViewDeadlineDateName.setText(scheduleList.get(listPosition).getDeadline());
        textViewTagName.setText(scheduleList.get(listPosition).getTag());
        textViewProjectIdName.setText(scheduleList.get(listPosition).getProjectId().toString());

    }

    @Override
    public int getItemCount() {
        return scheduleList.getSize();
    }
}