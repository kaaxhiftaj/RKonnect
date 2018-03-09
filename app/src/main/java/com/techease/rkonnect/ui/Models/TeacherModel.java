package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class TeacherModel {

    private  String Email,InstituteName,Uid;

    public TeacherModel()
    {

    }


    public TeacherModel(String Email,String Institute,String Uid)
    {
        this.Email=Email;
        this.InstituteName=Institute;
        this.Uid=Uid;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }






}
