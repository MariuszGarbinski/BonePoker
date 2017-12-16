package com.example.mariu.bonepoker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Piotr on 22.10.2017.
 */

public class Summary {

    //Table name
    public static final String TABLE = "Summary";

    public static final String KEY_ID = "id";
    public static final String KEY_SUM = "summary";
    public static final String KEY_DATE = "tmstmp";

    public int sum;
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
        return dateStr+": "+ sum;
    }

}
