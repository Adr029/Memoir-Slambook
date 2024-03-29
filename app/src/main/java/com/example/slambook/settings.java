package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    Button btn_logOut, btn_confirm, btn_delete;
    Context context = this;
    String oldPass, newPass, username, userfullName;
    SQLiteDBHelper myDB;
    EditText input_oldPass, input_newPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        myDB = new SQLiteDBHelper(context);
        username =  getIntent().getStringExtra("username");
        userfullName =  getIntent().getStringExtra("name");
        init();

    }

    public void init() {
        btn_logOut = findViewById(R.id.logOut);
        btn_confirm = findViewById(R.id.settings_confirm);
        btn_delete = findViewById(R.id.settings_delete);
        input_oldPass = findViewById(R.id.settings_password);
        input_newPass = findViewById(R.id.input_NewPassword);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                changePass();
            }
        });
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(settings.this, signIn.class);
                startActivity(logout);

            }

        });
    btn_delete.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View view) {
            deleteAllSlams();
        }
        });
        ImageButton back = (ImageButton)findViewById(R.id.back_settings);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void deleteAllSlams()
    {
        if (myDB.deleteAllSlamsByID(username)) {
            Toast.makeText(context, "All Slams Deleted", Toast.LENGTH_SHORT).show();
            Intent home = new Intent(settings.this, homePage.class);
            home.putExtra("username", username);
            home.putExtra("name", userfullName);
            startActivity(home);

        }
    }
    public void changePass() {
        oldPass = input_oldPass.getText().toString();
        newPass = input_newPass.getText().toString();

        if (oldPass.length() !=0 && newPass.length()!=0)
        {
            if (myDB.checkUserPassword(oldPass)) {
                if (myDB.updatePassword(username, newPass))
                {
                    input_oldPass.setText("");
                    input_newPass.setText("");
                    Toast.makeText(context, "Password changed.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(context, "Incorrect password.", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }
}