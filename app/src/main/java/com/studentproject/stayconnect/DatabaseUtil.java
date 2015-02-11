package com.studentproject.stayconnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseUtil extends SQLiteOpenHelper

{

    final String tag="db";
    private static final int DATABASE_VERSION = 17;
    private static final String DATABASE_NAME = "appStorage.db";


    //------------------------------ ACRONYM TABLE --------------------------------------

    public static final String acronym_table = "acronym_table";

    //ACRONYM TABLE COLUMNS names
    public static final String acronym = "acronym";
    public static final String full_form = "full_form";
    public static final String dept = "dept";

    private static final String create_acronym = "create table " + acronym_table + "(" + acronym + " varchar primary key," +
            full_form + " text not null," + dept + " text" + ")";

    //------------------------------------------------------------------------------------

    //------------------------------ DOCTOR TABLE --------------------------------------

    public static final String doctor_table = "doctor_table";

    //DOCTOR TABLE COLUMNS names
    /*public static final String docName = "docName";
    public static final String docAddress = "docAddress";
    public static final String docTelephone = "docTelephone";
    public static final String docHours = "docHours";
    public static final String

    private static final String create_doctor_table = "create table " + doctor_table + "(" + docName + " varchar primary key," +
            docAddress + " text not null," + docTelephone + " integer not null," + docHours + " varchar" + ")";*/

    //------------------------------------------------------------------------------------


    public DatabaseUtil(Context context)
    {
        // Create Database itself
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(tag,"DatabaseUtil: Creating the database - constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        Log.d(tag,"DatabaseUtil: Inside onCreate() ");
        database.execSQL(create_acronym);
        Log.d(tag,"DatabaseUtil: Creating the Table Acronym \n SQL:" + create_acronym);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.v(DatabaseUtil.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        Log.d(tag,"DatabaseUtil: Starting to destroy data ");

        db.execSQL("DROP TABLE IF EXISTS " + acronym_table);
        db.execSQL("DROP TABLE IF EXISTS " + doctor_table);
        Log.d(tag,"DatabaseUtil: Finished to destroy data ");


        onCreate(db);
        Log.d(tag,"DatabaseUtil: Finished to calling onCreate");
    }

}