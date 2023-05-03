package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button buttonforTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonforTest = findViewById(R.id.buttonTest);
       buttonforTest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent next= new Intent(MainActivity.this, MainActivity2.class);
            startActivity(next);
           }
       });
    }
}