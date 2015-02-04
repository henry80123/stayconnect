package com.studentproject.stayconnect;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toTestDatabaseActivity(View v){
        Intent i = new Intent(getApplicationContext(),TestDatabaseActivity.class);
        startActivity(i);
    }

    public void toDisplayAcronymActivity(View v){
        Intent i = new Intent(getApplicationContext(), DisplayAcronymActivity.class);
        startActivity(i);
    }


}

