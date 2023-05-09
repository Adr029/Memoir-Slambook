package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class addSlam extends AppCompatActivity {
Button btn_save;
EditText edt_name, edt_nickname, edt_birthday, edt_wish, edt_color, edt_food, edt_music, edt_msg;
SQLiteDBHelper myDB;
Context context = this;
String loggedin, userfullName;
TextView loggedinUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_slam);
        myDB = new SQLiteDBHelper(context);
        loggedin = getIntent().getStringExtra("username");
        userfullName = getIntent().getStringExtra("userfullName");
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
    edt_msg = findViewById(R.id.input_slamMsg);
    loggedinUser = findViewById(R.id.txt_userLoggedIn);
    loggedinUser.setText(userfullName +",");
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
            String msg = edt_msg.getText().toString();
            String loggedUser = loggedin;
            String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
if (name.length()!=0 && nickname.length()!=0 && birthday.length()!=0 && bdaywish.length()!=0 && color.length()!=0 && food.length()!=0 && music.length()!=0 && msg.length()!=0)
{
    if (myDB.insertSlams(name, nickname, birthday, bdaywish, color, food, music, msg, currentDate, loggedUser)) {
        Toast.makeText(context, "New Slam Added", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(addSlam.this, homePage.class);
        home.putExtra("username", loggedin);
        startActivity(home);

    } else {
        Toast.makeText(context, "Insert failed", Toast.LENGTH_SHORT).show();

    }
}
else {
    Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();

}
        }
    });
}

}