/*package com.studentproject.stayconnect;

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
        dbHelper = new DatabaseUtil(context);
        Log.d(tag, "DataConnection: Inside Constructor");
    }

    public void open() throws SQLException
    {
        // Assign the DatabaseUtil to the SQL Object to retrieve the data
        database = dbHelper.getWritableDatabase();
        Log.d(tag, "DataConnection: Opening the Database");
    }

    public void close()  // Close the connection to the database
    {
        dbHelper.close();
        Log.d(tag, "DataConnection: Closing Connection");
    }


    //--------------------  Return full Acronym List in alphabetical order ------------------

    public List<Acronym> getAllAcronym(String pid)
    {
        Log.d(tag, "getAllAcronym: Inside getCommentsSpecific() " );

        List<Acronym> AcronymList = new ArrayList<Acronym>();

        Log.d(tag, "getAllAcronym: Going to run query on database " );

        Cursor cursor = database.query(DatabaseUtil.acronym_table,  Acronym_Col,  null, null, null, null, null);

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

        return AcronymList;
    }




    //--------------------  Add Acronym to database  ------------------
    public void setLike(String pid, String like )
    {

        ContentValues values = new ContentValues();

        // First assign the values

        values.put(DatabaseUtil.COLUMN_RIL_PNAME, pid);
        values.put(DatabaseUtil.COLUMN_RIL_LIKE, like);


        Log.d(tag, "s| setLike()  PID:" + pid + "  Like " + like );

        Cursor cursor = database.query
                (DatabaseUtil.TABLE_RECEIVED_LIKE,  allColumnsRCV_LIKE,
                        DatabaseUtil.COLUMN_RIL_PNAME + " = " + pid, null, null, null, null);

        if(cursor.getCount() > 0)
        {

            database.update(DatabaseUtil.TABLE_RECEIVED_LIKE, values,
                    DatabaseUtil.COLUMN_RIL_PNAME + " =  "+ pid , null);
            Log.d(tag, "s| setLike()   : FINISHED UPDATING THE ROW TO NEW LIKE VALUE" );

        }
        else
        {
            Log.d(tag, "CommentsDataSource| setLike()   : VALUES DONT EXIST - ADDING tO DB" );
            long insertId = database.insert(DatabaseUtil.TABLE_RECEIVED_LIKE, null, values);
        }


    }

    //SET THE LIKE VALUE FOR A SPECIFIC PICTURE USER SENT ( LIKE CAN HAVE VALUE LIKE OR DISLIKE)
    public void RegisterLike(String pid, String uid, String like )
    {

        ContentValues values = new ContentValues();

        // First assign the values

        values.put(DatabaseUtil.COLUMN_R_L_PID, pid);
        values.put(DatabaseUtil.COLUMN_R_L_LIKE, like);
        values.put(DatabaseUtil.COLUMN_R_L_UID, uid);

        Log.d(tag, "CommentsDataSource| RegisterLike()  PID:" + pid + " UserID: " + uid +" Like " + like );
        Cursor cursor = null ;

        System.out.println("BEFORE QUERY");
        cursor = database.query(DatabaseUtil.TABLE_RESPONSE_LIKE,  allColumnsRESPONSE_LIKE, "img_name = '"+pid + "' AND "+ DatabaseUtil.COLUMN_R_L_UID + " =  '"+ uid +"'", null, null, null, null);
        System.out.println("AFTER QUERY");

        if(cursor.getCount() > 0)
        {

            database.update(DatabaseUtil.TABLE_RESPONSE_LIKE, values, DatabaseUtil.COLUMN_R_L_PID + " =  '"+pid + "' AND "+ DatabaseUtil.COLUMN_R_L_UID + " =  '"+ uid +"'", null);
            Log.d(tag, "CommentsDataSource| setLike()   : FINISHED UPDATING THE ROW TO NEW LIKE VALUE" );


        }else
        {

            System.out.println("NOT FOUND");
            Log.d(tag, "CommentsDataSource| setLike()   : VALUES DONT EXIST - ADDING tO DB" );
            long insertId = database.insert(DatabaseUtil.TABLE_RESPONSE_LIKE, null, values);
        }



    }


    // RETURN NUMBER OF LIKES FOR A PICTURE USER SENT
    public int getNumLikes(String pid)
    {

        Cursor cursor = database.query  ( DatabaseUtil.TABLE_RESPONSE_LIKE,  allColumnsRESPONSE_LIKE,
                DatabaseUtil.COLUMN_R_L_LIKE + " = 'like'" + " AND " +
                        DatabaseUtil.COLUMN_R_L_PID + " = '" + pid +"'", null, null, null, null);

        if(cursor.getCount()> 0)
        {
            return  cursor.getCount();
        }
        else
        {

            return 0;
        }



    }
    public int getNumDislikes(String pid)
    {

        Cursor cursor = database.query  ( DatabaseUtil.TABLE_RESPONSE_LIKE,  allColumnsRESPONSE_LIKE,
                DatabaseUtil.COLUMN_R_L_LIKE + " = 'dislike'" + " AND " +
                        DatabaseUtil.COLUMN_R_L_PID + " = '" + pid +"'", null, null, null, null);
        if(cursor.getCount()> 0)
        {
            return  cursor.getCount();
        }
        else
        {

            return 0;
        }




    }

    //ADD A CONTACT TO THE DATABASE
    public Contacts createContact(String phone_num, String name)
    {

        if(phone_num.charAt(0) == '+')
        {
            phone_num = phone_num.substring(1);
        }
        ContentValues values = new ContentValues();
        values.put(DatabaseUtil.COLUMN_UID, phone_num);
        values.put(DatabaseUtil.COLUMN_UNAME, name);

        Log.d(tag, "CommentsDataSource| CONTACTS: CONTACT  UID: " + phone_num + " Name: " + name  );

        //Insert the Contact
        long insertId = database.insert(DatabaseUtil.TABLE_FRIENDS, null, values);

        Contacts con = new Contacts();
        con.setName(name);
        con.setNumber(phone_num);

        return con;

    }


    public boolean ContactExists(String phone_num)
    {

        Log.d(tag, "CommentsDataSource| CONTACTS: Checking if number : " + phone_num  + " exists"  );

        //Insert the Contact
        Cursor cursor = database.query(DatabaseUtil.TABLE_FRIENDS,  allColumnsCONTACTS, "u_id = "+phone_num, null, null, null, null);

        if(cursor.getCount() <= 0)
        {
            Log.d(tag, "CommentsDataSource| CONTACTS: number : " + phone_num  + " does not exists"  );

            return false;

        }
        else return true;



    }

}


*/