package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class AttendanceRecordModel {
    private  String date;
    private String attendence;

    public AttendanceRecordModel()
    {

    }
    public AttendanceRecordModel(String attendence,String date)
    {
        this.attendence=attendence;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }


}
