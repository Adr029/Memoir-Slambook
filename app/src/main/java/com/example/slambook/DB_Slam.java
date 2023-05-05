package com.example.slambook;

import android.provider.BaseColumns;

public class DB_Slam {
//from modules
    public static class User implements BaseColumns {
        //Table
        public static String USER_TABLE = "table_user";
        //Columns
        public static String ID = "user_id";
        public static String COMPLETE_NAME = "user_complete_name";
        public static String NICKNAME = "user_nickname";
        public static String BIRTHDAY = "user_birthday";
        public static String BDAYWISH = "user_bdaywish";
        public static String COLOR = "user_color";
        public static String FOOD = "user_food";
        public static String MUSIC= "user_music";
    }

    public static class Post implements BaseColumns {
        //Table
        public static String POST_TABLE = "table_post";
        //Columns
        public static String ID = "post_id";
        public static String USERNAME= "user_username";
        public static String TEXT = "post_text";
        public static String DATE = "post_date";
    }

}
