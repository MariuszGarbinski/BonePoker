package com.example.mariu.bonepoker;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorEventListener;
import java.util.ArrayList;
import java.util.Random;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

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
    //private Switch switch1;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorEvent sensorEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener((SensorEventListener) this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        final Switch switch1 = (Switch)findViewById(R.id.switch_1);
        final Switch switch2 = (Switch)findViewById(R.id.switch_2);
        final Switch switch3 = (Switch)findViewById(R.id.switch_3);
        final Switch switch4 = (Switch)findViewById(R.id.switch_4);
        final Switch switch5 = (Switch)findViewById(R.id.switch_5);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch1.isChecked()){
                    Toast.makeText(getApplicationContext(),"ZABLOKOWANA KOŚĆ 1",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ODBLOKOWANA KOŚĆ 1",Toast.LENGTH_LONG).show();
                }
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch2.isChecked()){
                    Toast.makeText(getApplicationContext(),"ZABLOKOWANA KOŚĆ 2",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ODBLOKOWANA KOŚĆ 2",Toast.LENGTH_LONG).show();
                }
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch3.isChecked()){
                    Toast.makeText(getApplicationContext(),"ZABLOKOWANA KOŚĆ 3",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ODBLOKOWANA KOŚĆ 3",Toast.LENGTH_LONG).show();
                }
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch4.isChecked()){
                    Toast.makeText(getApplicationContext(),"ZABLOKOWANA KOŚĆ 4",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ODBLOKOWANA KOŚĆ 4",Toast.LENGTH_LONG).show();
                }
            }
        });

        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch5.isChecked()){
                    Toast.makeText(getApplicationContext(),"ZABLOKOWANA KOŚĆ 5",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"ODBLOKOWANA KOŚĆ 5",Toast.LENGTH_LONG).show();
                }
            }
        });
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

        ArrayList numbersGenerated = new ArrayList();

        for (int i = 0; i < 5; i++) {
            Random randNumber = new Random();
            int iNumber = randNumber.nextInt(6) + 1;

            numbersGenerated.add(iNumber);
        }
        //Sprawdzanie czy switch jest w pozycji ON i wyłączenie odświeżania liczb
        if(switch1.isChecked()){

        }else{
            text1 = (TextView)findViewById(R.id.number_1);
            text1.setText(""+numbersGenerated.get(0));
        }
        if(switch2.isChecked()){

        }else{
            text2 = (TextView)findViewById(R.id.number_2);
            text2.setText(""+numbersGenerated.get(1));
        }
        if(switch3.isChecked()){

        }else{
            text3 = (TextView)findViewById(R.id.number_3);
            text3.setText(""+numbersGenerated.get(2));
        }
        if(switch4.isChecked()){

        }else{
            text4 = (TextView)findViewById(R.id.number_4);
            text4.setText(""+numbersGenerated.get(3));
        }
        if(switch5.isChecked()){

        }else{
            text5 = (TextView)findViewById(R.id.number_5);
            text5.setText(""+numbersGenerated.get(4));
        }

        //Sprawdzanie czy switch jest w pozycji ON i wyłączenie animacji kości
        if(switch1.isChecked()){

        }else{
            square1 = (FrameLayout) findViewById(R.id.square_1);
            square1.setVisibility(View.INVISIBLE);
        }
        if(switch2.isChecked()){

        }else{
            square2 = (FrameLayout) findViewById(R.id.square_2);
            square2.setVisibility(View.INVISIBLE);
        }
        if(switch3.isChecked()){

        }else{
            square3 = (FrameLayout) findViewById(R.id.square_3);
            square3.setVisibility(View.INVISIBLE);
        }
        if(switch4.isChecked()){

        }else{
            square4 = (FrameLayout) findViewById(R.id.square_4);
            square4.setVisibility(View.INVISIBLE);
        }
        if(switch5.isChecked()){

        }else{
            square5 = (FrameLayout) findViewById(R.id.square_5);
            square5.setVisibility(View.INVISIBLE);
        }

        Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);

        if(switch5.isChecked()){

        }else{
            square5.setVisibility(View.VISIBLE);
            square5.clearAnimation();
            square5.startAnimation(a);
        }
        if(switch4.isChecked()){

        }else{
            square4.setVisibility(View.VISIBLE);
            square4.clearAnimation();
            square4.startAnimation(a);
        }
        if(switch3.isChecked()){

        }else{
            square3.setVisibility(View.VISIBLE);
            square3.clearAnimation();
            square3.startAnimation(a);
        }
        if(switch2.isChecked()){

        }else{
            square2.setVisibility(View.VISIBLE);
            square2.clearAnimation();
            square2.startAnimation(a);
        }
        if(switch1.isChecked()){

        }else{
            square1.setVisibility(View.VISIBLE);
            square1.clearAnimation();
            square1.startAnimation(a);
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

}




