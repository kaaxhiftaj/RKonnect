package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class StudentModel {
    private String Name,FatherName,RollNo,Age;

    public StudentModel()
    {

    }
    public StudentModel(String name,String fatherName,String rollNo,String age)
    {
        this.Age=age;
        this.FatherName=fatherName;
        this.RollNo=rollNo;
        this.Name=name;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String rollNo) {
        RollNo = rollNo;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }




}
