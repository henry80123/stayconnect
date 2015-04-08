package com.studentproject.stayconnect;

public class Acronym {

    private int Koushal;
    private String acronym;
    private String full_form;
    private String dept;

    public Acronym(String acronym, String full_form, String dept) {
        this.acronym = acronym;
        this.dept = dept;
        this.full_form = full_form;
    }


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
    //public String toString() {return ("Acronym: "+ this.acronym );
    //edited
    public String toString() {
        return (this.acronym );

    }

    public static boolean Validate(Acronym temp)
    {
        boolean allcharacters_acronym = temp.getAcronym().toLowerCase().trim().matches("[a-zA-Z]+");
        boolean allcharacters_fullform = temp.getfullform().trim().matches("[a-zA-Z]+");
        boolean allcharacters_dept = temp.getAcronym().trim().matches("[a-zA-Z]+");
        boolean nonullval_acronym = false;
        boolean nonullval_fullform = false;

        if( (temp.getAcronym().trim()=="") || (temp.getAcronym().trim()==null) )
        {
            nonullval_acronym=true;
        }

        if( (temp.getfullform().trim()=="") || (temp.getfullform().trim()==null) )
        {
            nonullval_fullform=true;
        }

        if(!allcharacters_acronym && !allcharacters_fullform && !allcharacters_dept && nonullval_acronym && nonullval_fullform )
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}

