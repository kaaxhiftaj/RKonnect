package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class StudentModel {
    private String Name;
    private String FatherName;
    private String RollNo;
    private String Age;
    private String CNIC;
    public String imageName;
    public String imageURL;


    public StudentModel()
    {

    }
    public StudentModel(String name,String fatherName,String rollNo,String age,String cnic)
    {
        this.Age=age;
        this.FatherName=fatherName;
        this.RollNo=rollNo;
        this.Name=name;
        this.CNIC=cnic;
    }
    public StudentModel(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
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
