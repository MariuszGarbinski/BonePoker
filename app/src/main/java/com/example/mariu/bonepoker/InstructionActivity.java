package com.example.mariu.bonepoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InstructionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        TextView instruction = (TextView) findViewById(R.id.txtInstruction);
        instruction.setText("INSTRUKCJA:\n\n");
    }
}