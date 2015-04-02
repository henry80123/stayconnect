package com.studentproject.stayconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;



public class LoadScreenActivity extends Activity {

    private static final String tag = "LoadScreen";
    private ProgressBar spinner;
    private Button logIn;
    private EditText username;
    private EditText pw;
    private TextView appname;

    private AlertDialog.Builder alertBuilder;  //For success
    private AlertDialog.Builder alertBuilder2; //For fail
    private AlertDialog.Builder alertBuilder3; //For fail to how to register
    private AlertDialog dialog2;//For fail
    private AlertDialog dialog3;//For fail to how to register

    private Animation anim_appname;
    //private Animation anim_username;
    //private Animation anim_pw;
    //private Animation anim_login_btn;
    //private AnimationSet animSet;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.launch_login_layou);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            //
            username = (EditText) findViewById(R.id.username_edit);
            pw = (EditText) findViewById(R.id.pw_edit);
            logIn = (Button) findViewById(R.id.login_btn);
            appname = (TextView)findViewById(R.id.startscreen_text);

            username.setVisibility(View.GONE);
            pw.setVisibility(View.GONE);
            logIn.setVisibility(View.GONE);

            alertBuilder = new AlertDialog.Builder(this);
            alertBuilder2 = new AlertDialog.Builder(this);
            alertBuilder3 = new AlertDialog.Builder(this);

            anim_appname = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_username = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_pw = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_login_btn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);

            //animSet = new AnimationSet(true);


        //spinner = (ProgressBar)findViewById(R.id.progressBar1);
            //spinner.setVisibility(View.GONE);

            final Thread welcomeThread = new Thread() {

                @Override
                public void run() {
                    try {
                        super.run();
                        sleep(3000); //Delay of 10 seconds
                            //If internet connection (Verify Model number)

                    } catch (InterruptedException e)
                    {
                        Log.d("thread", "is interrupted!" + e.toString());

                    }finally{

                        //run the UI within a child thread.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                appname.startAnimation(anim_appname);
                                anim_appname.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {
                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        appname.setVisibility(View.GONE);
                                        username.setVisibility(View.VISIBLE);
                                        pw.setVisibility(View.VISIBLE);
                                        logIn.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {
                                    }
                                });



                            }
                        });
                    }
                }
            };
            welcomeThread.start();
    }

    //for showing the alertdialog when user click the "Log In" button.
    public void toLoginDialog(View v){
        if(username.getText().toString().equals("admin") && pw.getText().toString().equals("admin")){
            alertBuilder.setMessage(R.string.login_success_alert_msg);
            alertBuilder.setNeutralButton(R.string.login_success_alert_btn, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });
            //For login success
            AlertDialog dialog = alertBuilder.show();
            TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
            messageText.setGravity(Gravity.CENTER);
            messageText.setTextColor(getResources().getColor(R.color.background_material_dark));
            dialog.show();

        }else{

            alertBuilder2.setMessage(R.string.login_fail_alert_msg);
            alertBuilder2.setPositiveButton(R.string.login_fail_alert_btn2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(final DialogInterface arg0, int which) {
                    dialog2.dismiss();
                    alertBuilder3.setMessage(R.string.login_reg_msg);
                    alertBuilder3.setNeutralButton(R.string.login_reg_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            dialog2.dismiss();
                        }
                    });
                    //For login fail and show how to register dialog
                    dialog3 = alertBuilder3.show();
                    TextView messageText = (TextView) dialog3.findViewById(android.R.id.message);
                    messageText.setGravity(Gravity.CENTER);
                    messageText.setTextColor(getResources().getColor(R.color.background_material_dark));
                    dialog3.show();

                }
            });
            alertBuilder2.setNegativeButton(R.string.login_fail_alert_btn1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int which) {
                    dialog2.dismiss();
                }
            });
            //For login fail and try again
            dialog2 = alertBuilder2.show();
            TextView messageText = (TextView)dialog2.findViewById(android.R.id.message);
            messageText.setGravity(Gravity.CENTER);
            messageText.setTextColor(getResources().getColor(R.color.background_material_dark));
            dialog2.show();

        }

    }

}
