package com.example.humraahi;

import android.os.Bundle;

import com.example.humraahi.DataModels.ScheduleDetails;
import com.example.humraahi.DataModels.ScheduleList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InspectorHomeActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ScheduleList scheduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_inspector);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerViewPendingTasks;
        RecyclerView recyclerViewCompletedTasks;


        recyclerViewPendingTasks = findViewById(R.id.schedule_recycler_view);
        recyclerViewPendingTasks.setHasFixedSize(true);

        recyclerViewCompletedTasks = findViewById(R.id.completed_schedule_recycler_view);
        recyclerViewCompletedTasks.setHasFixedSize(true);


        scheduleList = new ScheduleList();
        for (int i = 0; i < 6; i++) {

            scheduleList.addSchedule(new ScheduleDetails("Hello", "02-07-2020", "10-07-2020", "Inspection", i));
        }

        setTasks(recyclerViewPendingTasks, scheduleList);
        setTasks(recyclerViewCompletedTasks, scheduleList);
    }

    protected void setTasks(RecyclerView recyclerView, ScheduleList schedules){


        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        adapter = new SchedulesCustomAdaptor(schedules);
        recyclerView.setAdapter(adapter);


    }

}
