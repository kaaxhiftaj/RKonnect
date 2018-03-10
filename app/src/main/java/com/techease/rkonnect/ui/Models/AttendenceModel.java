package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class AttendenceModel {

    private String attendence;
    private String strDate;


    public AttendenceModel()
    {

    }
    public AttendenceModel(String b,String c)

    {
        this.attendence=b;
        this.strDate=c;
    }


    public String getDate() {
        return strDate;
    }

    public void setDate(String strDate)
    {
        strDate = strDate;
    }






    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }




}
