package com.example.mariu.bonepoker;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private FrameLayout square1;
    private FrameLayout square2;
    private FrameLayout square3;
    private FrameLayout square4;
    private FrameLayout square5;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorEvent sensorEvent;
    private Toast infoToast;
    private Result diceRolls;


    public void showToast(String message)
    {
        int toastDuration = 700;
        infoToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        CountDownTimer toastTimer = new CountDownTimer(toastDuration,100) {
            @Override
            public void onTick(long l) {
                infoToast.show();
            }

            @Override
            public void onFinish() {
                infoToast.cancel();
            }
        };

        infoToast.show();
        toastTimer.start();
    }

    private boolean checkSwitches()
    {
        boolean retVal = false;
        final Switch switch1 = (Switch)findViewById(R.id.switch_1);
        final Switch switch2 = (Switch)findViewById(R.id.switch_2);
        final Switch switch3 = (Switch)findViewById(R.id.switch_3);
        final Switch switch4 = (Switch)findViewById(R.id.switch_4);
        final Switch switch5 = (Switch)findViewById(R.id.switch_5);

        if (switch1.isChecked() &&
                switch2.isChecked() &&
                switch3.isChecked() &&
                switch4.isChecked() &&
                switch5.isChecked() )
        {
            retVal = true;
        }
        return  retVal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void ResetControls()
    {
        diceRolls = null;
        Switch switch1 = (Switch)findViewById(R.id.switch_1);
        Switch switch2 = (Switch)findViewById(R.id.switch_2);
        Switch switch3 = (Switch)findViewById(R.id.switch_3);
        Switch switch4 = (Switch)findViewById(R.id.switch_4);
        Switch switch5 = (Switch)findViewById(R.id.switch_5);
        switch1.setChecked(false);
        switch2.setChecked(false);
        switch3.setChecked(false);
        switch4.setChecked(false);
        switch5.setChecked(false);
        text1 = (TextView)findViewById(R.id.number_1);
        text2 = (TextView)findViewById(R.id.number_2);
        text3 = (TextView)findViewById(R.id.number_3);
        text4 = (TextView)findViewById(R.id.number_4);
        text5 = (TextView)findViewById(R.id.number_5);
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");

    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void getRandomNumber() {

        Switch switch1 = (Switch)findViewById(R.id.switch_1);
        Switch switch2 = (Switch)findViewById(R.id.switch_2);
        Switch switch3 = (Switch)findViewById(R.id.switch_3);
        Switch switch4 = (Switch)findViewById(R.id.switch_4);
        Switch switch5 = (Switch)findViewById(R.id.switch_5);

        ArrayList<Integer> numbersGenerated = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt(6) + 1;

            numbersGenerated.add(iNumber);
        }
        Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);

        //Sprawdzanie czy switch jest w pozycji ON i wyłączenie odświeżania liczb
        //Sprawdzanie czy switch jest w pozycji ON i wyłączenie animacji kości
        if(!switch1.isChecked()){
            text1 = (TextView)findViewById(R.id.number_1);
            text1.setText(numbersGenerated.get(0).toString());
            square1 = (FrameLayout) findViewById(R.id.square_1);
            square1.setVisibility(View.INVISIBLE);
            square1.setVisibility(View.VISIBLE);
            square1.clearAnimation();
            square1.startAnimation(a);
            diceRolls.dice1 = numbersGenerated.get(0);
        }
        if(!switch2.isChecked()){
            text2 = (TextView)findViewById(R.id.number_2);
            text2.setText(numbersGenerated.get(1).toString());
            square2 = (FrameLayout) findViewById(R.id.square_2);
            square2.setVisibility(View.INVISIBLE);
            square2.setVisibility(View.VISIBLE);
            square2.clearAnimation();
            square2.startAnimation(a);
            diceRolls.dice2 = numbersGenerated.get(1);
        }
        if(!switch3.isChecked()){
            text3 = (TextView)findViewById(R.id.number_3);
            text3.setText(numbersGenerated.get(2).toString());
            square3 = (FrameLayout) findViewById(R.id.square_3);
            square3.setVisibility(View.INVISIBLE);
            square3.setVisibility(View.VISIBLE);
            square3.clearAnimation();
            square3.startAnimation(a);
            diceRolls.dice3 = numbersGenerated.get(2);
        }
        if(!switch4.isChecked()){
            text4 = (TextView)findViewById(R.id.number_4);
            text4.setText(numbersGenerated.get(3).toString());
            square4 = (FrameLayout) findViewById(R.id.square_4);
            square4.setVisibility(View.INVISIBLE);
            square4.setVisibility(View.VISIBLE);
            square4.clearAnimation();
            square4.startAnimation(a);
            diceRolls.dice4 = numbersGenerated.get(3);
        }
        if(!switch5.isChecked()){
            text5 = (TextView)findViewById(R.id.number_5);
            text5.setText(numbersGenerated.get(4).toString());
            square5 = (FrameLayout) findViewById(R.id.square_5);
            square5.setVisibility(View.INVISIBLE);
            square5.setVisibility(View.VISIBLE);
            square5.clearAnimation();
            square5.startAnimation(a);
            diceRolls.dice5 = numbersGenerated.get(4);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    getRandomNumber();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void authorsClick(View view) {
        setContentView(R.layout.activity_authors);

        TextView authors = (TextView) findViewById(R.id.txtAuthors);
        authors.setText("Projekt graficzny i wykonanie:\n\n" +
                "Mariusz Garbiński\n" +
                "Piotr Sowiak\n");
    }

    public void historyClick(View view) {
        setContentView(R.layout.activity_history);
    }

    public void playClick(View view) {
        setContentView(R.layout.activity_start);

        if(diceRolls == null) diceRolls = new Result();

        final Switch switch1 = (Switch)findViewById(R.id.switch_1);
        final Switch switch2 = (Switch)findViewById(R.id.switch_2);
        final Switch switch3 = (Switch)findViewById(R.id.switch_3);
        final Switch switch4 = (Switch)findViewById(R.id.switch_4);
        final Switch switch5 = (Switch)findViewById(R.id.switch_5);
        final Button btnSave = (Button)findViewById(R.id.btnSave);
        final Button btnClear = (Button)findViewById(R.id.btnClear);
        final Button btnResult = (Button)findViewById(R.id.btnResult);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch1.isChecked()){
                    if(diceRolls.dice1 == 0)
                    {
                        switch1.setChecked(true);
                    }
                    else
                    {
                        showToast(checkSwitches() ? "Zablokowano wszystkie." : "Zablokowano kość 1");
                    }

                }else{
                    showToast("Odblokowano kość 1");
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch2.isChecked()){
                    if(diceRolls.dice2 == 0)
                    {
                        switch2.setChecked(true);
                    }
                    else
                    {
                        showToast(checkSwitches() ? "Zablokowano wszystkie." : "Zablokowano kość 2");
                    }

                }else{
                    showToast("Odblokowano kość 2");
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch3.isChecked()){
                    if(diceRolls.dice3 == 0)
                    {
                        switch3.setChecked(true);
                    }
                    else
                    {
                        showToast(checkSwitches() ? "Zablokowano wszystkie." : "Zablokowano kość 3");
                    }

                }else{
                    showToast("Odblokowano kość 3");
                }
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch4.isChecked()){
                    if(diceRolls.dice4 == 0)
                    {
                        switch4.setChecked(true);
                    }
                    else
                    {
                        showToast(checkSwitches() ? "Zablokowano wszystkie." : "Zablokowano kość 4");
                    }

                }else{
                    showToast("Odblokowano kość 4");
                }
            }
        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch5.isChecked()){
                    if(diceRolls.dice5 == 0)
                    {
                        switch5.setChecked(true);
                    }
                    else
                    {
                        showToast(checkSwitches() ? "Zablokowano wszystkie." : "Zablokowano kość 5");
                    }

                }else{
                    showToast("Odblokowano kość 5");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                if (checkSwitches())
                {
                    if(diceRolls != null)
                    {
                        diceRolls.Tmstmp = new Date();
                        ResultDBManager dbMan = new ResultDBManager(getApplicationContext());
                        if(dbMan.insert(diceRolls) > 0)
                        {
                            ResetControls();
                        }
                        else
                        {
                            showToast("Błąd zapisu do bazy danych.");
                        }
                    }
                }
                else
                {
                    showToast("Najpierw zablokuj wszystkie kości!");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                ResetControls();

            }
        });

        btnResult.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
    }

    public void exitClick(View view) {
        System.exit(0);
    }
}