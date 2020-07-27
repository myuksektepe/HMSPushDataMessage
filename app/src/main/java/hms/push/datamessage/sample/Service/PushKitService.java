package hms.push.datamessage.sample.Service;

import android.content.Intent;
import android.util.Log;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import hms.push.datamessage.sample.Helper.DataToNotification;

public class PushKitService extends HmsMessageService {

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.i("murat", "Device Token : " + token);
        sendTokenToDisplay(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage message) {
        super.onMessageReceived(message);
        if (message == null) {
            Log.e("murat", "Received message entity is null!");
            return;
        }

        Log.d("murat", "Data Message: " + message.getData());

        if (message.getData() != null) {
            try {
                JSONObject data = new JSONObject(message.getData());

                DataToNotification dataToNotification = new DataToNotification();
                dataToNotification.create(this, data);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /*
        Log.i("murat", "getCollapseKey: " + message.getCollapseKey()
                + "\n getData: " + message.getData()
                + "\n getFrom: " + message.getFrom()
                + "\n getTo: " + message.getTo()
                + "\n getMessageId: " + message.getMessageId()
                + "\n getSendTime: " + message.getSentTime()
                + "\n getMessageType: " + message.getMessageType()
                + "\n getTtl: " + message.getTtl());

        RemoteMessage.Notification notification = message.getNotification();
        if (notification != null) {
            Log.i("murat", "\n getImageUrl: " + notification.getImageUrl()
                    + "\n getTitle: " + notification.getTitle()
                    + "\n getTitleLocalizationKey: " + notification.getTitleLocalizationKey()
                    + "\n getTitleLocalizationArgs: " + Arrays.toString(notification.getTitleLocalizationArgs())
                    + "\n getBody: " + notification.getBody()
                    + "\n getBodyLocalizationKey: " + notification.getBodyLocalizationKey()
                    + "\n getBodyLocalizationArgs: " + Arrays.toString(notification.getBodyLocalizationArgs())
                    + "\n getIcon: " + notification.getIcon()
                    + "\n getSound: " + notification.getSound()
                    + "\n getTag: " + notification.getTag()
                    + "\n getColor: " + notification.getColor()
                    + "\n getClickAction: " + notification.getClickAction()
                    + "\n getChannelId: " + notification.getChannelId()
                    + "\n getLink: " + notification.getLink()
                    + "\n getNotifyId: " + notification.getNotifyId());
        }
         */

    }

    private void sendTokenToDisplay(String token) {
        Intent intent = new Intent("hms.push.datamessage.sample.ON_NEW_TOKEN");
        intent.putExtra("device_token", token);
        sendBroadcast(intent);
    }
}
