package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class homePage extends AppCompatActivity {
    Button sampleLogOut;
    Button sampleAddSlam;
    Button birthdayPage;
    Context context = this;
    SQLiteDBHelper myDB;
    TextView textDB;
    TextView usernameSample;
    String username, slamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        myDB = new SQLiteDBHelper(context);
        init();
        displaySlams();

    }
    public void init()
    {
        usernameSample = findViewById(R.id.usernameSample);
        sampleLogOut = findViewById(R.id.testLogOut);
        sampleAddSlam = findViewById(R.id.addSlam);
        birthdayPage = findViewById(R.id.birthdayPage);
        textDB = findViewById(R.id.txtDB);

        usernameSample.setText(username);
        sampleLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(homePage.this, signIn.class);
                startActivity(logout);
            }
        });

        sampleAddSlam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSlam = new Intent(homePage.this, addSlam.class);
                newSlam.putExtra("username", username);
                startActivity(newSlam);
            }
        });

        birthdayPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent birthday = new Intent(homePage.this, birthday.class);
                startActivity(birthday);
            }
        });
    }
    private void displaySlams()
    {
        textDB.setText("");
        String loggedinuser = username;
        Cursor result = myDB.selectSlams(loggedinuser);
        if (result.getCount() == 0)
        {
            textDB.setText("No Data");

        }
        else
        {
            StringBuffer stringBuffer = new StringBuffer();
            while (result.moveToNext())
            {
                stringBuffer.append(" " + result.getString(0) + " "+ result.getString(1) + " " + result.getString(8)+"\n\n");
                slamID = result.getString(0);
            }
            textDB.setText(stringBuffer);
            textDB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent slam = new Intent(homePage.this, slamDetails.class);
                    slam.putExtra("slamID", slamID);
                    startActivity(slam);
                }
            });
        }
    }
}