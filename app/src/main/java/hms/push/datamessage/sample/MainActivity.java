package hms.push.datamessage.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public String token;
    public MyReceiver receiver;
    public IntentFilter filter;
    Context context;
    TextView txt_device_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        txt_device_token = findViewById(R.id.txt_device_token);

        receiver = new MyReceiver();
        filter = new IntentFilter();
        filter.addAction("hms.push.datamessage.sample.ON_NEW_TOKEN");
        registerReceiver(receiver, filter);

    }

    public void unRegister() {
        unregisterReceiver(receiver);
    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("hms.push.datamessage.sample.ON_NEW_TOKEN".equals(intent.getAction())) {
                token = intent.getStringExtra("device_token");
                txt_device_token.setText("Device Token: \n\n" + token);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unRegister();
    }
}