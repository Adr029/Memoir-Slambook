package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addSlam extends AppCompatActivity {
Button btn_save;
EditText edt_name, edt_nickname, edt_birthday, edt_wish, edt_color, edt_food, edt_music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_slam);
        btn_save = findViewById(R.id.trySave);
        edt_name = findViewById(R.id.input_slamName);
        edt_nickname = findViewById(R.id.input_slamNickname);
        edt_birthday = findViewById(R.id.input_slamBirthday);
        edt_wish = findViewById(R.id.input_slamBirthdayWish);
        edt_color = findViewById(R.id.input_slamColor);
        edt_food = findViewById(R.id.input_slamFood);
        edt_music = findViewById(R.id.input_slamMusic);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}