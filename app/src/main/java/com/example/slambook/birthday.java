package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class birthday extends AppCompatActivity {

    ImageButton btn_Home, btn_AddSlam, btn_Birthday, btn_Settings;
    String username, userfullName;
    Context context = this;
    SQLiteDBHelper myDB;
    TextView textUser;
    TextView textBday;
    String loggedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday);
        myDB = new SQLiteDBHelper(context);
        init();
        loggedin = getIntent().getStringExtra("username");
        displayBirthdays();

    }

    public void init()
    {
    textUser = findViewById(R.id.txt_user);
    textBday = findViewById(R.id.txt_date);
    btn_AddSlam = findViewById(R.id.navBtn_addSlam_bday);
    btn_Home = findViewById(R.id.navBtn_home_bday);
    btn_Birthday = findViewById(R.id.navBtn_bday_bday);
    btn_Settings = findViewById(R.id.settings_bday);

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(birthday.this, homePage.class);
                home.putExtra("username", loggedin);
                home.putExtra("userfullName", userfullName);;
                startActivity(home);
            }
        });
        btn_AddSlam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSlam = new Intent(birthday.this, addSlam.class);
                newSlam.putExtra("username", loggedin);
                newSlam.putExtra("userfullName", userfullName);;
                startActivity(newSlam);
            }
        });

        btn_Birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent birthday = new Intent(birthday.this, birthday.class);
                birthday.putExtra("username", loggedin);
                startActivity(birthday);
            }
        });
        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(birthday.this, settings.class);
                settings.putExtra("username", loggedin);
                startActivity(settings);
            }
        });

    }
    public void onBackPressed() {
    }
   private void displayBirthdays()
    {
        textUser.setText("");
        textBday.setText("");

        Cursor result = myDB.selectSlamsByUser(loggedin);
        if (result.getCount() == 0)
        {
            textUser.setText("No Data");

        }
        else
        {
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            while (result.moveToNext())
            {
                stringBuffer.append(" " + result.getString(1) +"\n\n");
                stringBuffer2.append(" " + result.getString(3) +"\n\n");
            }

            textUser.setText(stringBuffer);
            textBday.setText(stringBuffer2);
        }
    }

}