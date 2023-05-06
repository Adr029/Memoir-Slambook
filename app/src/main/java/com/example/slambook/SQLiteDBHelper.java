package com.example.slambook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "Slams.db";
    private static int VERSION = 1;
    Context context;
    SQLiteDatabase DB = this.getWritableDatabase();

    public SQLiteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        final String CREATE_SLAM_TABLE = "CREATE TABLE `" + DB_Slam.Slams.SLAM_TABLE + "` (" +
                "`" + DB_Slam.Slams.ID + "` INTEGER PRIMARY KEY," +
                "`" + DB_Slam.Slams.COMPLETE_NAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.NICKNAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.BDAYWISH + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.BIRTHDAY + "` DATE NOT NULL," +
                "`" + DB_Slam.Slams.COLOR + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.FOOD + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.MUSIC + "` TEXT NOT NULL," +
                "`" + DB_Slam.Slams.DATE + "` TEXT NOT NULL," +
                " UNIQUE (`" + DB_Slam.Slams.ID + "`) ON CONFLICT ABORT);";

        final String CREATE_USER_TABLE = "CREATE TABLE `" + DB_Slam.User.USER_TABLE + "` (" +
                "`" + DB_Slam.User.ID + "` INTEGER PRIMARY KEY," +
                "`" + DB_Slam.User.NAME+ "` TEXT NOT NULL," +
                "`" + DB_Slam.User.USERNAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.User.PASSWORD + "` TEXT NOT NULL," +
                " UNIQUE (`" + DB_Slam.User.ID + "`) ON CONFLICT ABORT);";
        try {
            DB.execSQL(CREATE_SLAM_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DB.execSQL(CREATE_USER_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        {
            final String DROP_SLAM_TABLE = "DROP TABLE IF EXISTS " + DB_Slam.Slams.SLAM_TABLE;
            final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + DB_Slam.User.USER_TABLE;
            DB.execSQL(DROP_SLAM_TABLE);
            DB.execSQL(DROP_USER_TABLE);
            onCreate(DB);

        }

    }
    public boolean insertSlams(String name, String nickname, String birthday,
                               String bdaywish, String color, String food, String music, String currentDate) {
        ContentValues values = new ContentValues();
        values.put(DB_Slam.Slams.COMPLETE_NAME, name);
        values.put(DB_Slam.Slams.NICKNAME, nickname);
        values.put(DB_Slam.Slams.BIRTHDAY, birthday);
        values.put(DB_Slam.Slams.BDAYWISH, bdaywish);
        values.put(DB_Slam.Slams.COLOR, color);
        values.put(DB_Slam.Slams.FOOD, food);
        values.put(DB_Slam.Slams.MUSIC, music);
        values.put(DB_Slam.Slams.DATE, currentDate);
        long result = DB.insert(DB_Slam.Slams.SLAM_TABLE, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }
    public boolean insertUser(String name, String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DB_Slam.User.NAME, name);
        values.put(DB_Slam.User.USERNAME, username);
        values.put(DB_Slam.User.PASSWORD, password);

        long result = DB.insert(DB_Slam.User.USER_TABLE, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }
    public Cursor selectSlams()
    {
        DB = this.getReadableDatabase();
        Cursor result = DB.query(DB_Slam.Slams.SLAM_TABLE, null, null, null, null, null, null);
        return result;
    } public Cursor selectUSer()
    {
        DB = this.getReadableDatabase();
        Cursor result = DB.query(DB_Slam.User.USER_TABLE, null, null, null, null, null, null);
        return result;
    }

    public boolean checkUsername(String username)
    {
        Cursor check = DB.query(DB_Slam.User.USER_TABLE, null   ,null,null   ,null,null ,null);

        if (check.getCount() == 1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
