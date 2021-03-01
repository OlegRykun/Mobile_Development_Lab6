package com.example.ua.kpi.comsys.io8218;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentInfo(), "");
        adapter.AddFragment(new FragmentGraph(), "");
        adapter.AddFragment(new FragmentPieChart(), "");
        adapter.AddFragment(new FragmentFilms(), "");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_info);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_graph);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_pie_chart);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_films);

        //Shadow removing
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}