package com.example.mariu.bonepoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piotr on 22.10.2017.
 */

public class SummaryDBManager {
    private DBHelper dbHelper;

    public SummaryDBManager(Context context){
        dbHelper = new DBHelper(context);
    }

    public int insert(Summary summary){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr;
        dateStr = sdf.format(summary.Tmstmp);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Summary.KEY_SUM, summary.sum);
        values.put(Result.KEY_DATE, dateStr);

        long resultId = db.insert(Summary.TABLE, null, values);
        db.close();
        return (int)resultId;
    }

    public List<Summary> getHistorySum() throws ParseException {
        List<Summary> retList = new ArrayList<Summary>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT "+Summary.KEY_SUM
                +", "+Summary.KEY_DATE
                +" FROM "+Summary.TABLE
                +" ORDER BY "+Summary.KEY_DATE+" DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tempDate;
            do{
                Summary sum = new Summary();
                sum.sum = cursor.getInt(cursor.getColumnIndex(Summary.KEY_SUM));
                tempDate = cursor.getString(cursor.getColumnIndex(Result.KEY_DATE));
                if(tempDate == null || tempDate.length() == 0){
                    sum.Tmstmp = null;
                }
                else{
                    sum.Tmstmp = sdf.parse(tempDate);
                }

                retList.add(sum);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return retList;
    }

}
