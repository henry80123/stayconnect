package com.studentproject.stayconnect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;


public class LoadScreenActivity extends Activity {

    private static final String tag = "LoadScreen";
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);
        ImageView image = (ImageView)findViewById(R.id.startscreen);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_page);
        image.startAnimation(animation);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        //spinner.setVisibility(View.GONE);
        Log.d(tag, "LoadScreen: Inside the Load Screen");
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(5000); //Delay of 10 seconds
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
