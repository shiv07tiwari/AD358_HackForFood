package com.example.humraahi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.humraahi.DataModels.ScheduleDetails;
import com.example.humraahi.DataModels.ScheduleList;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class SchedulesCustomAdaptor extends RecyclerView.Adapter<SchedulesCustomAdaptor.MyViewHolder> {

    private ScheduleList scheduleList;
    private Context mContext;
    boolean completed;


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLocation;
        TextView textViewAssignedDate;
        TextView textViewDeadlineDate;
        TextView textViewProjectId;
        TextView textViewTag;
        Button startButton;


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
            this.startButton = itemView.findViewById(R.id.start_task);
        }
    }

    public SchedulesCustomAdaptor(ScheduleList scheduleList, Context context, boolean completed) {
        this.mContext = context;
        this.scheduleList = scheduleList;
        this.completed = completed;
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
        final TextView textViewProjectIdName = holder.textViewProjectId;
        final TextView textViewTagName = holder.textViewTag;
        Button startButtonName = holder.startButton;

        textViewLocationName.setText(scheduleList.get(listPosition).getLocation());
        textViewAssignedDateName.setText(scheduleList.get(listPosition).getAssignedDate());
        textViewDeadlineDateName.setText(scheduleList.get(listPosition).getDeadline());
        textViewTagName.setText(scheduleList.get(listPosition).getTag());
        textViewProjectIdName.setText(scheduleList.get(listPosition).getProjectId().toString());

        if(this.completed){
            startButtonName.setVisibility(View.INVISIBLE);
        }

        startButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textViewTagName.getText().equals("Verification")) {
                    Intent i = new Intent(mContext, ActivityVerificationForm.class);
                    i.putExtra("COMP_ID", textViewProjectIdName.getText().toString());
                    mContext.startActivity(i);
                }else{
                    Intent i = new Intent(mContext, ActivityInspectionForm.class);
                    mContext.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.getSize();
    }
}
