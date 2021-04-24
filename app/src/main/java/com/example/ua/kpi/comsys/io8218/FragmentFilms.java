package com.example.ua.kpi.comsys.io8218;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class FragmentFilms extends Fragment {

    //json link - https://run.mocky.io/v3/62b18870-21fb-4a08-aeb5-096b50992473
    private static String jsonURL = "https://run.mocky.io/v3/278f74b0-de01-4ef0-9421-065e1efcf00d";

    private FloatingActionButton addButton;
    View v;
    protected static RecyclerViewAdapter recyclerAdapter;
    private RecyclerView myRecycleView;
    protected static List<Film> listFilm;
    private Film deletedFilm = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.films_fragment, container, false);
        myRecycleView = v.findViewById(R.id.films_recyclerview);

//        setUpData();
        listFilm = new ArrayList<>();
        GetData getData = new GetData();
        getData.execute();

        /*addButton = v.findViewById(R.id.addingButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addIntent = new Intent(getContext(), AddFilmActivity.class);
                startActivity(addIntent);
            }
        });
        */
//        recyclerAdapter = new RecyclerViewAdapter(getContext(), listFilm);
//        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        myRecycleView.setAdapter(recyclerAdapter);

//        SearchView searchView = v.findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                ArrayList<Film> filteredFilms = new ArrayList<Film>();
//                for (Film film : listFilm) {
//                    if (film.getFilmName().toLowerCase().contains(s.toLowerCase())) {
//                        filteredFilms.add(film);
//                    }
//                }
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                ArrayList<Film> filteredFilms = new ArrayList<Film>();
//                for (Film film : listFilm) {
//                    if (film.getFilmName().toLowerCase().contains(s.toLowerCase())) {
//                        filteredFilms.add(film);
//                    }
//                }
//                recyclerAdapter.filterList(filteredFilms);
//
//                return false;
//            }
//        });

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(myRecycleView);

        return v;
    }

    public class GetData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(jsonURL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();

                    while (data != -1) {
                        current += (char) data;
                        data = inputStreamReader.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("Films");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);

                    Film film = new Film();
                    film.setFilmName(object.getString("Title"));
                    film.setFilmYear(object.getString("Year"));
                    film.setFilmType(object.getString("Type"));
                    film.setFilmPoster(object.getString("Poster"));

                    listFilm.add(film);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(listFilm);
        }
    }

    private void PutDataIntoRecyclerView(List<Film> listFilm) {
        recyclerAdapter = new RecyclerViewAdapter(getContext(), listFilm);
        myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecycleView.setAdapter(recyclerAdapter);
    }
//    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            int position = viewHolder.getAdapterPosition();
//            switch (direction) {
//                case ItemTouchHelper.LEFT:
//                    deletedFilm = listFilm.get(position);
//                    listFilm.remove(position);
//                    recyclerAdapter.notifyItemRemoved(position);
//                    break;
//            }
//        }
//
//        @Override
//        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            new RecyclerViewSwipeDecorator.Builder(getContext(), c, myRecycleView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.deleteFilm))
//                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
//                    .create()
//                    .decorate();
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    };

//    private void setUpData() {
//        listFilm = new ArrayList<>();



//        listFilm.add(new Film("Long title that want to break your layout. " +
//                "Long title that want to break your layout. " +
//                "Long title that want to break your layout. Long title that want to break your layout",
//                "2020", "", "", "", "", "", "",
//                "", "", "", "", "", "",
//                "test", R.color.noPoster));
//        listFilm.add(new Film("Star Wars: Episode IV - A New Hope",
//                "1977", "Action, Adventure, Fantasy, Sci-Fi", "George Lucas",
//                "George Lucas", "Mark Hamill, Harrison Ford, Carrie Fisher, Peter Cushing",
//                "USA", "English", "Lucasfilm Ltd", "25 May 1977",
//                "121 min", "Won 6 Oscars. Another 52 wins & 29 nominations.",
//                "8.6/10", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot," +
//                " a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station," +
//                " while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
//                "movie", R.drawable.poster_01));
//        listFilm.add(new Film("Star Wars: Episode V - The Empire Strikes Back", "1980",
//                "Action, Adventure, Fantasy, Sci-Fi", "Irvin Kershner",
//                "Leigh Brackett (screenplay by), Lawrence Kasdan (screenplay by), George Lucas (story by)",
//                "Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams",
//                "USA", "English", "Lucasfilm Ltd.",
//                "20 Jun 1980", "124 min",
//                "Won 1 Oscar. Another 24 wins & 20 nominations.", "8.7/10",
//                "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, " +
//                        "Luke Skywalker begins Jedi training with Yoda, while his friends are pursued " +
//                        "by Darth Vader and a bounty hunter named Boba Fett all over the galaxy.",
//                "movie", R.drawable.poster_02));
//        listFilm.add(new Film("Star Wars: Episode VI - Return of the Jedi", "1983",
//                "Action, Adventure, Fantasy, Sci-Fi", "Richard Marquand",
//                "Lawrence Kasdan (screenplay by), George Lucas (screenplay by), George Lucas (story by)",
//                "Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams",
//                "USA", "English", "Lucasfilm Ltd.",
//                "25 May 1983", "131 min",
//                "Nominated for 4 Oscars. Another 22 wins & 16 nominations.", "8.3/10",
//                "After a daring mission to rescue Han Solo from Jabba the Hutt, " +
//                        "the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, " +
//                        "Luke struggles to help Darth Vader back from the dark side without falling " +
//                        "into the Emperor's trap.",
//                "movie", R.drawable.poster_03));
//        listFilm.add(new Film("Star Wars: Episode I - The Phantom Menace", "1999",
//                "Action, Adventure, Fantasy, Sci-Fi", "George Lucas",
//                "George Lucas", "Liam Neeson, Ewan McGregor, Natalie Portman, Jake Lloyd",
//                "USA", "English", "Lucasfilm Ltd.",
//                "19 May 1999", "136 min",
//                "Nominated for 3 Oscars. Another 26 wins & 66 nominations.",
//                "6.5/10", "Two Jedi escape a hostile blockade to find allies and " +
//                "come across a young boy who may bring balance to the Force, " +
//                "but the long dormant Sith resurface to claim their original glory.",
//                "movie", R.drawable.poster_05));
//        listFilm.add(new Film("Star Wars: Episode II - Attack of the Clones", "2002",
//                "Action, Adventure, Fantasy, Sci-Fi", "George Lucas",
//                "George Lucas (screenplay by), Jonathan Hales (screenplay by), George Lucas (story by)",
//                "Ewan McGregor, Natalie Portman, Hayden Christensen, Christopher Lee",
//                "USA", "English", "Lucasfilm Ltd., Twentieth Century Fox",
//                "16 May 2002", "142 min",
//                "Nominated for 1 Oscar. Another 19 wins & 64 nominations.",
//                "6.5/10", "Ten years after initially meeting, Anakin Skywalker shares" +
//                " a forbidden romance with Padmé Amidala, while Obi-Wan Kenobi investigates an " +
//                "assassination attempt on the senator and discovers a secret clone army crafted for the Jedi.",
//                "movie", R.drawable.poster_07));
//        listFilm.add(new Film("Star Wars: Episode III - Revenge of the Sith", "2005",
//                "Action, Adventure, Fantasy, Sci-Fi", "George Lucas",
//                "George Lucas", "Ewan McGregor, Natalie Portman, Hayden Christensen, Ian McDiarmid",
//                "USA", "English", "Lucasfilm Ltd.",
//                "19 May 2005", "140 min",
//                "Nominated for 1 Oscar. Another 26 wins & 62 nominations.", "7.5/10",
//                "Three years into the Clone Wars, the Jedi rescue Palpatine from Count Dooku." +
//                        " As Obi-Wan pursues a new threat, Anakin acts as a double agent between " +
//                        "the Jedi Council and Palpatine and is lured into a sinister plan to rule the " +
//                        "galaxy.", "movie", R.drawable.poster_06));
//        listFilm.add(new Film("Star Trek", "2009", "Action, Adventure, Sci-Fi",
//                "J.J. Abrams", "Roberto Orci, Alex Kurtzman, Gene Roddenberry (television series \\\"Star Trek\\\")",
//                "Chris Pine, Zachary Quinto, Leonard Nimoy, Eric Bana",
//                "USA, Germany", "English", "Bad Robot",
//                "08 May 2009", "127 min", "Won 1 Oscar. Another 24 wins & 93 nominations.",
//                "7.9/10", "The brash James T. Kirk tries to live up to his father's legacy with" +
//                " Mr. Spock keeping him in check as a vengeful Romulan from the future creates black " +
//                "holes to destroy the Federation one planet at a time.", "movie", R.drawable.poster_08));
//        listFilm.add(new Film("Star Wars: Episode VII - The Force Awakens", "2015",
//                "Action, Adventure, Sci-Fi", "J.J. Abrams",
//                "Lawrence Kasdan, J.J. Abrams, Michael Arndt, George Lucas (based on characters created by)",
//                "Harrison Ford, Mark Hamill, Carrie Fisher, Adam Driver", "USA",
//                "English", "Lucasfilm Ltd., Bad Robot", "18 Dec 2015",
//                "138 min", "Nominated for 5 Oscars. Another 62 wins & 126 nominations.",
//                "7.9/10", "As a new threat to the galaxy rises, Rey, a desert scavenger, " +
//                "and Finn, an ex-stormtrooper, must join Han Solo and Chewbacca to search for the one hope " +
//                "of restoring peace.", "movie", R.color.noPoster));
//        listFilm.add(new Film("Star Wars: Episode VIII - The Last Jedi", "2017",
//                "Action, Adventure, Fantasy, Sci-Fi", "Rian Johnson",
//                "Rian Johnson, George Lucas (based on characters created by)",
//                "Mark Hamill, Carrie Fisher, Adam Driver, Daisy Ridley", "USA",
//                "English", "Lucasfilm Ltd.", "15 Dec 2017",
//                "152 min", "Nominated for 4 Oscars. Another 24 wins & 90 nominations.",
//                "7.0/10", "Rey develops her newly discovered abilities with the " +
//                "guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile," +
//                " the Resistance prepares for battle with the First Order.", "movie", R.color.noPoster));
//        listFilm.add(new Film("Rogue One: A Star Wars Story", "2016",
//                "Action, Adventure, Sci-Fi", "Gareth Edwards",
//                "Chris Weitz (screenplay by), Tony Gilroy (screenplay by), John Knoll (story by), Gary Whitta (story by)," +
//                        " George Lucas (based on characters created by)",
//                "Felicity Jones, Diego Luna, Alan Tudyk, Donnie Yen", "USA",
//                "English", "Lucasfilm Ltd.", "16 Dec 2016",
//                "133 min", "Nominated for 2 Oscars. Another 24 wins & 80 nominations.",
//                "7.8", "The daughter of an Imperial scientist joins the Rebel Alliance in a " +
//                "risky move to steal the plans for the Death Star.", "movie", R.drawable.poster_10));
    //}
}