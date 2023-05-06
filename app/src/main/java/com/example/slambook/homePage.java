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
    Context context = this;
    SQLiteDBHelper myDB;
    TextView textDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        myDB = new SQLiteDBHelper(context);
        init();
        displaySlams();

    }
    public void init()
    {
        sampleLogOut = findViewById(R.id.testLogOut);
        sampleAddSlam = findViewById(R.id.addSlam);
        textDB = findViewById(R.id.txtDB);
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
                startActivity(newSlam);
            }
        });
    }
    private void displaySlams()
    {
        textDB.setText("");

        Cursor result = myDB.selectSlams();
        if (result.getCount() == 0)
        {
            textDB.setText("No Data");

        }
        else
        {
            StringBuffer stringBuffer = new StringBuffer();
            while (result.moveToNext())
            {
                stringBuffer.append("ID " + result.getString(1) + " " + result.getString(2)+ " " +result.getString(3) + " "
                        + " "+ result.getString(4)+ " " +result.getString(5) + " " + result.getString(6)+ " "+ result.getString(7)+"\n\n");
            }
            textDB.setText(stringBuffer);
        }
    }
}