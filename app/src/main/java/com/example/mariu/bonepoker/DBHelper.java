package com.example.mariu.bonepoker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Piotr on 22.10.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "diceResults.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_RESULT = "CREATE TABLE " + Result.TABLE + "("
                + Result.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Result.KEY_D1 + " INTEGER, "
                + Result.KEY_D2 + " INTEGER, "
                + Result.KEY_D3 + " INTEGER, "
                + Result.KEY_D4 + " INTEGER, "
                + Result.KEY_D5 + " INTEGER, "
                + Result.KEY_DATE + " DATETIME )";

        db.execSQL(CREATE_TABLE_RESULT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + Result.TABLE);
        onCreate(db);
    }

}
