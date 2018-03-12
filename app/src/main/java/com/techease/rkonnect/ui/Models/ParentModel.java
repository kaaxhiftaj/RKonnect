package com.techease.rkonnect.ui.Models;

/**
 * Created by Adamnoor on 11-Mar-18.
 */

public class ParentModel {

    private String Name;
    private String CNIC;
    private String Email;

    public ParentModel()
    {

    }
    public  ParentModel(String name,String email,String cnic)
    {
        this.Name=name;
        this.Email=email;
        this.CNIC=cnic;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
