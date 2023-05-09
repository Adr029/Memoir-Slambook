package com.example.slambook;

import android.provider.BaseColumns;

public class DB_Slam {

    public static class Slams implements BaseColumns {
        //Table
        public static String SLAM_TABLE = "table_slam";
        //Columns
        public static String SLAMID = "slam_id";
        public static String COMPLETE_NAME = "slam_complete_name";
        public static String NICKNAME = "slam_nickname";
        public static String BIRTHDAY = "slam_birthday";
        public static String BDAYWISH = "slam_bdaywish";
        public static String COLOR = "slam_color";
        public static String FOOD = "slam_food";
        public static String MUSIC = "slam_music";
        public static String USERMSG = "slam_msg";
        public static String DATE = "answer_date";
        public static String LOGGEDIN = "answer_loggedin"; //kung kaninong acc naka log in
    }

    public static class User implements BaseColumns {
        //Table
        public static String USER_TABLE = "table_user";
        //Columns
        public static String ID = "user_id";
        public static String NAME = "user_name";
        public static String USERNAME = "user_username";
        public static String PASSWORD = "user_password";

    }

}
