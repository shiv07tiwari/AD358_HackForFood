package com.example.humraahi.DataModels;

import com.example.humraahi.DataModels.ScheduleDetails;

import java.util.ArrayList;

public class ScheduleList {

    ArrayList<ScheduleDetails> Schedules;

    public ScheduleList(){
        this.Schedules = new ArrayList<>();
    }

    public void addSchedule(ScheduleDetails sd){
        this.Schedules.add(sd);
    }

    public ScheduleDetails get(int index){
        return Schedules.get(index);
    }

    public int getSize(){
        return Schedules.size();
    }

}
