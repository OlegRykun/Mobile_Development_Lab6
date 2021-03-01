package com.example.ua.kpi.comsys.io8218;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class FragmentPieChart extends Fragment {

    PieChart pieChart;

    public FragmentPieChart() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.piechart_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pieChart = view.findViewById(R.id.piechart);

        pieChart.setUsePercentValues(true);
        //pieChart.setRotationEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 5, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setEntryLabelColor(Color.DKGRAY);
        pieChart.setEntryLabelTextSize(20f);

        pieChart.setTransparentCircleAlpha(0);

        pieChart.setDrawEntryLabels(false);


        //pieChart.setTransparentCircleRadius(60f);

        //pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //chart animation

        ArrayList<PieEntry> values = new ArrayList<>();

        values.add(new PieEntry(25, "Red"));
        values.add(new PieEntry(35, "Green"));
        values.add(new PieEntry(40, "Yellow"));

        PieDataSet dataSet = new PieDataSet(values, "Pie Chart");
        dataSet.setSliceSpace(5f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(Color.RED, Color.GREEN, Color.YELLOW);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(25f);
        data.setValueTextColor(ContextCompat.getColor(getActivity(), R.color.piechartValueTextColor));

        pieChart.setData(data);
    }
}
