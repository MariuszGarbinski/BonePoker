package com.example.mariu.bonepoker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Piotr on 22.10.2017.
 */

public class Result {

    //Table name
    public static final String TABLE = "Result";

    public static final String KEY_ID = "id";
    public static final String KEY_D1 = "dice1";
    public static final String KEY_D2 = "dice2";
    public static final String KEY_D3 = "dice3";
    public static final String KEY_D4 = "dice4";
    public static final String KEY_D5 = "dice5";
    public static final String KEY_DATE = "tmstmp";

    public int dice1;
    public int dice2;
    public int dice3;
    public int dice4;
    public int dice5;
    public Date Tmstmp;

    @Override
     public String toString(){
                String dateStr;
                if(Tmstmp != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    dateStr = sdf.format(Tmstmp);
                }
                else{
                    dateStr = "Brak daty";
                }
                return dateStr+": "+ dice1+" "+dice2+" "+dice3+" "+dice4+" "+dice5;
     }

}
