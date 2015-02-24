package com.studentproject.stayconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Paul on 11/2/15.
 */
public class DoctorListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorlist_layout);
    }

    public void toDisplayDoctorListOnButtonPress(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.one:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.two:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.three:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.four:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.five:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("which_one", v.getId());
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.six:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
            case R.id.seven:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://fzs.sve-mo.ba/sites/default/files/dokumenti-vijesti/sample.pdf");
                startActivity(i);
                break;
            case R.id.eight:
                i = new Intent(getApplicationContext(), DisplayDoctorListActivity.class);
                i.putExtra("url","http://docs.google.com/gview?embedded=true&url=http://www.stagecoachbus.com/PdfUploads/Timetable_28768_5.pdf");
                startActivity(i);
                break;
        }
    }



}




