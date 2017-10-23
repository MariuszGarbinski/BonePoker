package com.example.mariu.bonepoker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }

    public void playClick(View view) {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
    }

    public void historyClick(View view) {
        startActivity(new Intent(StartActivity.this, HistoryActivity.class));
    }

    public void authorsClick(View view) {
        startActivity(new Intent(StartActivity.this, AuthorsActivity.class));
    }

    public void exitClick(View view) {
        System.exit(0);
    }
}
