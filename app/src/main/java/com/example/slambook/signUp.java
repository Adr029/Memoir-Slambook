package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class signUp extends AppCompatActivity {
Button btn_signUp, btn_signIn;
Context context = this;
SQLiteDBHelper myDB;

EditText edt_name, edt_username, edt_password, edt_confirmPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        myDB = new SQLiteDBHelper(context);
        init();

    }
    public void init()
    {
        btn_signIn = findViewById(R.id.signIn2);
        btn_signUp = findViewById(R.id.signUp2);
        edt_name = findViewById(R.id.txt_nameReg);
        edt_username = findViewById(R.id.txt_usernameReg);
        edt_password = findViewById(R.id.txt_passwordReg);
        edt_confirmPW = findViewById(R.id.txt_confirmPW);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(signUp.this, signIn.class);
                startActivity(signIn);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edt_name.getText().toString();
                String username  = edt_username.getText().toString();
                String password = edt_password.getText().toString();
                String confirmpass = edt_confirmPW.getText().toString();
    if (name.length()!= 0 && username.length() != 0 && password.length()!= 0)
    {
        if (password.equals(confirmpass))
        {
            if (myDB.insertUser(name, username, password))
            {
                Toast.makeText(context, "New User Added", Toast.LENGTH_SHORT).show();
                Intent home = new Intent(signUp.this, homePage.class);
                home.putExtra("username", username);
                startActivity(home);
            }
            else
            {
                Toast.makeText(context, "Insert failed", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }

    }

    else
    {
        Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

    }

            }
        });
    }
}