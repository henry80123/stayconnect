package com.studentproject.stayconnect;

/**
 * Created by HenryChiang on 15-02-16.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class GcmIntentService extends IntentService {
    public static final int NOTIFICATION_ID = 1;
    public GcmIntentService() {
        super("GcmIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (!extras.isEmpty()) {
            try {
                //parse jsons message
                JSONTokener jsonTokener = new JSONTokener(extras.toString().replaceAll("Bundle", ""));
                JSONArray jsons = (JSONArray) jsonTokener.nextValue();
                JSONObject jsonObject = (JSONObject)jsons.get(0);
                //get message from json

                sendNotification(jsonObject.getString("message"), jsonObject.getString("title"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg, String title) {
        //the Activity directed to when clicking on the notification.
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        //
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                .setSummaryText("StayConnect")
                .bigText(msg)
                .setBigContentTitle(title);

        //Set Up New Notification
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.puzzle_logo2)
        .setTicker("New message")
        .setContentTitle(title)
        .setContentText(msg)
        .setAutoCancel(true)
        .setDefaults(Notification.DEFAULT_ALL)
        .setContentIntent(contentIntent)
        .setStyle(bigTextStyle);

        //mBuilder.build();

        //Viewing
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, nBuilder.build());
    }

}
