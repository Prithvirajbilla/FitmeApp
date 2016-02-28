package in.fitbilla;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import java.io.IOException;

import in.fitbilla.ui.activity.ProfileActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by prateek on 2/28/16.
 */
public class GCMCreator extends GcmListenerService {

    private static final String TAG = GCMCreator.class.getSimpleName();

    private SharedPreferences sharedPreferences;

    @Override
    public void onMessageReceived(final String from, final Bundle data) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sendNotification(1000, data);
    }

    private void sendNotification(final int id, final Bundle data) {
        final String message = "Bravo! You just finished a set of your reps.";
        final String summary = "Bravo! You just finished a set of your reps.";
        createNotification(id, message, summary);
    }

    private void createNotification(int id, String message, String summary) {
        final PendingIntent resultPendingIntent = getPendingIntent();

        final NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        Uri defaultSoundUri = Uri.parse("GENERAL_RINGTONE_URI");


        nb.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentIntent(resultPendingIntent)
                .setContentText(message)
                .setAutoCancel(true);

        if (defaultSoundUri != null) {
            nb.setSound(defaultSoundUri);
        } else {
            nb.setDefaults(Notification.DEFAULT_SOUND);
        }
        nb.setPriority(Notification.PRIORITY_HIGH);
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle()
                    .bigText(message)
                    .setBigContentTitle(getString(R.string.app_name));

        if (summary != null) {
                bigTextStyle.setSummaryText(summary);
        }
        nb.setStyle(bigTextStyle);

        Notification notification = nb.build();
        pushNotification(notification, id);
    }

    private PendingIntent getPendingIntent() {
        Intent resultIntent = new Intent(this, ProfileActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );        if("android.intent.action.VIEW".equals(resultIntent.getAction())){
            return PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(resultIntent);
            return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(final String message, final int id, final String image_url) {
        AsyncTask<Void, Void, Bitmap> t = new AsyncTask<Void, Void, Bitmap>() {
            protected Bitmap doInBackground(Void... p) {
                Bitmap notification_picture = null;
                try {
                    OkHttpClient client_ok = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(image_url)
                            .build();

                    Response response = client_ok.newCall(request).execute();
                    byte[] image = response.body().bytes();
                    notification_picture = BitmapFactory.decodeByteArray(image, 0, image.length);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return notification_picture;
            }

            protected void onPostExecute(Bitmap notification_picture) {
                Notification notification = createNotification(message, notification_picture);
                pushNotification(notification, id);
            }
        };

        t.execute();
    }

    public void pushNotification(Notification notification, int id) {
        NotificationManager notificationManager = getNotificationManager();
        notificationManager.notify(id, notification);
    }

    private void sendNotification(String message, int id) {
        Notification notification = createNotification(message, null);
        pushNotification(notification, id);
    }

    public Notification createNotification(String message, Bitmap notification_picture) {
        Intent resultIntent = new Intent(this, ProfileActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        final NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
        Uri defaultSoundUri = Uri.parse("GENERAL_RINGTONE_URI");

        nb.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentIntent(resultPendingIntent)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message)
                        .setBigContentTitle(getString(R.string.app_name))
                        .setSummaryText(message))
                .setAutoCancel(true);
        if (defaultSoundUri != null) {
            nb.setSound(defaultSoundUri);
        } else {
            nb.setDefaults(Notification.DEFAULT_SOUND);
        }
        nb.setPriority(Notification.PRIORITY_HIGH);
        return nb.build();
    }

    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
}
