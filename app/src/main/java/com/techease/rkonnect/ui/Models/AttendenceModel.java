package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class AttendenceModel {

    private String attendence;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String Date;



    public AttendenceModel()
    {

    }
    public AttendenceModel(String b,String c)

    {
        this.attendence=b;
        this.Date=c;
    }


    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }




}
