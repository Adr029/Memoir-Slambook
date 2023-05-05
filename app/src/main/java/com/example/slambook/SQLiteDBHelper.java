package com.example.slambook;

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
    public SQLiteDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        SQLiteDatabase DB = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        final String CREATE_USER_TABLE = "CREATE TABLE `"+DB_Slam.User.USER_TABLE+"` (" +
                "`"+DB_Slam.User.ID+"` INTEGER PRIMARY KEY," +
                "`"+DB_Slam.User.COMPLETE_NAME+"` TEXT NOT NULL," +
                "`"+DB_Slam.User.USERNAME+"` TEXT NOT NULL," +
                "`"+DB_Slam.User.PASSWORD+"` TEXT NOT NULL," +
                "`"+DB_Slam.User.BIRTHDAY+"` DATE," +
                "`"+DB_Slam.User.ADDRESS+"` TEXT," +
                " UNIQUE (`"+DB_Slam.User.ID+"`) ON CONFLICT ABORT);";

        final String CREATE_POST_TABLE = "CREATE TABLE `"+DB_Slam.Post.POST_TABLE+"` (" +
                "`"+DB_Slam.Post.ID+"` INTEGER PRIMARY KEY," +
                "`"+DB_Slam.Post.USERNAME+"` TEXT NOT NULL," +
                "`"+DB_Slam.Post.TEXT+"` TEXT NOT NULL," +
                "`"+DB_Slam.Post.DATE+"` DATE," +
                " FOREIGN KEY (`"+DB_Slam.Post.USERNAME+"`) REFERENCES " +
                " `"+DB_Slam.User.USER_TABLE+"`(`"+DB_Slam.User.ID+"`)," +
                " UNIQUE (`"+DB_Slam.Post.ID+"`) ON CONFLICT ABORT);";
        try {
            DB.execSQL(CREATE_USER_TABLE);
            Toast.makeText(context, "Table User Created.", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DB.execSQL(CREATE_POST_TABLE);
            Toast.makeText(context, "Table Post Created.", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        {
            final String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+DB_Slam.User.USER_TABLE;
            final String DROP_POST_TABLE = "DROP TABLE IF EXISTS "+DB_Slam.Post.POST_TABLE;
            DB.execSQL(DROP_USER_TABLE);
            DB.execSQL(DROP_POST_TABLE);
            onCreate(DB);
        }

    }
}
