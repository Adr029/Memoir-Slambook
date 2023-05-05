package com.example.slambook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Slams.db";
    private static int VERSION = 1;
    Context context;
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {

    }
}
