package com.example.mariu.bonepoker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AuthorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

        TextView authors = (TextView) findViewById(R.id.txtAuthors);
        authors.setText("Projekt graficzny i wykonanie:\n\n" +
                "Mariusz Garbiński\n" +
                "Piotr Sowiak\n");
    }
}
