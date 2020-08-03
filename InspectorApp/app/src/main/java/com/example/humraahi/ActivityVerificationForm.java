package com.example.humraahi;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
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

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;

public class ActivityVerificationForm extends AppCompatActivity {

    HashMap<String, String> VerificationData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.verification_form);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button submitButton = findViewById(R.id.verification_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner audit = findViewById(R.id.audit);
                EditText roadName = findViewById(R.id.roadName);
                Spinner inspectionCodes = findViewById(R.id.inspectionCodes);
                Spinner remedialCodes = findViewById(R.id.remedialCodes);
                Spinner activities = findViewById(R.id.activities);
                EditText localName = findViewById(R.id.localName);
                CheckBox priority_low = findViewById(R.id.priority_low);
                CheckBox priority_med = findViewById(R.id.priority_med);
                CheckBox priority_high = findViewById(R.id.priority_high);
                CheckBox risk1 = findViewById(R.id.risk1);
                CheckBox risk2 = findViewById(R.id.risk2);
                CheckBox risk3 = findViewById(R.id.risk3);
                CheckBox risk4 = findViewById(R.id.risk4);
                EditText description = findViewById(R.id.description);
                CheckBox stream = findViewById(R.id.stream);
                CheckBox hangingOutlet = findViewById(R.id.handingOutlet);
                CheckBox otherStream = findViewById(R.id.otherStream);
                CheckBox stopSignYes = findViewById(R.id.stopSignYes);
                CheckBox stopSignNo = findViewById(R.id.stopSignNo);
                CheckBox KMYes = findViewById(R.id.KMYes);
                CheckBox KMNo = findViewById(R.id.KMNo);
                CheckBox RPYes = findViewById(R.id.RPYes);
                CheckBox RPNo = findViewById(R.id.RPNo);
                EditText anticipatedTurnout = findViewById(R.id.ant_turnout);
                CheckBox railYes = findViewById(R.id.railYes);
                CheckBox railNo = findViewById(R.id.railNo);
                CheckBox hillYes = findViewById(R.id.hillYes);
                CheckBox hillNo = findViewById(R.id.hillNo);
                CheckBox nryes = findViewById(R.id.narror_roadYes);
                CheckBox nrno = findViewById(R.id.narror_roadNo);
                CheckBox plyes = findViewById(R.id.PL_Yes);
                CheckBox plno = findViewById(R.id.Pl_No);
                EditText roadGrades = findViewById(R.id.roadGrades);
                CheckBox vehicleUsageL = findViewById(R.id.vu_low);
                CheckBox vehicleUsageM = findViewById(R.id.vu_med);
                CheckBox vehicleUsageH = findViewById(R.id.vu_high);

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
                VerificationData.put("Description", description.getText().toString());

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

                Log.d("Verify",VerificationData.toString());

                finish();
            }
        });

    }
}
