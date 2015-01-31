package com.studentproject.stayconnect;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.TextView;

/**
 * Created by Koushal on 28/01/2015.
 */
public class SingleAcronymActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.single_acro_layout);

        TextView acronym = (TextView) findViewById(R.id.AcronymValue);
        TextView fullform = (TextView) findViewById(R.id.FFValue);
        TextView dept = (TextView) findViewById(R.id.DeptValue);


        Intent i = getIntent();
        // getting attached intent data
        String acrostr = i.getStringExtra("acronym");
        // displaying selected product name
        acronym.setText(acrostr);
        fullform.setText("full-form");
        dept.setText("Test");



    }
}
