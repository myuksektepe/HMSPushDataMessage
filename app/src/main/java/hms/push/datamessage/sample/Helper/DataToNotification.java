package hms.push.datamessage.sample.Helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.Random;

import hms.push.datamessage.sample.NotificationDetailActivity;
import hms.push.datamessage.sample.R;

public class DataToNotification {
    String CHANNEL_ID = "1";
    String CHANNEL_NAME = "HMSNoti";
    String CHANNEL_DESCRIPTION = "HMSDesc";
    int notificationId = 0;

    public void create(Context context, JSONObject data_message) {

        // Create Random Notificaiton ID
        notificationId = new Random().nextInt((999 - 0) + 1) + 0;

        createNotificationChannel(context);

        try {

            // Parse Data Message
            String type = data_message.getString("type");
            String title = data_message.getString("title");
            String content = data_message.getString("content");
            String big_text = data_message.optString("big_text");
            String small_icon = data_message.optString("small_icon");
            String style = data_message.optString("style");
            String on_click = data_message.optString("on_click");
            String data_string = data_message.optString("data");
            JSONObject data_json = null;

            try {
                data_json = new JSONObject(data_string);
            } catch (JSONException e) {
                Log.d("murat", "Data type is not JSON");
            }


            // Type Check
            if (type.equals("notification")) {

                // Create Notification Builder
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);

                // Check & Set Small Icon
                if (small_icon == null || small_icon.equals("")) {
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                } else {
                    builder.setSmallIcon(getResId(small_icon));
                }

                // Check Big Text Style
                if (style.equals("big_text") && big_text != null) {
                    builder.setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(big_text));
                }

                // OnClick - Tap the notification
                if (on_click == null || on_click.equals("open_app") || on_click.equals("")) {

                    // Detect first activity
                    //PackageManager pm = context.getPackageManager();
                    //Intent intent = pm.getLaunchIntentForPackage(context.getPackageName());

                    // NotificationDetail Activity
                    Intent intent = new Intent(context, NotificationDetailActivity.class);
                    intent.putExtra("details", data_message.toString());

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    builder.setContentIntent(pendingIntent);

                } else {

                    // Handle with on_click Value
                    //...

                }

                if (data_json != null) {

                    // Parse Data
                    /*
                    String key = data_json.optString("key");
                    String value = data_json.optString("value");

                    String key2 = data_json.optString("key2");
                    String value2 = data_json.optString("value2");
                     */

                    Log.d("murat", "Data JSON: " + data_json);
                } else {
                    Log.d("murat", "Data String: " + data_string);
                }


                // Send Notification
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(notificationId, builder.build());

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_NAME;
            String description = CHANNEL_DESCRIPTION;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static int getResId(String resName) {
        try {
            Field idField = R.drawable.class.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
