package com.example.mariu.bonepoker;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

import static java.lang.Integer.compare;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView textThrows;
    private FrameLayout square1;
    private FrameLayout square2;
    private FrameLayout square3;
    private FrameLayout square4;
    private FrameLayout square5;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private int shoots;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private Toast infoToast;
    private Result diceRolls;
    int[] tab = new int[5];
    int[] tabTemp = new int[5];

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
        Switch switch1 = (Switch)findViewById(R.id.switch_1);
        Switch switch2 = (Switch)findViewById(R.id.switch_2);
        Switch switch3 = (Switch)findViewById(R.id.switch_3);
        Switch switch4 = (Switch)findViewById(R.id.switch_4);
        Switch switch5 = (Switch)findViewById(R.id.switch_5);

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
        shoots = 3;
        if(diceRolls == null) {
            diceRolls = new Result();
        }

        final Switch switch1 = (Switch)findViewById(R.id.switch_1);
        final Switch switch2 = (Switch)findViewById(R.id.switch_2);
        final Switch switch3 = (Switch)findViewById(R.id.switch_3);
        final Switch switch4 = (Switch)findViewById(R.id.switch_4);
        final Switch switch5 = (Switch)findViewById(R.id.switch_5);
        final Button btnSave = (Button)findViewById(R.id.btnSave);
        final Button btnClear = (Button)findViewById(R.id.btnClear);
        final Button btnResult = (Button)findViewById(R.id.btnResult);
        final TextView textThrows = (TextView)findViewById(R.id.textThrows);

        textThrows.setText(String.valueOf(shoots));

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switch1.isChecked()){
                    if(diceRolls.dice1 == 0)
                    {
                        switch1.setChecked(false);
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
                        switch2.setChecked(false);
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
                        switch3.setChecked(false);
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
                        switch4.setChecked(false);
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
                        switch5.setChecked(false);
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
                //TODO: Do zrobienia wyliczenie punktów
                startActivity(new Intent(MainActivity.this, HistoryActivity.class));
            }
        });
    }

    private void ResetControls()
    {
        shoots = 3;
        diceRolls = new Result();
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
        textThrows = (TextView)findViewById(R.id.textThrows);
        textThrows.setText(String.valueOf(shoots));
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
        TextView txtOnes = (TextView)findViewById(R.id.textView1_1);
        TextView txtTwos = (TextView)findViewById(R.id.textView2_1);
        TextView txtThrees = (TextView)findViewById(R.id.textView3_1);
        TextView txtFours = (TextView)findViewById(R.id.textView4_1);
        TextView txtFives = (TextView)findViewById(R.id.textView5_1);
        TextView txtSixs = (TextView)findViewById(R.id.textView6_1);
        TextView txtPair = (TextView)findViewById(R.id.textView7_1);
        TextView txtTwoPairs = (TextView)findViewById(R.id.textView8_1);
        TextView txtThreeOfKind = (TextView)findViewById(R.id.textView9_1);
        TextView txtLittleStraight = (TextView)findViewById(R.id.textView10_1);
        TextView txtBigStraight = (TextView)findViewById(R.id.textView11_1);
        TextView txtFull = (TextView)findViewById(R.id.textView12_1);
        TextView txtFourOfKind = (TextView)findViewById(R.id.textView13_1);
        TextView txtYathzee = (TextView)findViewById(R.id.textView14_1);
        TextView txtChance = (TextView)findViewById(R.id.textView15_1);

        ArrayList<Integer> numbersGenerated = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Random randNumber = new Random();
                int iNumber = randNumber.nextInt(6) + 1;

                numbersGenerated.add(iNumber);
            }
            Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);

            if(!switch1.isChecked()){
                text1 = (TextView)findViewById(R.id.number_1);
                text1.setText(numbersGenerated.get(0).toString());
                square1 = (FrameLayout) findViewById(R.id.square_1);
                square1.setVisibility(View.VISIBLE);
                square1.clearAnimation();
                square1.startAnimation(a);
                diceRolls.dice1 = numbersGenerated.get(0);
                tab[0] = numbersGenerated.get(0);
            }
            if(!switch2.isChecked()){
                text2 = (TextView)findViewById(R.id.number_2);
                text2.setText(numbersGenerated.get(1).toString());
                square2 = (FrameLayout) findViewById(R.id.square_2);
                square2.setVisibility(View.VISIBLE);
                square2.clearAnimation();
                square2.startAnimation(a);
                diceRolls.dice2 = numbersGenerated.get(1);
                tab[1] = numbersGenerated.get(1);
            }
            if(!switch3.isChecked()){
                text3 = (TextView)findViewById(R.id.number_3);
                text3.setText(numbersGenerated.get(2).toString());
                square3 = (FrameLayout) findViewById(R.id.square_3);
                square3.setVisibility(View.VISIBLE);
                square3.clearAnimation();
                square3.startAnimation(a);
                diceRolls.dice3 = numbersGenerated.get(2);
                tab[2] = numbersGenerated.get(2);
            }
            if(!switch4.isChecked()){
                text4 = (TextView)findViewById(R.id.number_4);
                text4.setText(numbersGenerated.get(3).toString());
                square4 = (FrameLayout) findViewById(R.id.square_4);
                square4.setVisibility(View.VISIBLE);
                square4.clearAnimation();
                square4.startAnimation(a);
                diceRolls.dice4 = numbersGenerated.get(3);
                tab[3] = numbersGenerated.get(3);
            }
            if(!switch5.isChecked()){
                text5 = (TextView)findViewById(R.id.number_5);
                text5.setText(numbersGenerated.get(4).toString());
                square5 = (FrameLayout) findViewById(R.id.square_5);
                square5.setVisibility(View.VISIBLE);
                square5.clearAnimation();
                square5.startAnimation(a);
                diceRolls.dice5 = numbersGenerated.get(4);
                tab[4] = numbersGenerated.get(4);
            }


        int iOnes = 0;
        int iTwos = 0;
        int iThrees = 0;
        int iFours = 0;
        int iFives = 0;
        int iSixes = 0;

        int iPair = 0;
        int iTwoPair = 0;
        int iThreeOfKind = 0;
        int iFourOfKind = 0;
        int iFullHouse = 0;
        int iLittleStraight = 0;
        int iBigStraight = 0;
        int iYathzee = 0;

        for(int i = 0; i < tab.length; i++){
            tabTemp[i] = tab[i];
            if(tab[i] == 1) iOnes++;
            if(tab[i] == 2) iTwos++;
            if(tab[i] == 3) iThrees++;
            if(tab[i] == 4) iFours++;
            if(tab[i] == 5) iFives++;
            if(tab[i] == 6) iSixes++;
        }

        for (int i = 1; i < tabTemp.length; i++) {
            int j = i;
            while (j > 0 && compare(j - 1, j) > 0) {
                int temp = tabTemp[j];
                tabTemp[j] = tabTemp[j - 1];
                tabTemp[j - 1] = temp;
                j--;
            }
        }

        if( (((tabTemp[0] == tabTemp[1]) && (tabTemp[1] == tabTemp[2])) &&
                (tabTemp[3] == tabTemp[4]) &&
                (tabTemp[2] != tabTemp[3])) ||
                ((tabTemp[0] == tabTemp[1]) &&
                        ((tabTemp[2] == tabTemp[3]) && (tabTemp[3] == tabTemp[4])) &&
                        (tabTemp[1] != tabTemp[2])) )
        {
            iFullHouse = (tabTemp[0] + tabTemp[1] + tabTemp[2] + tabTemp[3] + tabTemp[4]) + 10;
        }

        if( (((tabTemp[0] == tabTemp[1]) && (tabTemp[2] == tabTemp[3]) &&
                (tabTemp[1] != tabTemp[2]))))
        {
            iTwoPair = tabTemp[0] + tabTemp[1] + tabTemp[2] + tabTemp[3];
        }

        switch (iOnes){
            case 2:
                iPair = (iOnes * 1);
                break;
            case 3:
                iPair = (iOnes * 1) - 1;
                iThreeOfKind = (iOnes * 1);
                break;
            case 4:
                iPair = (iOnes * 1) - 2;
                iThreeOfKind = (iOnes * 1) - 1;
                iFourOfKind = (iOnes * 1);
                break;
            case 5:
                iPair = (iOnes * 1) - 3;
                iThreeOfKind = (iOnes * 1) - 2;
                iFourOfKind = ((iOnes * 1) - 1) + 20;
                iYathzee = (iOnes * 1) + 50;
                break;
        }
        switch (iTwos){
            case 2:
                iPair = (iTwos * 2);
                break;
            case 3:
                iPair = (iTwos * 2) - 2;
                iThreeOfKind = (iTwos * 2);
                break;
            case 4:
                iPair = (iTwos * 2) - 4;
                iThreeOfKind = (iTwos * 2) - 2;
                iFourOfKind = (iTwos * 2);
                break;
            case 5:
                iPair = (iTwos * 2) - 6;
                iThreeOfKind = (iTwos * 2) - 4;
                iFourOfKind = ((iTwos * 2) - 2) + 20;
                iYathzee = (iTwos * 2) + 50;
                break;
        }
        switch (iThrees){
            case 2:
                iPair = (iThrees * 3);
                break;
            case 3:
                iPair = (iThrees * 3) - 3;
                iThreeOfKind = (iThrees * 3);
                break;
            case 4:
                iPair = (iThrees * 3) - 6;
                iThreeOfKind = (iThrees * 3) - 3;
                iFourOfKind = (iThrees * 3);
                break;
            case 5:
                iPair = (iThrees * 3) - 9;
                iThreeOfKind = (iThrees * 3) - 6;
                iFourOfKind = ((iThrees * 3) - 3) + 20;
                iYathzee = (iThrees * 3) + 50;
                break;
        }
        switch (iFours){
            case 2:
                iPair = (iFours * 4);
                break;
            case 3:
                iPair = (iFours * 4) - 4;
                iThreeOfKind = (iFours * 4);
                break;
            case 4:
                iPair = (iFours * 4) - 8;
                iThreeOfKind = (iFours * 4) - 4;
                iFourOfKind = (iFours * 4);
                break;
            case 5:
                iPair = (iFours * 4) - 12;
                iThreeOfKind = (iFours * 4) - 8;
                iFourOfKind = ((iFours * 4) - 4) + 20;
                iYathzee = (iFours * 4) + 50;
                break;
        }
        switch (iFives){
            case 2:
                iPair = (iFives * 5);
                break;
            case 3:
                iPair = (iFives * 5) - 5;
                iThreeOfKind = (iFives * 5);
                break;
            case 4:
                iPair = (iFives * 5) - 10;
                iThreeOfKind = (iFives * 5) - 5;
                iFourOfKind = (iFives * 5);
                break;
            case 5:
                iPair = (iFives * 5) - 15;
                iThreeOfKind = (iFives * 5) - 10;
                iFourOfKind = ((iFives * 5) - 5) + 20;
                iYathzee = (iFives * 5) + 50;
                break;
        }
        switch (iSixes){
            case 2:
                iPair = (iSixes * 6);
                break;
            case 3:
                iPair = (iSixes * 6) - 6;
                iThreeOfKind = (iSixes * 6);
                break;
            case 4:
                iPair = (iSixes * 6) - 12;
                iThreeOfKind = (iSixes * 6) - 6;
                iFourOfKind = (iSixes * 6);
                break;
            case 5:
                iPair = (iSixes * 6) - 18;
                iThreeOfKind = (iSixes * 6) - 12;
                iFourOfKind = ((iSixes * 6) - 6) + 20;
                iYathzee = (iSixes * 6) + 50;
                break;
        }


        if(iOnes == 1 && iTwos == 1 && iThrees == 1 & iFours == 1 && iFives == 1){
            iLittleStraight = 15;
        }

        if(iTwos == 1 && iThrees == 1 & iFours == 1 && iFives == 1 && iSixes == 1){
            iBigStraight = 20;
        }

        txtOnes.setText(String.valueOf(iOnes * 1));
        txtTwos.setText(String.valueOf(iTwos * 2));
        txtThrees.setText(String.valueOf(iThrees * 3));
        txtFours.setText(String.valueOf(iFours * 4));
        txtFives.setText(String.valueOf(iFives * 5));
        txtSixs.setText(String.valueOf(iSixes * 6));
        txtPair.setText(String.valueOf(iPair));
        txtTwoPairs.setText(String.valueOf(iTwoPair));
        txtThreeOfKind.setText(String.valueOf(iThreeOfKind));
        txtLittleStraight.setText(String.valueOf(iLittleStraight));
        txtBigStraight.setText(String.valueOf(iBigStraight));
        txtFull.setText(String.valueOf(iFullHouse));
        txtFourOfKind.setText(String.valueOf(iFourOfKind));
        txtYathzee.setText(String.valueOf(iYathzee));
        txtChance.setText(String.valueOf((iOnes * 1) + (iTwos * 2) + (iThrees * 3) + (iFours * 4) + (iFives * 5) + (iSixes *6)));
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;
        textThrows = (TextView) findViewById(R.id.textThrows);

            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 100) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    if (speed > SHAKE_THRESHOLD) {
                        if(shoots > 0){
                            getRandomNumber();
                            shoots -= 1;
                            textThrows.setText(String.valueOf(shoots));
                        }
                    }
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}