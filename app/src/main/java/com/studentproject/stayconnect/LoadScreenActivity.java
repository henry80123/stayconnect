package com.studentproject.stayconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class LoadScreenActivity extends Activity {

    private static final String tag = "LoadScreen";
    private EditText username;
    private EditText pw;
    private TextView appname;
    private Button loginBtn;
    private ImageView logo;
    private AlertDialog.Builder alertBuilder;  //For successfully
    private AlertDialog.Builder alertBuilder2; //For fail
    private AlertDialog.Builder alertBuilder3; //For fail and how to register
    private AlertDialog dialog2;//For fail
    private AlertDialog dialog3;//For fail and how to register
    private int marginTop;

    private boolean isPressed =false;
    private int i=1;


    private RelativeLayout.LayoutParams lp;

    private Animation anim_appname;
    //private Animation anim_username;
    //private Animation anim_pw;
    //private Animation anim_login_btn;
    //private AnimationSet animSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.launch_login_layout);


            username = (EditText) findViewById(R.id.username_edit);
            pw = (EditText) findViewById(R.id.pw_edit);
            loginBtn = (Button) findViewById(R.id.login_btn);
            appname = (TextView)findViewById(R.id.startscreen_text);

            username.setVisibility(View.GONE);
            pw.setVisibility(View.GONE);
            loginBtn.setVisibility(View.GONE);

            alertBuilder = new AlertDialog.Builder(this);
            alertBuilder2 = new AlertDialog.Builder(this);
            alertBuilder3 = new AlertDialog.Builder(this);

            anim_appname = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_username = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_pw = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);
            //anim_login_btn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.launch_appname);

            //move the screen up when keyboard pops up.
            lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            logo = (ImageView)findViewById(R.id.startscreen_image);
            marginTop = getResources().getDimensionPixelSize(R.dimen.margin_top);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL,logo.getId());



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
                                        loginBtn.setVisibility(View.VISIBLE);
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
            buttonEffect(loginBtn);
            imageEffect();
            moveScreenUp();


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
                    finish();
                }
            });
            //For login successfully
            AlertDialog dialog = alertBuilder.show();

            //Disable the dismiss ability for dialog for login successfully.
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
            //

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

    public void moveScreenUp(){
        final View activityRootView = findViewById(R.id.activityRoot);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - r.bottom;
                if (heightDiff > 100) {
                    lp.setMargins(0, 0, 0, 0);
                    logo.setLayoutParams(lp);
                } else {
                    lp.setMargins(0, marginTop, 0, 0);
                    logo.setLayoutParams(lp);

                }
            }
        });

    }

    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(0xe02ca491, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                }
                return false;
            }
        });
    }

    public void imageEffect(){
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isPressed &&i==1){
                    logo.setImageResource(R.drawable.puzzle_logo2);
                    i++;
                }else if(i==2){
                    logo.setImageResource(R.drawable.puzzle_logo3);
                    i++;
                }else if(i==3){
                    logo.setImageResource(R.drawable.puzzle_logo4);
                    i++;
                }else if(i==4){
                    logo.setImageResource(R.drawable.puzzle_logo5);
                    i=1;
                }
                isPressed =!isPressed;

            }

        });

    }

}
