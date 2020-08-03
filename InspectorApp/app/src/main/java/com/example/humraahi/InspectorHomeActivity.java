package com.example.humraahi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.humraahi.DataModels.ScheduleDetails;
import com.example.humraahi.DataModels.ScheduleList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
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

        Button btn = findViewById(R.id.start_task);
        final TextView tag = findViewById(R.id.tag);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag.getText().equals("Verification")) {
                    Intent i = new Intent(InspectorHomeActivity.this, ActivityVerificationForm.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(InspectorHomeActivity.this, ActivityInspectionForm.class);
                    startActivity(i);
                }
            }
        });
        RecyclerView recyclerViewPendingTasks;
        RecyclerView recyclerViewCompletedTasks;


        recyclerViewPendingTasks = findViewById(R.id.schedule_recycler_view);
        recyclerViewPendingTasks.setHasFixedSize(true);

        recyclerViewCompletedTasks = findViewById(R.id.completed_schedule_recycler_view);
        recyclerViewCompletedTasks.setHasFixedSize(true);


        scheduleList = new ScheduleList();
        for (int i = 0; i < 6; i++) {
            scheduleList.addSchedule(new ScheduleDetails("Mahatma Gandhi Road, Delhi", "02-07-2020", "10-07-2020", "Inspection", i+31));
        }

        setTasks(recyclerViewPendingTasks, scheduleList);
        scheduleList = new ScheduleList();
        for (int i = 0; i < 6; i++) {

            scheduleList.addSchedule(new ScheduleDetails("Civil Lines, Delhi", "02-07-2020", "10-07-2020", "Inspection", i+20));
        }
        setTasks(recyclerViewCompletedTasks, scheduleList);

        Button tut = findViewById(R.id.tutorial_button);
        tut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InspectorHomeActivity.this, TutorialActivity.class);
                startActivity(i);
            }
        });
    }

    protected void setTasks(RecyclerView recyclerView, ScheduleList schedules){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SchedulesCustomAdaptor(schedules, this);
        recyclerView.setAdapter(adapter);
    }

}
