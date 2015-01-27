package com.studentproject.stayconnect;

/**
 * Created by Koushal on 22/01/2015.
 */

public class Acronym {

    private String acronym;


    private String full_form;
    private String dept;

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setFull_form(String full_form) {
        this.full_form = full_form;
    }

    public String getAcronym()
    {
        return acronym;
    }

    public String getfullform()
    {
        return full_form;
    }

    public String getDept()
    {
        return dept;
    }

    public Acronym()
    {
        this.acronym = null;
        this.dept = null;
        this.full_form = null;
    }

    @Override
    public String toString() {
        return ("Acronym: "+ this.acronym + " Full Form: "+ this.full_form);
    }
}


