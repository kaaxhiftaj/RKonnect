package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 12-Mar-18.
 */

public class ParentHomeModel {

    String name,rollNo,fatherName,status,strDate,classTitle,instituteName;

    public void ParentModel()
    {

    }

    public void ParentModel(String Name,String RollNo,String Status,String StrDate,String ClassTitle,String InstituteName)
    {
     this.name=Name;
     this.rollNo=RollNo;
     this.status=Status;
     this.strDate=StrDate;
     this.classTitle=ClassTitle;
     this.instituteName=InstituteName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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

    public String getClassTitle() {
        return classTitle;
    }

    public void setClassTitle(String classTitle) {
        this.classTitle = classTitle;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }


}
