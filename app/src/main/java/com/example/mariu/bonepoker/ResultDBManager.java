package com.example.mariu.bonepoker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Piotr on 22.10.2017.
 */

public class ResultDBManager {
    private DBHelper dbHelper;

    public ResultDBManager(Context context){
        dbHelper = new DBHelper(context);
    }

    public int insert(Result result){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Result.KEY_D1, result.dice1);
        values.put(Result.KEY_D2, result.dice2);
        values.put(Result.KEY_D3, result.dice3);
        values.put(Result.KEY_D4, result.dice4);
        values.put(Result.KEY_D5, result.dice5);

        long resultId = db.insert(Result.TABLE, null, values);
        db.close();
        return (int)resultId;
    }
}
