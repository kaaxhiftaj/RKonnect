package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class HistoryModel {

    private String StudentName;
    private String Roll_No;
    private String class_Name;
   // private String attendence;
    public HistoryModel()
    {

    }
    public HistoryModel(String name,String roll,String class_no)
    {
        this.StudentName=name;
        this.Roll_No=roll;
        this.class_Name=class_no;
       // this.attendence=status;
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

//    public String getStatus() {
//        return attendence;
//    }
//
//    public void setStatus(String status) {
//        attendence = status;
//    }


}
