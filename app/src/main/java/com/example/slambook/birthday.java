package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class birthday extends AppCompatActivity {

    ImageButton btn_Home, btn_AddSlam, btn_Birthday, btn_Settings;
    String username, userfullName;
    Context context = this;
    SQLiteDBHelper myDB;
    String loggedin;
    RecyclerView birthdayReycler;
    birthdayAdapter BirthdayAdapter;
    ArrayList<String> author, bday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthday);
        myDB = new SQLiteDBHelper(context);
        init();
        loggedin = getIntent().getStringExtra("username");
        userfullName = getIntent().getStringExtra("name");
        displayBirthdays();

    }

    public void init()
    {
        author = new ArrayList<>();
        bday = new ArrayList<>();

        BirthdayAdapter = new birthdayAdapter(this, author, bday);
        birthdayReycler = findViewById(R.id.bday_recycler);
        birthdayReycler.setAdapter(BirthdayAdapter);
        birthdayReycler.setLayoutManager(new LinearLayoutManager(this));

    btn_AddSlam = findViewById(R.id.navBtn_addSlam_bday);
    btn_Home = findViewById(R.id.navBtn_home_bday);
    btn_Birthday = findViewById(R.id.navBtn_bday_bday);
    btn_Settings = findViewById(R.id.settings_bday);

        btn_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(birthday.this, homePage.class);
                home.putExtra("username", loggedin);
                home.putExtra("name", userfullName);;
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

        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(birthday.this, settings.class);
                settings.putExtra("username", loggedin);
                settings.putExtra("userfullName", userfullName);;

                startActivity(settings);
            }
        });

    }
    public void onBackPressed() {
    }
   private void displayBirthdays()
    {

        Cursor result = myDB.selectBirthdaysByUser(loggedin);
        if (result.getCount() == 0)
        {
            //textUser.setText("No Data"); yung parang sa no slams yet
        }
        else
        {
            while (result.moveToNext())
            {
                author.add(result.getString(1));
                bday.add(result.getString(3));
            }

        }
    }

}