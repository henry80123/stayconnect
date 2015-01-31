package com.studentproject.stayconnect;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;

        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Comparator;
        import java.util.List;


public class DataConnection {

    private SQLiteDatabase database;
    private DatabaseUtil   dbHelper;
    final String tag="db";

    // Column names for Database
    private String[] Acronym_Col = { DatabaseUtil.acronym, DatabaseUtil.full_form, DatabaseUtil.dept };

    public DataConnection(Context context)
    {
        Log.d(tag, "DataConnection: Inside Constructor");
        dbHelper = new DatabaseUtil(context);

    }

    public void open() throws SQLException
    {
        // Assign the DatabaseUtil to the SQL Object to retrieve the data
        database = dbHelper.getWritableDatabase();
        Log.d(tag, "DataConnection: Opening the Database");
    }

    public void close()
    {
        dbHelper.close();
        Log.d(tag, "DataConnection: Closing Connection");
    }

    //--------------------  Return full Acronym List in alphabetical order ------------------
    public List<Acronym> getAllAcronym()
    {
        Log.d(tag, "Inside getAllAcronym()" );

        List<Acronym> AcronymList = new ArrayList<Acronym>();

        Log.d(tag, "getAllAcronym: Going to run query on database " );
        Cursor cursor = database.query(DatabaseUtil.acronym_table,  Acronym_Col,  null, null, null, null, null);
        Log.d(tag, "getAllAcronym: Number of acronyms returned: "+cursor.getCount() );

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Acronym acro = new Acronym();
            acro.setAcronym(cursor.getString(0));
            acro.setFull_form(cursor.getString(1));
            acro.setDept(cursor.getString(2));
            AcronymList.add(acro);
            cursor.moveToNext();
        }
        cursor.close();

        Collections.sort(AcronymList , new Comparator<Acronym>() {
            public int compare(Acronym a1, Acronym a2) {
                return a1.getAcronym().compareTo(a2.getAcronym());
            }
        });
        Log.d(tag, "Number of acronyms found: "+ AcronymList.size() );
        return AcronymList;
    }

    //--------------------  Add Acronym to database  --------------------
    //----- Returns true if successful else return false ----------------
    public Acronym registerAcronym(String acronym, String fullform,  String dept1 )
    {


        ContentValues values = new ContentValues();
        values.put(DatabaseUtil.acronym,acronym.trim());
        values.put(DatabaseUtil.full_form, fullform.trim());
        values.put(DatabaseUtil.dept, dept1.trim());

        Log.d(tag, "registerAcronym()  acronym:" + acronym + "  fullform: " + fullform + "  dept: " + dept1 );
        Cursor cursor = database.query(DatabaseUtil.acronym_table,  Acronym_Col, DatabaseUtil.acronym + " = '" +acronym.trim()+"'" , null, null, null, null);
        Log.d(tag, "registerAcronym() condition :" + DatabaseUtil.acronym + " = " + "'acronym.trim()'");
        Log.d(tag, "registerAcronym() Number of acronyms exsisting :" + cursor.getCount()+"");

        if(cursor.getCount() <= 0)
        {
            long insertId = database.insert(DatabaseUtil.acronym_table, null, values);
            Log.d(tag, "registerAcronym() : Finished updating acronym to database" );
            return (new Acronym(acronym,fullform,dept1));
        }
        else
        {
            Log.d(tag, "registerAcronym() : Acronym already exists" );
            return null;
        }
    }


}

