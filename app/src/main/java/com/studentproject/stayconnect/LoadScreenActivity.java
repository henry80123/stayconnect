package com.studentproject.stayconnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;


public class LoadScreenActivity extends ActionBarActivity {

    private static final String tag = "LoadScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        Log.d(tag, "LoadScreen: Inside the Load Screen");
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(3000); //Delay of 10 seconds
                    /*
                        If internet connection (Verify Model number)





                     */
                } catch (Exception e)
                {

                } finally {

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(i);
                    finish();
                }
            }
        };
        welcomeThread.start();



    }


}
