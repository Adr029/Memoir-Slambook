package com.example.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addSlam extends AppCompatActivity {
Button btn_save;
ImageButton navBtn_bday, navBtn_home;
EditText edt_name, edt_nickname, edt_birthday, edt_wish, edt_color, edt_food, edt_music, edt_msg;
SQLiteDBHelper myDB;
Context context = this;
String loggedin, userfullName;
TextView loggedinUser;
String birthMonth, birthDay, birthData;
DatePickerDialog.OnDateSetListener setListener;
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
    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
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

    //navbar
    navBtn_bday = findViewById(R.id.navBtn_bday_addSlam);
    navBtn_home = findViewById(R.id.navBtn_home_addSlam);

    edt_birthday.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(addSlam.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth, setListener,year, month, day);
            datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            datePickerDialog.show();
        }
    });

    setListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            int placeholderYear = 1111;
            birthDay = String.valueOf(day);
            birthData = placeholderYear+"-"+month+"-"+day;
            switch (month)
            {
                case 0:
                    birthMonth = "January";
                    break;
                case 1:
                    birthMonth = "February";
                    break;
                case 2:
                    birthMonth = "March";
                    break;
                case 3:
                    birthMonth = "April";
                    break;
                case 4:
                    birthMonth = "May";
                    break;
                case 5:
                    birthMonth = "June";
                    break;
                case 6:
                    birthMonth = "July";
                    break;
                case 7:
                    birthMonth = "August";
                    break;
                case 8:
                    birthMonth = "September";
                    break;
                case 9:
                    birthMonth = "October";
                    break;
                case 10:
                    birthMonth = "November";
                    break;
                case 11:
                    birthMonth = "December";
                    break;
            }
            edt_birthday.setText(birthMonth + " "+day + ", "+year);
        }
    };
    btn_save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String name = edt_name.getText().toString();
            String nickname  = edt_nickname.getText().toString();
            String birthday = birthMonth + " "+ birthDay;
            String bdaywish = edt_wish.getText().toString();
            String color = edt_color.getText().toString();
            String food = edt_food.getText().toString();
            String music = edt_music.getText().toString();
            String msg = edt_msg.getText().toString();
            String loggedUser = loggedin;
            String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
if (name.length()!=0 && nickname.length()!=0 && birthday.length()!=0 && bdaywish.length()!=0 && color.length()!=0 && food.length()!=0 && music.length()!=0 && msg.length()!=0)
{
    if (myDB.insertSlams(name, nickname, birthday, bdaywish, color, food, music, msg, currentDate, loggedUser, birthData)) {
        Toast.makeText(context, "New Slam Added", Toast.LENGTH_SHORT).show();
        Intent home = new Intent(addSlam.this, homePage.class);
        home.putExtra("username", loggedin);
        home.putExtra("name", userfullName);
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
    //nav bar
    navBtn_bday.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent birthday = new Intent(addSlam.this, birthday.class);
            birthday.putExtra("username", loggedin);
            birthday.putExtra("name", userfullName);
            startActivity(birthday);
        }
    });
    navBtn_home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent homePage = new Intent(addSlam.this, homePage.class);
            homePage.putExtra("username", loggedin);
            homePage.putExtra("name", userfullName);
            startActivity(homePage);
        }
    });
}
    public void onBackPressed() {
    }

}