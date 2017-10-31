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
        instruction.setText("1. Cel gry:\n\n" +
                "Celem gry jest uzyskanie jak największej ilości punktów. Gracz " +
                "otrzymuje punkty za wyrzucenie odpowiednich oczek na kościach do gry. " +
                "W każdej rundzie gram może oddać maksymalnie trzy rzuty. Pierwszy rzut " +
                "zawsze wykonywany jest wszystkimi kośćmi, a następnie gracz rzuca wytypowanymi " +
                "przez siebie kośćmi.\n\n" +
                "2. Rzuty kośćmi i ich wybór\n\n" +
                "Rzutu koścmi dokonuje się poprzez potrząśnięcie telefonem. Aby zablokować kości, " +
                "które będą niezmienne podczas kolejnych rzutów, gracz musi przesunąć suwak pod kością. " +
                " Przesuwając ponownie suwak gracz może oblokować kość, która ponownie będzie rzucana.\n\n" +
                "3. Punktacja\n" +
                "");
    }
}