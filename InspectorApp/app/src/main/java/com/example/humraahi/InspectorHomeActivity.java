package com.example.humraahi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.humraahi.DataModels.AllReq;
import com.example.humraahi.DataModels.SchServer;
import com.example.humraahi.DataModels.ScheduleDetails;
import com.example.humraahi.DataModels.ScheduleList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InspectorHomeActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    ProgressBar _pro;
    private static ScheduleList scheduleList;
    private static ScheduleList scheduleList2;
    LinearLayout sync;
    ScrollView _scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_inspector);


        _pro = findViewById((R.id.progress_circular));
        sync = findViewById(R.id.sync_view);
        _scroll = findViewById(R.id.scroll_view);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Button btn = findViewById(R.id.start_task);
        final TextView tag = findViewById(R.id.tag);

        HashMap<String, String> _offline_map = loadMap();
        Log.e("HashMap", String.valueOf(_offline_map));

        if (!_offline_map.isEmpty()) {
            Log.e("log", "HANDLER AAYA HANDLER");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sync.setVisibility(View.GONE);
                    _pro.setVisibility(View.VISIBLE);
                    fetchData();
                    clearMap();
                }
            }, 5000);
        } else {
            sync.setVisibility(View.GONE);
            _pro.setVisibility(View.VISIBLE);
            fetchData();
        }


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


        Button _button = findViewById(R.id.tutorial_button);
        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InspectorHomeActivity.this, TutorialActivity.class);
                startActivity(i);

            }
        });

        Button _button_ = findViewById(R.id.noti_button);
        _button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InspectorHomeActivity.this, NotiActivity.class);
                startActivity(i);

            }
        });
//        scheduleList = new ScheduleList();
//        for (int i = 0; i < 6; i++) {
//            scheduleList.addSchedule(new ScheduleDetails("Mahatma Gandhi Road, Delhi", "02-07-2020", "10-07-2020", "Inspection", i+31));
//        }
//
//        setTasks(recyclerViewPendingTasks, scheduleList);
//        scheduleList = new ScheduleList();
//        for (int i = 0; i < 6; i++) {
//
//            scheduleList.addSchedule(new ScheduleDetails("Civil Lines, Delhi", "02-07-2020", "10-07-2020", "Inspection", i+20));
//        }
//        setTasks(recyclerViewCompletedTasks, scheduleList);
//
//        Button tut = findViewById(R.id.tutorial_button);
//        tut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(InspectorHomeActivity.this, TutorialActivity.class);
//                startActivity(i);
//            }
//        });
    }

    protected void setTasks(RecyclerView recyclerView, ScheduleList schedules, boolean completed){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SchedulesCustomAdaptor(schedules, this, completed);
        recyclerView.setAdapter(adapter);
    }

    private HashMap<String,String> loadMap(){
        HashMap<String,String> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        try{
            if (pSharedPref != null){
                String jsonString = pSharedPref.getString("My_map", (new JSONObject()).toString());
                JSONObject jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                while(keysItr.hasNext()) {
                    String key = keysItr.next();
                    String value = (String) jsonObject.get(key);
                    outputMap.put(key, value);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return outputMap;
    }

    void fetchData() {
        final RecyclerView recyclerViewPendingTasks;
        final RecyclerView recyclerViewCompletedTasks;
        recyclerViewPendingTasks = findViewById(R.id.schedule_recycler_view);
        recyclerViewPendingTasks.setHasFixedSize(true);
        recyclerViewCompletedTasks = findViewById(R.id.completed_schedule_recycler_view);
        recyclerViewCompletedTasks.setHasFixedSize(true);
        scheduleList = new ScheduleList();
        scheduleList2 = new ScheduleList();
        final Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());


        NetworkService apiService =
                APIClient.getClient().create(NetworkService.class);
        AllReq a = new AllReq("officer_get", 3);
        Call<ArrayList<SchServer>> call = apiService.getComplaints(a);
        call.enqueue(new Callback<ArrayList<SchServer>>() {
            @Override
            public void onResponse(Call<ArrayList<SchServer>> call, Response<ArrayList<SchServer>> response) {
                Log.e("log", call.request().url().toString());
                Log.e("Log", String.valueOf(response.body()));
                List<SchServer> list = response.body();
                for (SchServer s : list) {
                    // String location, String assignedDate, String type, String deadline, String tag, Integer pid
                    String add = "";
                    try {
                        add = geocoder.getFromLocation(s.getLat(), s.getLng(), 1).get(0).getLocality();
                        if (add == null) {
                            add += geocoder.getFromLocation(s.getLat(), s.getLng(), 1).get(0).getSubLocality();
                        }
                        if (add.equals("nullnull")) {
                            add = "MG Road, Indore";
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String tag = (s.is_verified())?"Inspection":"Verification";
                    ScheduleDetails sch_ = new ScheduleDetails(add, "02-07-2020", s.getDefect_type(),
                            "10-10-2020", tag, s.getRoad_id());
                    Log.e("Log", "STATUS: "+s.is_resolved());
                    if (s.is_resolved()) {
                        scheduleList2.addSchedule(sch_);
                    } else {
                        scheduleList.addSchedule(sch_);
                    }
                }
                Log.e("log", "List1: "+scheduleList.getSize());
                Log.e("log", "List12: "+scheduleList2.getSize());
                setTasks(recyclerViewPendingTasks, scheduleList, false);
                setTasks(recyclerViewCompletedTasks, scheduleList2, true);
                _scroll.setVisibility(View.VISIBLE);
                _pro.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ArrayList<SchServer>> call, Throwable t) {
                Log.e("log", call.request().url().toString());
                Log.e("ERROR2", t.toString());
            }
        });
    }
    private void clearMap() {
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null){
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("My_map").apply();
            editor.commit();
        }
    }

}





