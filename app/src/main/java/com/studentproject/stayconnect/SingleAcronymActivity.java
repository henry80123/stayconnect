package com.studentproject.stayconnect;

        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.widget.TextView;

/**
 * Created by Koushal on 28/01/2015.
 */
public class SingleAcronymActivity extends Activity {


    private SQLiteDatabase database;
    private DatabaseUtil   dbHelper;
    private String[] Acronym_Col = { DatabaseUtil.acronym, DatabaseUtil.full_form, DatabaseUtil.dept };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_acro_layout);

        TextView acronym = (TextView) findViewById(R.id.AcronymValue);
        TextView fullform = (TextView) findViewById(R.id.FFValue);
        TextView dept = (TextView) findViewById(R.id.DeptValue);

        //get the passed data
        Intent i = getIntent();
        String acrostr = i.getStringExtra("acronym");
        //open android database
        dbHelper = new DatabaseUtil(this);
        database = dbHelper.getWritableDatabase();
        //search the database with the selected acronym and set the fullForm and dept.
        Cursor cursor = database.query(DatabaseUtil.acronym_table,  Acronym_Col, DatabaseUtil.acronym + " = '" +acrostr+ "'" , null, null, null, null);
        cursor.moveToFirst();
        String fullFormStr=cursor.getString(1);
        String deptStr = cursor.getString(2);

        // displaying selected product name
        acronym.setText(acrostr);
        fullform.setText(fullFormStr);
        dept.setText(deptStr);



    }
}
