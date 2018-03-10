package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 09-Mar-18.
 */

public class HistoryModel {

    private String name;
    private String rollNo;
    private String age;
    private String fatherName;
   // private String attendence;

    public HistoryModel()
    {

    }
    public HistoryModel(String name,String rollNo,String age, String fatherName)
    {
        this.name = name;
        this.rollNo = rollNo;
        this.age= age;
        this.fatherName=fatherName;
    //    this.attendence = attendence;

    }


    public String getFatherName() {
        return fatherName;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getAttendence() {
//        return attendence;
//    }
//
//    public void setAttendence(String attendence) {
//        this.attendence = attendence;
//    }
}
