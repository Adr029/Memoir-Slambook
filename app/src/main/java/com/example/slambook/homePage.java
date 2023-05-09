package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity implements  itemOnClick{
    Button sampleLogOut, btn_AddSlam, btn_Birthday;
    Context context = this;
    SQLiteDBHelper myDB;
    TextView textDB, usernameSample;
    String username, slamID, slamAuthor, slamDate;

    RecyclerView slamRecycler;
    recyclerAdapter slamAdapter;
    ArrayList<model> modelList;
    private itemOnClick listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        myDB = new SQLiteDBHelper(context);
        modelList = new ArrayList<>();
        init();
        displaySlams();

    }
    public void init()
    {
        slamAdapter = new recyclerAdapter(homePage.this, modelList, this);
        slamRecycler = findViewById(R.id.recycler_slam);
        slamRecycler.setAdapter(slamAdapter);
        slamRecycler.setLayoutManager(new LinearLayoutManager(homePage.this));
        usernameSample = findViewById(R.id.usernameSample);
        sampleLogOut = findViewById(R.id.testLogOut);
        btn_AddSlam = findViewById(R.id.addSlam);
        btn_Birthday = findViewById(R.id.birthdayPage);
        textDB = findViewById(R.id.txt_noSlam);
        usernameSample.setText(username);
        sampleLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(homePage.this, signIn.class);
                startActivity(logout);
            }
        });

        btn_AddSlam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newSlam = new Intent(homePage.this, addSlam.class);
                newSlam.putExtra("username", username);
                startActivity(newSlam);
            }
        });

        btn_Birthday.setOnClickListener(new View.OnClickListener() {
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
        Cursor result = myDB.selectSlamsByUser(loggedinuser);
        if (result.getCount() == 0)
        {
            textDB.setText("No Slams Yet");
        }
        else
        {
            while (result.moveToNext())
            {
                slamID = result.getString(0);
                slamAuthor = result.getString(1);
                slamDate = result.getString(9);
                modelList.add(new model(slamID, slamAuthor, slamDate));
            }
            textDB.setText("");
        }
    }

    @Override
    public void onItemClicked(model slamModel) {
        slamID = slamModel.getSlam_id();
        Intent slam = new Intent(homePage.this, slamDetails.class);
        slam.putExtra("slamID", slamID);
        startActivity(slam);
    }
}