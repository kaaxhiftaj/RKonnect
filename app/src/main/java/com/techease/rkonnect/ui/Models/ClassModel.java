package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 08-Mar-18.
 */

public class ClassModel {
    private String ClassTitle;
    private String InstituteName;

    public ClassModel()
    {

    }
    public ClassModel(String title,String name)
    {
        this.ClassTitle=title;
        this.InstituteName=name;
    }
    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String classTitle) {
        ClassTitle = classTitle;
    }

    public String getInstituteName() {
        return InstituteName;
    }

    public void setInstituteName(String instituteName) {
        InstituteName = instituteName;
    }



}
