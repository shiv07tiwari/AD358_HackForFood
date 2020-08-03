package com.example.humraahi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.humraahi.DataModels.AllReq;
import com.example.humraahi.DataModels.SchServer;
import com.example.humraahi.DataModels.VerificationAdd;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONObject;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityVerificationForm extends AppCompatActivity {

    HashMap<String, String> VerificationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verification_form);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final Spinner audit = findViewById(R.id.audit);
        final EditText roadName = findViewById(R.id.roadName);
        final Spinner inspectionCodes = findViewById(R.id.inspectionCodes);
        final Spinner remedialCodes = findViewById(R.id.remedialCodes);
        final Spinner activities = findViewById(R.id.activities);
        final EditText localName = findViewById(R.id.localName);
        final CheckBox priority_low = findViewById(R.id.priority_low);
        final CheckBox priority_med = findViewById(R.id.priority_med);
        final CheckBox priority_high = findViewById(R.id.priority_high);
        final CheckBox risk1 = findViewById(R.id.risk1);
        final CheckBox risk2 = findViewById(R.id.risk2);
        final CheckBox risk3 = findViewById(R.id.risk3);
        final CheckBox risk4 = findViewById(R.id.risk4);
        final EditText description = findViewById(R.id.description);
        final CheckBox stream = findViewById(R.id.stream);
        final CheckBox hangingOutlet = findViewById(R.id.handingOutlet);
        final CheckBox otherStream = findViewById(R.id.otherStream);
        final CheckBox stopSignYes = findViewById(R.id.stopSignYes);
        final CheckBox stopSignNo = findViewById(R.id.stopSignNo);
        final CheckBox KMYes = findViewById(R.id.KMYes);
        final CheckBox KMNo = findViewById(R.id.KMNo);
        final CheckBox RPYes = findViewById(R.id.RPYes);
        final CheckBox RPNo = findViewById(R.id.RPNo);
        final EditText anticipatedTurnout = findViewById(R.id.ant_turnout);
        final CheckBox railYes = findViewById(R.id.railYes);
        final CheckBox railNo = findViewById(R.id.railNo);
        final CheckBox hillYes = findViewById(R.id.hillYes);
        final CheckBox hillNo = findViewById(R.id.hillNo);
        final CheckBox nryes = findViewById(R.id.narror_roadYes);
        final CheckBox nrno = findViewById(R.id.narror_roadNo);
        final CheckBox plyes = findViewById(R.id.PL_Yes);
        final CheckBox plno = findViewById(R.id.Pl_No);
        final EditText roadGrades = findViewById(R.id.roadGrades);
        final CheckBox vehicleUsageL = findViewById(R.id.vu_low);
        final CheckBox vehicleUsageM = findViewById(R.id.vu_med);
        final CheckBox vehicleUsageH = findViewById(R.id.vu_high);
        Button submitButton = findViewById(R.id.verification_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Log.e("log", "HEREEEEE");
                VerificationData = new HashMap<>();

                VerificationData.put("audit", audit.getSelectedItem().toString());
                VerificationData.put("road_name", roadName.getText().toString());
                VerificationData.put("inspection_codes", inspectionCodes.getSelectedItem().toString());
                VerificationData.put("remedial_codes", remedialCodes.getSelectedItem().toString());
                VerificationData.put("activities", activities.getSelectedItem().toString());
                VerificationData.put("local_name", localName.getText().toString());
                String priority = "";
                if(priority_low.isChecked()) priority = "low";
                else if(priority_med.isChecked()) priority = "medium";
                else if(priority_high.isChecked()) priority = "high";
                else priority = "Not Added";

                VerificationData.put("priority", priority);

                String risk="";

                if(risk1.isChecked()) risk = risk1.getText().toString();
                else if(risk2.isChecked()) risk = risk2.getText().toString();
                else if(risk3.isChecked()) risk = risk3.getText().toString();
                else if(risk4.isChecked()) risk = risk4.getText().toString();
                else risk = "Not Added";

                VerificationData.put("risk", risk);
                VerificationData.put("description", description.getText().toString());

                String streamCulvert = "None";

                if(stream.isChecked()) streamCulvert = stream.getText().toString();
                else if(hangingOutlet.isChecked()) streamCulvert = hangingOutlet.getText().toString();
                else if(otherStream.isChecked()) streamCulvert = otherStream.getText().toString();

                VerificationData.put("stream_culvert", streamCulvert);

                String stopSign = "None";

                if(stopSignYes.isChecked()) stopSign = "Yes";
                else if(stopSignNo.isChecked()) stopSign = "No";

                VerificationData.put("stop_sign", stopSign);

                String KM_board = "None";

                if(KMYes.isChecked()){
                    KM_board = "Yes";
                }else if(KMNo.isChecked()) KM_board = "No";

                VerificationData.put("kilometre_marker_boards", KM_board);

                String RP_board = "None";

                if(RPYes.isChecked()){
                    RP_board = "Yes";
                }else if(RPNo.isChecked()) RP_board = "No";

                VerificationData.put("radio_protocol_signs", RP_board);
                VerificationData.put("anticipated_turnout_constructed", anticipatedTurnout.getText().toString());

                String rail = "None";

                if(railYes.isChecked()){
                    rail = "Yes";
                }else if(railNo.isChecked()) rail = "No";

                VerificationData.put("rail_crossing_present", rail);

                String hill = "None";

                if(hillYes.isChecked()){
                    hill = "Bind corners, Hill crests";
                }else if(hillNo.isChecked()) hill = "No hills";

                VerificationData.put("hill", hill);

                String narrow_road = "None";

                if(nryes.isChecked()){
                    narrow_road = "Yes";
                }else if(nrno.isChecked()) narrow_road = "No";

                VerificationData.put("narrow_road_sections", narrow_road);


                String pl = "None";

                if(plyes.isChecked()){
                    pl = "Yes";
                }else if(plno.isChecked()) pl = "No";

                VerificationData.put("overhead_power_lines", pl);

                VerificationData.put("road_grades", roadGrades.getText().toString());

                String vu_use="None";

                if(vehicleUsageL.isChecked()) vu_use="low";
                else if(vehicleUsageM.isChecked()) vu_use ="medium";
                else if(vehicleUsageH.isChecked()) vu_use="high";

                VerificationData.put("vehicle_usage", vu_use);
                int randomNum = ThreadLocalRandom.current().nextInt(30, 70 + 1);
                VerificationData.put("sub_id", Integer.toString(randomNum));
                randomNum = ThreadLocalRandom.current().nextInt(1, 200);
                VerificationData.put("complaint_id", Integer.toString(randomNum));

                Log.e("log", "Count: "+VerificationData.size());
                // 22
                Log.e("Verify\n",VerificationData.toString());

                if (roadName.getText().toString().equals("Test_Offline")) {
                    Log.e("log", "Not Connected");
                    Toast.makeText(getBaseContext(),"Verification Submitted!", Toast.LENGTH_LONG).show();
                    saveMap(VerificationData);
                    finish();
                } else {
                    Log.e("log", "Connected");
                    clearMap();
                    NetworkService apiService =
                            APIClient.getClient().create(NetworkService.class);
                    VerificationAdd a = new VerificationAdd("add", VerificationData);
                    Call<String> call = apiService.addComplaint(a);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Toast.makeText(getBaseContext(),"Verification Submitted!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                }

            }
        });
    }

    private void saveMap(HashMap<String,String> inputMap){
        SharedPreferences pSharedPref = getApplicationContext().getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null){
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
            editor.remove("My_map").apply();
            editor.putString("My_map", jsonString);
            editor.commit();
        }
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
