package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class AttendanceRecordModel {
    private  String strDate;
    private String status;

    public AttendanceRecordModel()
    {

    }
    public AttendanceRecordModel(String status,String strDate)
    {
        this.status=status;
        this.strDate=strDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
