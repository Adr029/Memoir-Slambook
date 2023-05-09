package com.example.slambook;

public class model {
    public model (String slam_id, String slam_author, String slam_date)
    {
        this.slam_id = slam_id;
        this.slam_author = slam_author;
        this.slam_date = slam_date;
    }
    String slam_id, slam_author, slam_date;

    public String getSlam_id() {
        return slam_id;
    }

    public void setSlam_id(String slam_id) {
        this.slam_id = slam_id;
    }

    public String getSlam_author() {
        return slam_author;
    }

    public void setSlam_author(String slam_author) {
        this.slam_author = slam_author;
    }

    public String getSlam_date() {
        return slam_date;
    }

    public void setSlam_date(String slam_date) {
        this.slam_date = slam_date;
    }


}