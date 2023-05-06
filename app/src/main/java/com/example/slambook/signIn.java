package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signIn extends AppCompatActivity {
Button btn_signIn;
Button btn_signUp;
    SQLiteDBHelper myDB;
String username;
EditText name;
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
        name = findViewById(R.id.input_username);
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

    public void signIn()
    {
    username = name.getText().toString();
        if (myDB.checkUsername(username)) {
            Intent home = new Intent(signIn.this, homePage.class);
            startActivity(home);
            home.putExtra("username", username);
            startActivity(home);
        }
        else {
            Toast.makeText(context, "User does not exist", Toast.LENGTH_SHORT).show();

        }

    }
}