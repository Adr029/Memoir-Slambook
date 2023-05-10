package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class birthday extends AppCompatActivity {

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