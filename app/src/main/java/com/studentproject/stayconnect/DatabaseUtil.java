package com.studentproject.stayconnect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseUtil extends SQLiteOpenHelper

{

    final String tag="db";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appstorage.db";


    //------------------------------ ACRONYM TABLE --------------------------------------

    public static final String acronym_table = "acronym_tbl";
    public static final String acronym = "acronym";
    public static final String full_form = "fullform";
    public static final String dept = "dept";

    private static final String create_acronym = "create table " + acronym_table + "(" + acronym + " varchar primary key, " +
            full_form + " text not null, " + dept + " text); ";

    //------------------------------------------------------------------------------------


    public DatabaseUtil(Context context)
    {
        // Create Database itself
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(tag,"CreateDatabase: Creating the database - constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        //database.execSQL(DATABASE_CREATE);
        Log.d(tag,"CreateDatabase: Inside onCreate() ");
        database.execSQL(create_acronym);
        Log.d(tag,"CreateDatabase: Creating the Table Acronym \n SQL: "+create_acronym);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DatabaseUtil.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        Log.d(tag,"CreateDatabase: Starting to destroy data ");

        db.execSQL("DROP TABLE IF EXISTS " + create_acronym);
        Log.d(tag,"CreateDatabase: Finished to destroy data ");

        onCreate(db);
        Log.d(tag,"CreateDatabase: Finished to calling ONCREATE");
    }

}
