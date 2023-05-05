package com.example.slambook;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

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
        final String CREATE_USER_TABLE = "CREATE TABLE `" + DB_Slam.User.USER_TABLE + "` (" +
                "`" + DB_Slam.User.ID + "` INTEGER PRIMARY KEY," +
                "`" + DB_Slam.User.COMPLETE_NAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.User.NICKNAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.User.BDAYWISH + "` TEXT NOT NULL," +
                "`" + DB_Slam.User.BIRTHDAY + "` DATE," +
                "`" + DB_Slam.User.COLOR + "` TEXT," +
                "`" + DB_Slam.User.FOOD + "` TEXT," +
                "`" + DB_Slam.User.MUSIC + "` TEXT," +
                " UNIQUE (`" + DB_Slam.User.ID + "`) ON CONFLICT ABORT);";

        final String CREATE_POST_TABLE = "CREATE TABLE `" + DB_Slam.Post.POST_TABLE + "` (" +
                "`" + DB_Slam.Post.ID + "` INTEGER PRIMARY KEY," +
                "`" + DB_Slam.Post.USERNAME + "` TEXT NOT NULL," +
                "`" + DB_Slam.Post.TEXT + "` TEXT NOT NULL," +
                "`" + DB_Slam.Post.DATE + "` DATE," +
                " FOREIGN KEY (`" + DB_Slam.Post.USERNAME + "`) REFERENCES " +
                " `" + DB_Slam.User.USER_TABLE + "`(`" + DB_Slam.User.ID + "`)," +
                " UNIQUE (`" + DB_Slam.Post.ID + "`) ON CONFLICT ABORT);";
        try {
            DB.execSQL(CREATE_USER_TABLE);
            //Toast.makeText(context, "Table User Created.", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DB.execSQL(CREATE_POST_TABLE);
            //Toast.makeText(context, "Table Post Created.", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        {
            final String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + DB_Slam.User.USER_TABLE;
            final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + DB_Slam.Post.POST_TABLE;
            DB.execSQL(DROP_USER_TABLE);
            DB.execSQL(DROP_POST_TABLE);
            onCreate(DB);

        }

    }
    public boolean insertUser(String name, String nickname, String birthday,
                              String bdaywish, String color, String food, String music) {
        ContentValues values = new ContentValues();
        values.put(DB_Slam.User.COMPLETE_NAME, name);
        values.put(DB_Slam.User.NICKNAME, nickname);
        values.put(DB_Slam.User.BIRTHDAY, birthday);
        values.put(DB_Slam.User.BDAYWISH, bdaywish);
        values.put(DB_Slam.User.COLOR, color);
        values.put(DB_Slam.User.FOOD, food);
        values.put(DB_Slam.User.MUSIC, music);
        long result = DB.insert(DB_Slam.User.USER_TABLE, null, values);
        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }
}
