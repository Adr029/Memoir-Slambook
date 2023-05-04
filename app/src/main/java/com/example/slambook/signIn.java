package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signIn extends AppCompatActivity {
Button btn_signIn;
Button btn_signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        btn_signIn = findViewById(R.id.signIn);
        btn_signUp = findViewById(R.id.signUp);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent home = new Intent(signIn.this, homePage.class);
            startActivity(home);
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
}