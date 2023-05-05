package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addSlam extends AppCompatActivity {
Button btn_save;
EditText edt_name, edt_nickname, edt_birthday, edt_wish, edt_color, edt_food, edt_music;
    SQLiteDBHelper myDB;
    SQLiteDatabase DB;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_slam);
        myDB = new SQLiteDBHelper(context);
        init();

    }

private void init()
{
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
            String name = edt_name.getText().toString();
            String nickname  = edt_nickname.getText().toString();
            String birthday = edt_birthday.getText().toString();
            String bdaywish = edt_wish.getText().toString();
            String color = edt_color.getText().toString();
            String food = edt_food.getText().toString();
            String music = edt_music.getText().toString();
            if (myDB.insertUser(name, nickname, birthday, bdaywish, color, food, music)) {
                Toast.makeText(context, "New User Added.", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(context, "Insert failed.", Toast.LENGTH_SHORT).show();

            }
        }
    });
}

}