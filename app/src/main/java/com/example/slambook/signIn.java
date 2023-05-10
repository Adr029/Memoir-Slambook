package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signIn extends AppCompatActivity {
Button btn_signIn, btn_signUp;
SQLiteDBHelper myDB;
String username, password, name;
EditText txt_name, txt_password;
Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        myDB = new SQLiteDBHelper(context);
        init();

    }

    public void init()
    {
        btn_signIn = findViewById(R.id.signIn);
        btn_signUp = findViewById(R.id.signUp);
        txt_name = findViewById(R.id.input_username);
        txt_password = findViewById(R.id.settings_password);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(signIn.this, signUp.class);
                startActivity(signUp);
            }
        });

    }
    @Override
    public void onBackPressed() {
    }
    public void signIn()
    {
    username = txt_name.getText().toString();
    password = txt_password.getText().toString();
        if (myDB.checkCredentials(username, password))
        {
            Cursor result = myDB.selectUserByUsername(username);
            while (result.moveToNext())
            {
                name = result.getString(1);
            }
            Intent home = new Intent(signIn.this, homePage.class);
            home.putExtra("username", username);
            home.putExtra("name", name);
            startActivity(home);

        }
        else {
            Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();

        }

    }
}