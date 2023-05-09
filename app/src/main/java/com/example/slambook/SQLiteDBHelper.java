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
                "`" + DB_Slam.Slams.SLAMID + "` INTEGER PRIMARY KEY," + //0
                "`" + DB_Slam.Slams.COMPLETE_NAME + "` TEXT NOT NULL," + //1
                "`" + DB_Slam.Slams.NICKNAME + "` TEXT NOT NULL," + //2
                "`" + DB_Slam.Slams.BIRTHDAY + "` DATE NOT NULL," + //3
                "`" + DB_Slam.Slams.BDAYWISH + "` TEXT NOT NULL," + //4
                "`" + DB_Slam.Slams.COLOR + "` TEXT NOT NULL," + //5
                "`" + DB_Slam.Slams.FOOD + "` TEXT NOT NULL," + //6
                "`" + DB_Slam.Slams.MUSIC + "` TEXT NOT NULL," + //7
                "`" + DB_Slam.Slams.USERMSG + "` TEXT NOT NULL," +//8
                "`" + DB_Slam.Slams.DATE + "` TEXT NOT NULL," + //9
                "`" + DB_Slam.Slams.LOGGEDIN + "` TEXT NOT NULL," + //10
                " UNIQUE (`" + DB_Slam.Slams.SLAMID + "`) ON CONFLICT ABORT);";

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
    //change to user ID
    public boolean insertSlams(String name, String nickname, String birthday,
                               String bdaywish, String color, String food, String music, String msg, String currentDate, String loggedUser) {
        ContentValues values = new ContentValues();
        values.put(DB_Slam.Slams.COMPLETE_NAME, name);
        values.put(DB_Slam.Slams.NICKNAME, nickname);
        values.put(DB_Slam.Slams.BIRTHDAY, birthday);
        values.put(DB_Slam.Slams.BDAYWISH, bdaywish);
        values.put(DB_Slam.Slams.COLOR, color);
        values.put(DB_Slam.Slams.FOOD, food);
        values.put(DB_Slam.Slams.MUSIC, music);
        values.put(DB_Slam.Slams.USERMSG, msg);
        values.put(DB_Slam.Slams.DATE, currentDate);
        values.put(DB_Slam.Slams.LOGGEDIN, loggedUser);
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
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor selectSlamsByUser(String loggedinuser)
    {
        String selection = "answer_loggedin=?";
        String[] selectionArgs = {loggedinuser};
        DB = this.getReadableDatabase();
        Cursor result = DB.query(DB_Slam.Slams.SLAM_TABLE, null, selection, selectionArgs, null, null, null);
        return result;
    }
    public Cursor selectSlamByID(String slamID)
    {
        String selection = "slam_id=?";
        String[] selectionArgs = {slamID};
        DB = this.getReadableDatabase();
        Cursor result = DB.query(DB_Slam.Slams.SLAM_TABLE, null, selection, selectionArgs, null, null, null);
        return result;
    }
    public Cursor selectUser()
    {
        DB = this.getReadableDatabase();
        Cursor result = DB.query(DB_Slam.User.USER_TABLE, null, null, null, null, null, null);
        return result;
    }

    public boolean checkAccount(String username, String password)
    {
        String[] columns = {DB_Slam.User.USERNAME};
        String selection = "user_username=? and user_password=?";
        String[] selectionArgs = {username, password};
        Cursor check = DB.query(DB_Slam.User.USER_TABLE, columns,selection,selectionArgs,null,null ,null);
        int count = check.getCount();
        check.close();

        if (count > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
