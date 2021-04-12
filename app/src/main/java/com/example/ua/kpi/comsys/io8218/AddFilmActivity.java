package com.example.ua.kpi.comsys.io8218;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AddFilmActivity extends AppCompatActivity {

    TextInputEditText addTitle;
    TextInputEditText addType;
    TextInputEditText addYear;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        addTitle = findViewById(R.id.addTitle);
        addType = findViewById(R.id.addType);
        addYear = findViewById(R.id.addYear);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = addTitle.getText().toString();
                String type = addType.getText().toString();
                String year = addYear.getText().toString();

                if (name.equals("") && type.equals("") && year.equals("")) {
                    Toast.makeText(AddFilmActivity.this, "Anything didn`t input", Toast.LENGTH_SHORT).show();
                }
                else if (name.equals("")) {
                    Toast.makeText(AddFilmActivity.this, "Title didn`t input. Please input the title", Toast.LENGTH_SHORT).show();
                }
                else {
                    FragmentFilms.listFilm.add(new Film(name, year, "", "", "",
                            "", "", "", "", "",
                            "", "", "", "", type, R.color.noPoster));

                    Toast.makeText(AddFilmActivity.this, "New film added", Toast.LENGTH_SHORT).show();

                    FragmentFilms.recyclerAdapter.notifyDataSetChanged();

                    finish();
                }
            }
        });
    }
}