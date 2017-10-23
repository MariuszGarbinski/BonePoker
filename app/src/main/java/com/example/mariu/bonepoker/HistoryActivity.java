package com.example.mariu.bonepoker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        try {
            ArrayList<String> zxczxc = new ArrayList<>();
            ListView listView = (ListView)findViewById(R.id.lvHistory);
            ResultDBManager dbMan = new ResultDBManager(getApplicationContext());
            List<Result> histRes = null;
            histRes = dbMan.getHistoryResults();
            for (Result element : histRes) {
                zxczxc.add(element.toString());
            }

            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, zxczxc);
            listView.setAdapter(adapter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}