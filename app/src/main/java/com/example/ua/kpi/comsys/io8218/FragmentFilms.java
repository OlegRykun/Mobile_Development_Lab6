package com.example.ua.kpi.comsys.io8218;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentFilms extends Fragment {

    View v;
    private RecyclerView myRecycleView;
    private List<Film> listFilm;

    public FragmentFilms() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.films_fragment, container, false);
        myRecycleView = v.findViewById(R.id.films_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), listFilm);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycleView.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listFilm = new ArrayList<>();

        listFilm.add(new Film("Long title that want to break your layout. " +
                "Long title that want to break your layout. " +
                "Long title that want to break your layout. Long title that want to break your layout",
                "2020", "test", R.color.noPoster));
        listFilm.add(new Film("Star Wars: Episode IV - A New Hope Star Wars: Episode IV - A New Hope", "1977",  "movie", R.drawable.poster_01));
        listFilm.add(new Film("Star Wars: Episode V - The Empire Strikes Back", "1980", "movie", R.drawable.poster_02));
        listFilm.add(new Film("Star Wars: Episode VI - Return of the Jedi", "1983", "movie", R.drawable.poster_03));
        listFilm.add(new Film("Star Wars: Episode VII - The Force Awakens", "", "movie", R.color.noPoster));
        listFilm.add(new Film("Star Wars: Episode I - The Phantom Menace", "1999", "movie", R.drawable.poster_05));
        listFilm.add(new Film("Star Wars: Episode III - Revenge of the Sith", "2005", "movie", R.drawable.poster_06));
        listFilm.add(new Film("Star Wars: Episode II - Attack of the Clones", "2002", "movie", R.drawable.poster_07));
        listFilm.add(new Film("Star Trek", "2009", "movie", R.drawable.poster_08));
        listFilm.add(new Film("Star Wars: Episode VIII - The Last Jedi", "2017", "", R.color.noPoster));
        listFilm.add(new Film("Rogue One: A Star Wars Story", "2016", "movie", R.drawable.poster_10));
    }
}
