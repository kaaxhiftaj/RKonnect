package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class HistoryModel {

    private String StudentName;
    private String Roll_No;
    private String class_Name;
    private String Attendance_Status;
    public HistoryModel()
    {

    }
    public HistoryModel(String name,String roll,String class_no,String status)
    {
        this.StudentName=name;
        this.Roll_No=roll;
        this.class_Name=class_no;
        this.Attendance_Status=status;
    }
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }
    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }
    public String getRoll_No() {
        return Roll_No;
    }

    public void setRoll_No(String roll_No) {
        Roll_No = roll_No;
    }

    public String getStatus() {
        return Attendance_Status;
    }

    public void setStatus(String status) {
        Attendance_Status = status;
    }


}
