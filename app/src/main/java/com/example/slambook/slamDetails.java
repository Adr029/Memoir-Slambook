package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class slamDetails extends AppCompatActivity {
    SQLiteDBHelper myDB;
    Context context = this;
    String slamID, name, nickname, bday, bdaywish, color, food, music, msg, loggedinUser, userfullName;
TextView txt_name, txt_nickname, txt_birthday, txt_bdaywish, txt_color, txt_food, txt_music, txt_msg, txt_userLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slam_details);
        Intent intent = getIntent();
        slamID = intent.getStringExtra("slamID");
        userfullName = intent.getStringExtra("userfullName");
        myDB = new SQLiteDBHelper(context);
        init();
        displaySlam();

    }

    public void init()
    {
    txt_name = findViewById(R.id.output_name);
    txt_nickname = findViewById(R.id.output_nickname);
    txt_birthday = findViewById(R.id.output_bday);
    txt_bdaywish = findViewById(R.id.output_wish);
    txt_color = findViewById(R.id.output_color);
    txt_food = findViewById(R.id.output_food);
    txt_music = findViewById(R.id.output_music);
    txt_msg = findViewById(R.id.output_message);
    txt_userLoggedIn = findViewById(R.id.txt_userLoggedIn);
    }

    public void displaySlam()
    {
        Cursor result = myDB.selectSlamByID(slamID);
        while (result.moveToNext())
        {
            name = result.getString(1);
            nickname = result.getString(2);
            bday = result.getString(3);
            bdaywish = result.getString(4);
            color = result.getString(5);
            food = result.getString(6);
            music = result.getString(7);
            msg = result.getString(8);
            loggedinUser = result.getString(10);
        }
        txt_name.setText(name);
        txt_nickname.setText(nickname);
        txt_birthday.setText(bday);
        txt_bdaywish.setText(bdaywish);
        txt_color.setText(color);
        txt_food.setText(food);
        txt_music.setText(music);
        txt_msg.setText(msg);
        txt_userLoggedIn.setText(userfullName);

    }
}