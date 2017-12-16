package com.example.mariu.bonepoker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
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

    private TextView textThrows;
    private TextView textRounds;
    private FrameLayout square1;
    private FrameLayout square2;
    private FrameLayout square3;
    private FrameLayout square4;
    private FrameLayout square5;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private int shoots;
    private int rounds;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private Toast infoToast;
    private Result diceRolls;
    private Summary summary;
    int[] tab = new int[5];
    private boolean bOnes = true;
    private boolean bTwos = true;
    private boolean bThrees = true;
    private boolean bFours = true;
    private boolean bFives = true;
    private boolean bSixs = true;
    private boolean bPair = true;
    private boolean bTwoPairs = true;
    private boolean bThreeOfKind = true;
    private boolean bLitleStraight = true;
    private boolean bBigStraight = true;
    private boolean bFullHouse = true;
    private boolean bFourOfKind = true;
    private boolean bYathzee = true;
    private boolean bChance = true;

    private int total;


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
        Switch switch1 = findViewById(R.id.switch_1);
        Switch switch2 = findViewById(R.id.switch_2);
        Switch switch3 = findViewById(R.id.switch_3);
        Switch switch4 = findViewById(R.id.switch_4);
        Switch switch5 = findViewById(R.id.switch_5);

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        shoots = 3;
        rounds = 15;
        if(diceRolls == null) {
            diceRolls = new Result();
        }

        if(summary == null){
            summary = new Summary();
        }

        final Switch switch1 = findViewById(R.id.switch_1);
        final Switch switch2 = findViewById(R.id.switch_2);
        final Switch switch3 = findViewById(R.id.switch_3);
        final Switch switch4 = findViewById(R.id.switch_4);
        final Switch switch5 = findViewById(R.id.switch_5);
        final Button btnSave = findViewById(R.id.btnSave);
        final Button btnNewGame = findViewById(R.id.btnNewGame);
        final TextView textRounds = findViewById(R.id.textRounds);
        final TextView textThrows = findViewById(R.id.textThrows);
        final TextView lblOnes = findViewById(R.id.textView1);
        final TextView txtOnes = findViewById(R.id.textView1_1);
        final TextView lblTwos = findViewById(R.id.textView2);
        final TextView txtTwos = findViewById(R.id.textView2_1);
        final TextView lblThrees = findViewById(R.id.textView3);
        final TextView txtThrees = findViewById(R.id.textView3_1);
        final TextView lblFours = findViewById(R.id.textView4);
        final TextView txtFours = findViewById(R.id.textView4_1);
        final TextView lblFivess = findViewById(R.id.textView5);
        final TextView txtFivess = findViewById(R.id.textView5_1);
        final TextView lblSixs = findViewById(R.id.textView6);
        final TextView txtSixs = findViewById(R.id.textView6_1);
        final TextView lblPair = findViewById(R.id.textView7);
        final TextView txtPair = findViewById(R.id.textView7_1);
        final TextView lblTwoPairs = findViewById(R.id.textView8);
        final TextView txtTwoPairs = findViewById(R.id.textView8_1);
        final TextView lblThreeOfKind = findViewById(R.id.textView9);
        final TextView txtThreeOfKind = findViewById(R.id.textView9_1);
        final TextView lblLitleStraight = findViewById(R.id.textView10);
        final TextView txtLitleStraight = findViewById(R.id.textView10_1);
        final TextView lblBigStraight = findViewById(R.id.textView11);
        final TextView txtBigStraight = findViewById(R.id.textView11_1);
        final TextView lblFullHouse = findViewById(R.id.textView12);
        final TextView txtFullHouse = findViewById(R.id.textView12_1);
        final TextView lblFoureOfKind = findViewById(R.id.textView13);
        final TextView txtFoureOfKind = findViewById(R.id.textView13_1);
        final TextView lblYathzee = findViewById(R.id.textView14);
        final TextView txtYathzee = findViewById(R.id.textView14_1);
        final TextView lblChance = findViewById(R.id.textView15);
        final TextView txtChance = findViewById(R.id.textView15_1);
        final TextView txtTotal = findViewById(R.id.textView16_1);


        textThrows.setText(String.valueOf(shoots));
        textRounds.setText(String.valueOf(rounds));
        btnSave.setEnabled(false);

        //Tablica wyników
        lblOnes.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(txtOnes.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num1;
                txtTotal.setText(String.valueOf(total));
                bOnes = false;
                if (num1 != 0){
                    txtOnes.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtOnes.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblTwos.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num2 = Integer.parseInt(txtTwos.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num2;
                txtTotal.setText(String.valueOf(total));
                bTwos = false;
                if (num2 != 0){
                    txtTwos.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtTwos.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblThrees.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num3 = Integer.parseInt(txtThrees.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num3;
                txtTotal.setText(String.valueOf(total));
                bThrees = false;
                if (num3 != 0){
                    txtThrees.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtThrees.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblFours.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num4 = Integer.parseInt(txtFours.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num4;
                txtTotal.setText(String.valueOf(total));
                bFours = false;
                if (num4 != 0){
                    txtFours.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtFours.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblFivess.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num5 = Integer.parseInt(txtFivess.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num5;
                txtTotal.setText(String.valueOf(total));
                bFives = false;
                if (num5 != 0){
                    txtFivess.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtFivess.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblSixs.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num6 = Integer.parseInt(txtSixs.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num6;
                txtTotal.setText(String.valueOf(total));
                bSixs = false;
                if (num6 != 0){
                    txtSixs.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtSixs.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblPair.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num7 = Integer.parseInt(txtPair.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num7;
                txtTotal.setText(String.valueOf(total));
                bPair = false;
                if (num7 != 0){
                    txtPair.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtPair.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblTwoPairs.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num8 = Integer.parseInt(txtTwoPairs.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num8;
                txtTotal.setText(String.valueOf(total));
                bTwoPairs = false;
                if (num8 != 0){
                    txtTwoPairs.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtTwoPairs.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblThreeOfKind.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num9 = Integer.parseInt(txtThreeOfKind.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num9;
                txtTotal.setText(String.valueOf(total));
                bThreeOfKind = false;
                if (num9 != 0){
                    txtThreeOfKind.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtThreeOfKind.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblLitleStraight.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num10 = Integer.parseInt(txtLitleStraight.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num10;
                txtTotal.setText(String.valueOf(total));
                bLitleStraight = false;
                if (num10 != 0){
                    txtLitleStraight.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtLitleStraight.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblBigStraight.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num11 = Integer.parseInt(txtBigStraight.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num11;
                txtTotal.setText(String.valueOf(total));
                bBigStraight = false;
                if (num11 != 0){
                    txtBigStraight.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtBigStraight.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblFullHouse.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num12 = Integer.parseInt(txtFullHouse.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num12;
                txtTotal.setText(String.valueOf(total));
                bFullHouse = false;
                if (num12 != 0){
                    txtFullHouse.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtFullHouse.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblFoureOfKind.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num13 = Integer.parseInt(txtFoureOfKind.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num13;
                txtTotal.setText(String.valueOf(total));
                bFourOfKind = false;
                if (num13 != 0){
                    txtFoureOfKind.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtFoureOfKind.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblYathzee.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num14 = Integer.parseInt(txtYathzee.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num14;
                txtTotal.setText(String.valueOf(total));
                bYathzee = false;
                if (num14 != 0){
                    txtYathzee.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtYathzee.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        lblChance.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                int num15 = Integer.parseInt(txtChance.getText().toString());
                int sum = Integer.parseInt(txtTotal.getText().toString());
                total = sum + num15;
                txtTotal.setText(String.valueOf(total));
                bChance = false;
                if (num15 != 0){
                    txtChance.setTextColor(Color.rgb(0,204,0));
                }else{
                    txtChance.setTextColor(Color.rgb(255,0,0));
                }
                ResetControls();
            }
        });

        //Przełączniki
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


                summary.Tmstmp = new Date();
                SummaryDBManager sDbMan = new SummaryDBManager(getApplicationContext());
                if(sDbMan.insert(summary) > 0)
                {
                    ResetControls();
                }else{
                    showToast("Błąd zapisu do bazy danych.");
                }
/*
                diceRolls.Tmstmp = new Date();
                ResultDBManager dbMan = new ResultDBManager(getApplicationContext());
                if(dbMan.insert(diceRolls) > 0)
                {
                    ResetControls();
                }else{
                    showToast("Błąd zapisu do bazy danych.");
                }
*/
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                rounds = 16;
                btnNewGame.setEnabled(false);
                bOnes = true;
                bTwos = true;
                bThrees = true;
                bFours = true;
                bFives = true;
                bSixs = true;
                bPair = true;
                bTwoPairs = true;
                bThreeOfKind = true;
                bLitleStraight = true;
                bBigStraight = true;
                bFullHouse = true;
                bFourOfKind = true;
                bYathzee = true;
                bChance = true;
                txtTotal.setText("0");
                txtOnes.setTextColor(Color.rgb(224,224,224));
                txtTwos.setTextColor(Color.rgb(224,224,224));
                txtThrees.setTextColor(Color.rgb(224,224,224));
                txtFours.setTextColor(Color.rgb(224,224,224));
                txtFivess.setTextColor(Color.rgb(224,224,224));
                txtSixs.setTextColor(Color.rgb(224,224,224));
                txtPair.setTextColor(Color.rgb(224,224,224));
                txtTwoPairs.setTextColor(Color.rgb(224,224,224));
                txtThreeOfKind.setTextColor(Color.rgb(224,224,224));
                txtLitleStraight.setTextColor(Color.rgb(224,224,224));
                txtBigStraight.setTextColor(Color.rgb(224,224,224));
                txtFullHouse.setTextColor(Color.rgb(224,224,224));
                txtFoureOfKind.setTextColor(Color.rgb(224,224,224));
                txtYathzee.setTextColor(Color.rgb(224,224,224));
                txtChance.setTextColor(Color.rgb(224,224,224));
                ResetControls();

            }
        });
    }

    private void ResetControls()
    {
        square1.setBackgroundResource(R.drawable.square);
        square2.setBackgroundResource(R.drawable.square);
        square3.setBackgroundResource(R.drawable.square);
        square4.setBackgroundResource(R.drawable.square);
        square5.setBackgroundResource(R.drawable.square);

        TextView txtOnes = findViewById(R.id.textView1_1);
        TextView txtTwos = findViewById(R.id.textView2_1);
        TextView txtThrees = findViewById(R.id.textView3_1);
        TextView txtFours = findViewById(R.id.textView4_1);
        TextView txtFives = findViewById(R.id.textView5_1);
        TextView txtSixs = findViewById(R.id.textView6_1);
        TextView txtPair = findViewById(R.id.textView7_1);
        TextView txtTwoPairs = findViewById(R.id.textView8_1);
        TextView txtThreeOfKind = findViewById(R.id.textView9_1);
        TextView txtLittleStraight = findViewById(R.id.textView10_1);
        TextView txtBigStraight = findViewById(R.id.textView11_1);
        TextView txtFull = findViewById(R.id.textView12_1);
        TextView txtFourOfKind = findViewById(R.id.textView13_1);
        TextView txtYathzee = findViewById(R.id.textView14_1);
        TextView txtChance = findViewById(R.id.textView15_1);
        Button btnSave = findViewById(R.id.btnSave);

        shoots = 3;
        rounds -= 1;
        if (rounds == 0) btnSave.setEnabled(true);
        diceRolls = new Result();
        Switch switch1 = findViewById(R.id.switch_1);
        Switch switch2 = findViewById(R.id.switch_2);
        Switch switch3 = findViewById(R.id.switch_3);
        Switch switch4 = findViewById(R.id.switch_4);
        Switch switch5 = findViewById(R.id.switch_5);
        switch1.setChecked(false);
        switch2.setChecked(false);
        switch3.setChecked(false);
        switch4.setChecked(false);
        switch5.setChecked(false);

        if (bOnes != false) txtOnes.setText("0");
        if (bTwos != false) txtTwos.setText("0");
        if (bThrees != false) txtThrees.setText("0");
        if (bFours != false) txtFours.setText("0");
        if (bFives != false) txtFives.setText("0");
        if (bSixs != false) txtSixs.setText("0");
        if (bPair != false) txtPair.setText("0");
        if (bTwoPairs != false) txtTwoPairs.setText("0");
        if (bThreeOfKind != false) txtThreeOfKind.setText("0");
        if (bLitleStraight != false) txtLittleStraight.setText("0");
        if (bBigStraight != false) txtBigStraight.setText("0");
        if (bFullHouse != false) txtFull.setText("0");
        if (bFourOfKind != false) txtFourOfKind.setText("0");
        if (bYathzee != false) txtYathzee.setText("0");
        if (bChance != false) txtChance.setText("0");

        textThrows = findViewById(R.id.textThrows);
        textThrows.setText(String.valueOf(shoots));

        textRounds = findViewById(R.id.textRounds);
        textRounds.setText(String.valueOf(rounds));
    }

    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getRandomNumber() {

        Switch switch1 = findViewById(R.id.switch_1);
        Switch switch2 = findViewById(R.id.switch_2);
        Switch switch3 = findViewById(R.id.switch_3);
        Switch switch4 = findViewById(R.id.switch_4);
        Switch switch5 = findViewById(R.id.switch_5);

        ArrayList<Integer> numbersGenerated = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                Random randNumber = new Random();
                int iNumber = randNumber.nextInt(6) + 1;

                numbersGenerated.add(iNumber);
            }
            Animation a = AnimationUtils.loadAnimation(this, R.anim.move_down_ball_first);

            if(!switch1.isChecked()){
                square1 = findViewById(R.id.square_1);
                switch (numbersGenerated.get(0)){
                    case 1:
                        square1.setBackgroundResource(R.drawable.bone1);
                        break;
                    case 2:
                        square1.setBackgroundResource(R.drawable.bone2);
                        break;
                    case 3:
                        square1.setBackgroundResource(R.drawable.bone3);
                        break;
                    case 4:
                        square1.setBackgroundResource(R.drawable.bone4);
                        break;
                    case 5:
                        square1.setBackgroundResource(R.drawable.bone5);
                        break;
                    case 6:
                        square1.setBackgroundResource(R.drawable.bone6);
                        break;
                }
                square1.setVisibility(View.VISIBLE);
                square1.clearAnimation();
                square1.startAnimation(a);
                diceRolls.dice1 = numbersGenerated.get(0);
                tab[0] = numbersGenerated.get(0);
            }
            if(!switch2.isChecked()){
                square2 = findViewById(R.id.square_2);
                switch (numbersGenerated.get(1)){
                    case 1:
                        square2.setBackgroundResource(R.drawable.bone1);
                        break;
                    case 2:
                        square2.setBackgroundResource(R.drawable.bone2);
                        break;
                    case 3:
                        square2.setBackgroundResource(R.drawable.bone3);
                        break;
                    case 4:
                        square2.setBackgroundResource(R.drawable.bone4);
                        break;
                    case 5:
                        square2.setBackgroundResource(R.drawable.bone5);
                        break;
                    case 6:
                        square2.setBackgroundResource(R.drawable.bone6);
                        break;
                }
                square2.setVisibility(View.VISIBLE);
                square2.clearAnimation();
                square2.startAnimation(a);
                diceRolls.dice2 = numbersGenerated.get(1);
                tab[1] = numbersGenerated.get(1);
            }
            if(!switch3.isChecked()){
                square3 = findViewById(R.id.square_3);
                switch (numbersGenerated.get(2)){
                    case 1:
                        square3.setBackgroundResource(R.drawable.bone1);
                        break;
                    case 2:
                        square3.setBackgroundResource(R.drawable.bone2);
                        break;
                    case 3:
                        square3.setBackgroundResource(R.drawable.bone3);
                        break;
                    case 4:
                        square3.setBackgroundResource(R.drawable.bone4);
                        break;
                    case 5:
                        square3.setBackgroundResource(R.drawable.bone5);
                        break;
                    case 6:
                        square3.setBackgroundResource(R.drawable.bone6);
                        break;
                }
                square3.setVisibility(View.VISIBLE);
                square3.clearAnimation();
                square3.startAnimation(a);
                diceRolls.dice3 = numbersGenerated.get(2);
                tab[2] = numbersGenerated.get(2);
            }
            if(!switch4.isChecked()){
                square4 = findViewById(R.id.square_4);
                switch (numbersGenerated.get(3)){
                    case 1:
                        square4.setBackgroundResource(R.drawable.bone1);
                        break;
                    case 2:
                        square4.setBackgroundResource(R.drawable.bone2);
                        break;
                    case 3:
                        square4.setBackgroundResource(R.drawable.bone3);
                        break;
                    case 4:
                        square4.setBackgroundResource(R.drawable.bone4);
                        break;
                    case 5:
                        square4.setBackgroundResource(R.drawable.bone5);
                        break;
                    case 6:
                        square4.setBackgroundResource(R.drawable.bone6);
                        break;
                }
                square4.setVisibility(View.VISIBLE);
                square4.clearAnimation();
                square4.startAnimation(a);
                diceRolls.dice4 = numbersGenerated.get(3);
                tab[3] = numbersGenerated.get(3);
            }
            if(!switch5.isChecked()){
                square5 = findViewById(R.id.square_5);
                switch (numbersGenerated.get(4)){
                    case 1:
                        square5.setBackgroundResource(R.drawable.bone1);
                        break;
                    case 2:
                        square5.setBackgroundResource(R.drawable.bone2);
                        break;
                    case 3:
                        square5.setBackgroundResource(R.drawable.bone3);
                        break;
                    case 4:
                        square5.setBackgroundResource(R.drawable.bone4);
                        break;
                    case 5:
                        square5.setBackgroundResource(R.drawable.bone5);
                        break;
                    case 6:
                        square5.setBackgroundResource(R.drawable.bone6);
                        break;
                }
                square5.setVisibility(View.VISIBLE);
                square5.clearAnimation();
                square5.startAnimation(a);
                diceRolls.dice5 = numbersGenerated.get(4);
                tab[4] = numbersGenerated.get(4);
            }
    }

    private void afterUpdate() {

        TextView txtOnes = findViewById(R.id.textView1_1);
        TextView txtTwos = findViewById(R.id.textView2_1);
        TextView txtThrees = findViewById(R.id.textView3_1);
        TextView txtFours = findViewById(R.id.textView4_1);
        TextView txtFives = findViewById(R.id.textView5_1);
        TextView txtSixs = findViewById(R.id.textView6_1);
        TextView txtPair = findViewById(R.id.textView7_1);
        TextView txtTwoPairs = findViewById(R.id.textView8_1);
        TextView txtThreeOfKind = findViewById(R.id.textView9_1);
        TextView txtLittleStraight = findViewById(R.id.textView10_1);
        TextView txtBigStraight = findViewById(R.id.textView11_1);
        TextView txtFull = findViewById(R.id.textView12_1);
        TextView txtFourOfKind = findViewById(R.id.textView13_1);
        TextView txtYathzee = findViewById(R.id.textView14_1);
        TextView txtChance = findViewById(R.id.textView15_1);

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
            if(tab[i] == 1) iOnes++;
            if(tab[i] == 2) iTwos++;
            if(tab[i] == 3) iThrees++;
            if(tab[i] == 4) iFours++;
            if(tab[i] == 5) iFives++;
            if(tab[i] == 6) iSixes++;
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

        if (iOnes >= 2 && iTwos >= 2){
            iTwoPair = 6;
        }else if(iOnes >= 2 && iThrees >= 2){
            iTwoPair = 8;
        }else if(iOnes >= 2 && iFours >= 2){
            iTwoPair = 10;
        }else if(iOnes >= 2 && iFives >= 2){
            iTwoPair = 12;
        }else if(iOnes >= 2 && iSixes >= 2){
            iTwoPair = 14;
        }else if(iTwos >= 2 && iThrees >= 2){
            iTwoPair = 10;
        }else if(iTwos >= 2 && iFours >= 2){
            iTwoPair = 12;
        }else if(iTwos >= 2 && iFives >= 2){
            iTwoPair = 14;
        }else if(iTwos >= 2 && iSixes >= 2){
            iTwoPair = 16;
        }else if(iThrees >= 2 && iFours >= 2){
            iTwoPair = 14;
        }else if(iThrees >= 2 && iFives >= 2){
            iTwoPair = 16;
        }else if(iThrees >= 2 && iSixes >= 2){
            iTwoPair = 18;
        }else if(iFours >= 2 && iFives >= 2){
            iTwoPair = 18;
        }else if(iFours >= 2 && iSixes >= 2){
            iTwoPair = 20;
        }else if(iFives >= 2 && iSixes >= 2){
            iTwoPair = 22;
        }

        if ((iOnes == 2 && iTwos == 3) || (iOnes == 3 && iTwos == 2)){
            iFullHouse = (iOnes + (iTwos * 2)) + 10;
        }else if ((iOnes == 2 && iThrees == 3) || (iOnes == 3 && iThrees == 2)){
            iFullHouse = (iOnes + (iThrees * 3)) + 10;
        }else if ((iOnes == 2 && iFours == 3) || (iOnes == 3 && iFours == 2)){
            iFullHouse = (iOnes + (iFours * 4)) + 10;
        }else if ((iOnes == 2 && iFives == 3) || (iOnes == 3 && iFives == 2)){
            iFullHouse = (iOnes + (iFives * 5)) + 10;
        }else if ((iOnes == 2 && iSixes == 3) || (iOnes == 3 && iSixes == 2)){
            iFullHouse = (iOnes + (iSixes * 6)) + 10;
        }else if ((iTwos == 2 && iThrees == 3) || (iTwos == 3 && iThrees == 2)){
            iFullHouse = ((iTwos * 2) + (iThrees * 3)) + 10;
        }else if ((iTwos == 2 && iFours == 3) || (iTwos == 3 && iFours == 2)){
            iFullHouse = ((iTwos * 2) + (iFours * 4)) + 10;
        }else if ((iTwos == 2 && iFives == 3) || (iTwos == 3 && iFives == 2)){
            iFullHouse = ((iTwos * 2) + (iFives * 5)) + 10;
        }else if ((iTwos == 2 && iSixes == 3) || (iTwos == 3 && iSixes == 2)){
            iFullHouse = ((iTwos * 2) + (iSixes * 6)) + 10;
        }else if ((iThrees == 2 && iFours == 3) || (iThrees == 3 && iFours == 2)){
            iFullHouse = ((iThrees * 3) + (iFours * 4)) + 10;
        }else if ((iThrees == 2 && iFives == 3) || (iThrees == 3 && iFives == 2)){
            iFullHouse = ((iThrees * 3) + (iFives * 5)) + 10;
        }else if ((iThrees == 2 && iSixes == 3) || (iThrees == 3 && iSixes == 2)){
            iFullHouse = ((iThrees * 3) + (iSixes * 6)) + 10;
        }else if ((iFours == 2 && iFives == 3) || (iFours == 3 && iFives == 2)){
            iFullHouse = ((iFours * 4) + (iFives * 5)) + 10;
        }else if ((iFours == 2 && iSixes == 3) || (iFours == 3 && iSixes == 2)){
            iFullHouse = ((iFours * 4) + (iSixes * 6)) + 10;
        }else if ((iFives == 2 && iSixes == 3) || (iFives == 3 && iSixes == 2)){
            iFullHouse = ((iFives * 5) + (iSixes * 6)) + 10;
        }


        if(iOnes == 1 && iTwos == 1 && iThrees == 1 & iFours == 1 && iFives == 1){
            iLittleStraight = 15;
        }

        if(iTwos == 1 && iThrees == 1 & iFours == 1 && iFives == 1 && iSixes == 1){
            iBigStraight = 20;
        }

        if (bOnes != false) txtOnes.setText(String.valueOf(iOnes * 1));
        if (bTwos != false) txtTwos.setText(String.valueOf(iTwos * 2));
        if (bThrees != false) txtThrees.setText(String.valueOf(iThrees * 3));
        if (bFours != false) txtFours.setText(String.valueOf(iFours * 4));
        if (bFives != false) txtFives.setText(String.valueOf(iFives * 5));
        if (bSixs != false) txtSixs.setText(String.valueOf(iSixes * 6));
        if (bPair != false) txtPair.setText(String.valueOf(iPair));
        if (bTwoPairs != false) txtTwoPairs.setText(String.valueOf(iTwoPair));
        if (bThreeOfKind != false) txtThreeOfKind.setText(String.valueOf(iThreeOfKind));
        if (bLitleStraight != false) txtLittleStraight.setText(String.valueOf(iLittleStraight));
        if (bBigStraight != false) txtBigStraight.setText(String.valueOf(iBigStraight));
        if (bFullHouse != false) txtFull.setText(String.valueOf(iFullHouse));
        if (bFourOfKind != false) txtFourOfKind.setText(String.valueOf(iFourOfKind));
        if (bYathzee != false) txtYathzee.setText(String.valueOf(iYathzee));
        if (bChance != false) txtChance.setText(String.valueOf((iOnes * 1) + (iTwos * 2) + (iThrees * 3) + (iFours * 4) + (iFives * 5) + (iSixes *6)));
    }

    @SuppressLint("NewApi")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor mySensor = sensorEvent.sensor;
        textThrows = findViewById(R.id.textThrows);
        Button btnSave = findViewById(R.id.btnSave);

            if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                long curTime = System.currentTimeMillis();

                if ((curTime - lastUpdate) > 300) {
                    long diffTime = (curTime - lastUpdate);
                    lastUpdate = curTime;

                    float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                    if (speed > SHAKE_THRESHOLD) {
                        if(rounds > 0){
                            if(shoots > 0){
                                getRandomNumber();
                                afterUpdate();
                                shoots -= 1;
                                textThrows.setText(String.valueOf(shoots));
                            }
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