package com.example.ua.kpi.comsys.io8218;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class FragmentGraph extends Fragment {

    private LineGraphSeries<DataPoint> series;

    public FragmentGraph() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.graph_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        double x,y;
        x = -5;

        GraphView graph = view.findViewById(R.id.graph);
        series = new LineGraphSeries<>();

        int numDataPoints = 50;
        for (int i = 0; i < numDataPoints; i++){
            y = Math.pow(x, 2);
            series.appendData(new DataPoint(x, y), true, 50);
            x += 0.25;
        }

        graph.addSeries(series);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-5.0);
        graph.getViewport().setMaxX(5.0);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-2.0);
        graph.getViewport().setMaxY(26.0);
    }
}
