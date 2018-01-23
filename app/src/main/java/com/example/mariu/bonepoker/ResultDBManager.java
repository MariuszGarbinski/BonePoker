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

public class ResultDBManager {
    private DBHelper dbHelper;

    public ResultDBManager(Context context){
        dbHelper = new DBHelper(context);
    }

    public int insert(Result result){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr;
        dateStr = sdf.format(result.Tmstmp);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Result.KEY_D1, result.dice1);
        values.put(Result.KEY_D2, result.dice2);
        values.put(Result.KEY_D3, result.dice3);
        values.put(Result.KEY_D4, result.dice4);
        values.put(Result.KEY_D5, result.dice5);
        values.put(Result.KEY_DATE, dateStr);

        long resultId = db.insert(Result.TABLE, null, values);
        db.close();
        return (int)resultId;
    }

    public List<Result> getHistoryResults() throws ParseException {
        List<Result> retList = new ArrayList<Result>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT "+Result.KEY_D1
                +", "+Result.KEY_D2
                +", "+Result.KEY_D3
                +", "+Result.KEY_D4
                +", "+Result.KEY_D5
                +", "+Result.KEY_DATE
                +" FROM "+Result.TABLE
                +" ORDER BY "+Result.KEY_DATE+" DESC";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String tempDate;
            do{
                Result res = new Result();
                res.dice1 = cursor.getInt(cursor.getColumnIndex(Result.KEY_D1));
                res.dice2 = cursor.getInt(cursor.getColumnIndex(Result.KEY_D2));
                res.dice3 = cursor.getInt(cursor.getColumnIndex(Result.KEY_D3));
                res.dice4 = cursor.getInt(cursor.getColumnIndex(Result.KEY_D4));
                res.dice5 = cursor.getInt(cursor.getColumnIndex(Result.KEY_D5));
                tempDate = cursor.getString(cursor.getColumnIndex(Result.KEY_DATE));
                if(tempDate == null || tempDate.length() == 0){
                    res.Tmstmp = null;
                }
                else{
                    res.Tmstmp = sdf.parse(tempDate);
                }

                retList.add(res);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return retList;
    }
}