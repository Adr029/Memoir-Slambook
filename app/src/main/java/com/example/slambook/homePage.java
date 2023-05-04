package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homePage extends AppCompatActivity {
    Button sampleLogOut;
    Button sampleAddSlam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        sampleLogOut = findViewById(R.id.testLogOut);
        sampleAddSlam = findViewById(R.id.addSlam);

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
}