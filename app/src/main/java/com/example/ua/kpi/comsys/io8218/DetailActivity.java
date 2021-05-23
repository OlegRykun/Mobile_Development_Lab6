package com.example.ua.kpi.comsys.io8218;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private String filmPoster;
    private String filmName;
    private String filmYear;
    private String filmGenre;
    private String filmDirector;
    private String filmWriter;
    private String filmActors;
    private String filmCountry;
    private String filmLanguage;
    private String filmProduction;
    private String filmRelease;
    private String filmRuntime;
    private String filmAwards;
    private String filmRating;
    private String filmPlot;

    Film film;

    ImageView poster;
    TextView title, year, genre, director, writer, actors;
    TextView country, language, production, release, runtime;
    TextView awards, rating, plot;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.film_detail);

        poster = findViewById(R.id.film_image);
        title = findViewById(R.id.film_title);
        year = findViewById(R.id.film_year);
        genre = findViewById(R.id.film_genre);
        director = findViewById(R.id.film_director);
        writer = findViewById(R.id.film_writer);
        actors = findViewById(R.id.film_actors);
        country = findViewById(R.id.film_country);
        language = findViewById(R.id.film_language);
        production = findViewById(R.id.film_production);
        release = findViewById(R.id.film_release);
        runtime = findViewById(R.id.film_runtime);
        awards = findViewById(R.id.film_awards);
        rating = findViewById(R.id.film_rating);
        plot = findViewById(R.id.film_plot);

        getDataOfSelectedFilm();
        setValues();
    }

    private void getDataOfSelectedFilm() {
        filmPoster = getIntent().getStringExtra("filmPoster");
        filmName = getIntent().getStringExtra("filmName");
        filmYear = getIntent().getStringExtra("filmYear");
        filmGenre = getIntent().getStringExtra("filmGenre");
        filmDirector = getIntent().getStringExtra("filmDirector");
        filmWriter = getIntent().getStringExtra("filmWriter");
        filmActors = getIntent().getStringExtra("filmActors");
        filmCountry = getIntent().getStringExtra("filmCountry");
        filmLanguage = getIntent().getStringExtra("filmLanguage");
        filmProduction = getIntent().getStringExtra("film");
        filmRelease = getIntent().getStringExtra("filmRelease");
        filmRuntime = getIntent().getStringExtra("filmRuntime");
        filmAwards = getIntent().getStringExtra("filmAwards");
        filmRating = getIntent().getStringExtra("filmRating");
        filmPlot = getIntent().getStringExtra("filmPlot");
    }

    private void setValues() {
//        poster.setImageResource(filmPoster);
        Glide.with(this).load(filmPoster).into(poster);

        title.setText(filmName);
        year.setText(filmYear);
        genre.setText(filmGenre);
        director.setText(filmDirector);
        writer.setText(filmWriter);
        actors.setText(filmActors);
        country.setText(filmCountry);
        language.setText(filmLanguage);
        production.setText(filmProduction);
        release.setText(filmRelease);
        runtime.setText(filmRuntime);
        awards.setText(filmAwards);
        rating.setText(filmRating);
        plot.setText(filmPlot);
    }
}
