package com.example.humraahi;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ActivityInspectionForm extends AppCompatActivity {

    HashMap<String, String> InspectionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inspection_form);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button submitButton = findViewById(R.id.inspection_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InspectionData = new HashMap<>();

                EditText pid = findViewById(R.id.project_id);
                EditText cid = findViewById(R.id.contract_id);
                EditText section = findViewById(R.id.section);
                EditText section_length = findViewById(R.id.section_length);
                EditText cen_line = findViewById(R.id.center_lines);
                EditText grade_peg = findViewById(R.id.grade_peg);
                EditText rem_org = findViewById(R.id.removal_of_organic);
                EditText ex_l = findViewById(R.id.excavation_line);
                EditText s_comp = findViewById(R.id.setu_compaction);
                EditText s_ct_ref = findViewById(R.id.ct_ref);
                EditText s_cbr_ref = findViewById(R.id.cbr_ref);
                EditText s_ga = findViewById(R.id.ga_insetu);
                EditText lower_layer_p = findViewById(R.id.lower_layer_p);
                EditText lower_layer_c = findViewById(R.id.lower_level_c);
                EditText lower_level_c_ref = findViewById(R.id.lower_level_c_ref);
                EditText lower_level_cbr = findViewById(R.id.lower_level_cbr);
                EditText lower_level_ga = findViewById(R.id.lower_level_ga);
                EditText upper_level_p = findViewById(R.id.upper_level_p);
                EditText upper_level_c = findViewById(R.id.upper_level_c);
                EditText upper_level_c_ref = findViewById(R.id.upper_level_c_ref);
                EditText upper_level_cbr = findViewById(R.id.upper_level_cbr);
                EditText upper_level_ga = findViewById(R.id.upper_level_ga);
                EditText imp_gravel_p = findViewById(R.id.imp_gravel_p);
                EditText imp_gravel_c = findViewById(R.id.imp_gravel_c);
                EditText imp_gravel_cbr = findViewById(R.id.imp_gravel_cbr);
                EditText imp_gravel_ga = findViewById(R.id.imp_gravel_ga);
                EditText imp_gravel2_p = findViewById(R.id.imp_gravel2_p);
                EditText imp_gravel2_c = findViewById(R.id.imp_gravel2_c);
                EditText imp_gravel2_cbr = findViewById(R.id.imp_gravel2_cbr);
                EditText imp_gravel2_ga = findViewById(R.id.imp_gravel2_ga);
                EditText crusher_run_p = findViewById(R.id.crusher_run_p);
                EditText crusher_run_c = findViewById(R.id.crusher_run_c);
                EditText crusher_run_c_ref = findViewById(R.id.crusher_run_c_ref);
                EditText slashing = findViewById(R.id.slashing);
                EditText crusher_run_ga = findViewById(R.id.crusher_run_ga);
                EditText final_grading = findViewById(R.id.final_grading);
                EditText level_slopes = findViewById(R.id.level_slopes);
                EditText comp_pre_p = findViewById(R.id.comp_pre_p);
                EditText comp_p = findViewById(R.id.comp_p);
                EditText ga = findViewById(R.id.ga);
                EditText truck_calib = findViewById(R.id.truck_calib);
                EditText torp_primer = findViewById(R.id.torp_primer);
                EditText spread_rate = findViewById(R.id.spread_rate);
                EditText final_ga = findViewById(R.id.final_ga);
                EditText road_length = findViewById(R.id.comp_road_len);
                CheckBox status_comp = findViewById(R.id.status_comp);
                CheckBox status_prog = findViewById(R.id.status_prog);

                InspectionData.put("project_id", pid.getText().toString());
                InspectionData.put("contract_id", cid.getText().toString());
                InspectionData.put("section", section.getText().toString());
                InspectionData.put("section_length", section_length.getText().toString());
                InspectionData.put("center_lines", cen_line.getText().toString());
                InspectionData.put("grade_and_peg", grade_peg.getText().toString());
                InspectionData.put("removal_of_organic_materials", rem_org.getText().toString());
                InspectionData.put("excavation_lines_and_grids", ex_l.getText().toString());
                InspectionData.put("in_situ_compaction", s_comp.getText().toString());
                InspectionData.put("compaction_test_ref", s_ct_ref.getText().toString());
                InspectionData.put("in_situ_cbr_test_ref", s_cbr_ref.getText().toString());
                InspectionData.put("in_situ_grade_acceptance", s_ga.getText().toString());
                InspectionData.put("lower_level_placement_s_grade", lower_layer_p.getText().toString());
                InspectionData.put("lower_level_compaction_s_grade", lower_layer_c.getText().toString());
                InspectionData.put("lower_level_compaction_test_ref", lower_level_c_ref.getText().toString());
                InspectionData.put("lower_level_cbr_and_pl_test_ref", lower_level_cbr.getText().toString());
                InspectionData.put("lower_level_grade_acceptance", lower_level_ga.getText().toString());
                InspectionData.put("upper_level_placement_s_grade", upper_level_p.getText().toString());
                InspectionData.put("upper_level_compaction_s_grade", upper_level_c.getText().toString());
                InspectionData.put("upper_level_compaction_test_ref", upper_level_c_ref.getText().toString());
                InspectionData.put("upper_level_cbr_and_pl_test_ref", upper_level_cbr.getText().toString());
                InspectionData.put("upper_level_grade_acceptance", upper_level_ga.getText().toString());
                InspectionData.put("imp_gravel_placement_s_base", imp_gravel_p.getText().toString());
                InspectionData.put("imp_gravel_compaction_s_base", imp_gravel_c.getText().toString());
                InspectionData.put("imp_gravel_cbr_and_pl_test_ref", imp_gravel_cbr.getText().toString());
                InspectionData.put("imp_gravel_grade_acceptance", imp_gravel_ga.getText().toString());
                InspectionData.put("imp_gravel_wilime_placement_stabile", imp_gravel2_p.getText().toString());
                InspectionData.put("imp_gravel_wilime_compaction_s_grade", imp_gravel2_c.getText().toString());
                InspectionData.put("imp_gravel_wilime_cbr_and_pl_test_ref", imp_gravel2_cbr.getText().toString());
                InspectionData.put("imp_gravel_wilime_grade_acceptance", imp_gravel2_ga.getText().toString());
                InspectionData.put("crusher_run_placement", crusher_run_p.getText().toString());
                InspectionData.put("crusher_run_compaction", crusher_run_c.getText().toString());
                InspectionData.put("crusher_run_compaction_test_ref", crusher_run_c_ref.getText().toString());
                InspectionData.put("slashing", slashing.getText().toString());
                InspectionData.put("crusher_run_grade_acceptance", crusher_run_ga.getText().toString());
                InspectionData.put("final_grading", final_grading.getText().toString());
                InspectionData.put("levels_and_slopes", level_slopes.getText().toString());
                InspectionData.put("complete_pre_placement", comp_pre_p.getText().toString());
                InspectionData.put("complete_placement", comp_p.getText().toString());
                InspectionData.put("complete_kerbing_grade_acceptance", ga.getText().toString());
                InspectionData.put("truck_calibration", truck_calib.getText().toString());
                InspectionData.put("torp_primer", torp_primer.getText().toString());
                InspectionData.put("spread_rate_stone", spread_rate.getText().toString());
                InspectionData.put("final_grade_acceptance", final_ga.getText().toString());
                InspectionData.put("completed_road_length", road_length.getText().toString());

                String status = "None";

                if (status_comp.isChecked()) {
                    status = status_comp.getText().toString();
                } else {
                    status = status_prog.getText().toString();
                }

                InspectionData.put("construction_status", status);

                Log.d("Inspect", InspectionData.toString());

        finish();
            }
        });
    }
}
