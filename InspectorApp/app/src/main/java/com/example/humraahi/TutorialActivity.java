package com.example.humraahi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class TutorialActivity extends AppCompatActivity {
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ViewPager2 v = findViewById(R.id.pager);
        ViewPagerAdapter va = new ViewPagerAdapter();
        v.setAdapter(va);
    }
}
