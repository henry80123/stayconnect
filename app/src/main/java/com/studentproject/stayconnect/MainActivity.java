package com.studentproject.stayconnect;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
///
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {


    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String PROPERTY_REG_ID = "integrated-bit-858";
    public static final String SENDER_ID = "419236527908";

    private GoogleCloudMessaging gcm;
    private String regid;
    final String gcmLog= "GCM";

    private AlertDialog.Builder alertBuilder;
    private AlertDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_menu_layout);

        alertBuilder = new AlertDialog.Builder(this);


        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId();

            if (regid.length() == 0) {
                registerInBackground();
                Toast.makeText(MainActivity.this, "Not Yet registered", Toast.LENGTH_SHORT).show();
                Log.d(gcmLog, "Not yet Registered, Registering....");
            } else {
                Toast.makeText(MainActivity.this, "Already registered", Toast.LENGTH_SHORT).show();
                Log.d(gcmLog, "Already Registered ");
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuItem item = menu.add(Menu.NONE,R.id.logout,1,R.string.action_logout_btn);
        item.setIcon(R.drawable.exit);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                toLogOutDialog();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //check if it supports GooglePlayServices
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

    //get regID
    private String getRegistrationId() {
        final SharedPreferences prefs = getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }
        return registrationId;
    }

    //register to get regID
    private void registerInBackground() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
                    }
                    regid = gcm.register(SENDER_ID);
                    //sendRegisterId(regid);
                    storeRegistrationId(regid);
                    Log.d(gcmLog, "RegID = "+regid);
                    Log.d(gcmLog, "Successful registration");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return null;
            }

        }.execute(null, null, null);
    }

    //store regID
    private void storeRegistrationId(String regId)
    {
        SharedPreferences prefs = getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.commit();
    }

    //send to http for easy check regID
    /*private void sendRegisterId(String regId)
    {
        try
        {
            URL url = new URL("http://emlscer.no-ip.info:8080/Sample/AppList.php?add=" + regId);
            HttpURLConnection Conn = (HttpURLConnection) url.openConnection();
            Conn.connect();
            Conn.getResponseCode();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    public void toLogOutDialog(){
        alertBuilder.setMessage(R.string.logout_alert_msg);
        alertBuilder.setPositiveButton(R.string.logout_alert_btn1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface arg0, int which) {
                Log.d("dialog", "click yes");
                Intent i = new Intent(getApplicationContext(),LoadScreenActivity.class);
                startActivity(i);
            }
        });
        alertBuilder.setNegativeButton(R.string.logout_alert_btn2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int which) {
                Log.d("dialog", "click no");
                dialog.cancel();
            }
        });
        //For login fail and try again
        dialog = alertBuilder.show();
        TextView messageText = (TextView)dialog.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        messageText.setTextColor(getResources().getColor(R.color.background_material_dark));
        dialog.show();

    }

    public void toTestDatabaseActivity(View v){
        Intent i = new Intent(getApplicationContext(),TestDatabaseActivity.class);
        startActivity(i);
    }

    public void toDisplayAcronymActivity(View v){
        Intent i = new Intent(getApplicationContext(), DisplayAcronymActivity.class);
        startActivity(i);
    }

    public void toDoctorListActivity(View v){
        Intent i = new Intent(getApplicationContext(), DoctorListActivity.class);
        startActivity(i);
    }

    public void toDisplayNotification(View v){
        Intent i = new Intent(getApplicationContext(), DisplayNotificationActivity.class);
        startActivity(i);
    }





}

