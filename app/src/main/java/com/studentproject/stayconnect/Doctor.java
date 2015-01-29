package com.studentproject.stayconnect;

/**
 * Created by HenryChiang on 15-01-29.
 */
public class Doctor {

    private String docName;
    private String docAddress;
    private String docTelephone;
    private String docHours;


    public Doctor(String docName, String docAddress, String docTelephone, String docHours) {
        this.docName = docName;
        this.docAddress = docAddress;
        this.docTelephone = docTelephone;
        this.docHours = docHours;

    };

    public Doctor(){
            this.docName = null;
            this.docAddress = null;
            this.docTelephone = null;
            this.docHours = null;
        }


    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocAddress(String docAddress) {
        this.docAddress = docAddress;

    }

    public void setDocTelephone(String docTelephone) {
        this.docTelephone = docTelephone;

    }

    public void setDocHours(String docHours) {
        this.docHours = docHours;

    }

    public String getDocName() {
        return docName;
    }

    public String getDocAddress() {
        return docAddress;
    }

    public String getDocTelephone() {
        return docTelephone;
    }

    public String getDocHours() {
        return docHours;
    }
}

