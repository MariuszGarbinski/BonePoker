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
        TextView instruction1 = (TextView) findViewById(R.id.txtInstruction1);
        instruction.setText("1. Cel gry:\n\n" +
                "Celem gry jest uzyskanie jak największej ilości punktów. " +
                "Gracz w swojej kolejce może wykonać trzy rzuty. Gracz stara się " +
                "uzyskać jedną z kombinacji punktowanych. Podczas drugiego i " +
                "trzeciego rzutu gracz może część kości odłożyć i rzucać pozostałymi." +
                "Maksymalnie po trzecim rzucie wynik musi być zapisany do jednej z " +
                "wybranych kategorii w tabelce poniżej kości klikając jej etykietę. " +
                "Na początku wynik można wpisać do wielu kategorii. Pod koniec partii " +
                "może okazać się, że wyniku nie można wpisać do żadnej z wolnych " +
                "kategorii.  W takiej sytuacji gracz musi zdecydować, z której kategorii " +
                "zrezygnuje i tam wpisać wynik zerowy. Wpisów (również zerowych) nie można " +
                "zmieniać do końca partii. Zakończenie partii następuje po wypełnieniu całej tabelki." +
                " \n\n" +
                "2. Rzuty kośćmi i ich wybór\n\n" +
                "Rzutu koścmi dokonuje się poprzez potrząśnięcie telefonem. Aby zablokować kości, " +
                "które będą niezmienne podczas kolejnych rzutów, gracz musi przesunąć suwak pod kością. " +
                " Przesuwając ponownie suwak gracz może oblokować kość, która ponownie będzie rzucana.\n\n" +
                "3. Punktacja\n");

        instruction1.setText("4. Zapis i odczyt wyników:\n\n" +
                "Po ukończeniu gry (15 rund), przycisk 'ZAPISZ WYNIK' zostanie aktywowany, " +
                "który podczas gry jest nieaktywny. Po jego kliknięciu wynik wraz z datą " +
                "i godziną zakończenia zostanie zapisany w bazie wyników. " +
                "W każdej chwili można sprawdzić uzyskane wyniki poprzez kliknięcie przycisku " +
                "'OSIĄGNIĘCIA', znajdujący się na panelu głównym gry. Zotsną wyświetlone " +
                "wszystkie uzyskane wyniki gier.");
    }
}