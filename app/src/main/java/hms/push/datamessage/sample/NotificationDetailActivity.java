package hms.push.datamessage.sample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationDetailActivity extends AppCompatActivity {

    String details;
    TextView txt_notification_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        txt_notification_details = findViewById(R.id.txt_notification_details);
        txt_notification_details.setText("");

        Intent intent = getIntent();

        //Bundle bundle = new Bundle();
        //bundle = getIntent().getExtras();
        if (intent != null) {
            details = intent.getStringExtra("details");
            Log.d("murat", "Details: " + details);

            txt_notification_details.setText(formatString(details));
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static String formatString(String text) {
        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n\n" + indentString + letter + "\n\n");
                    indentString = indentString + "\t\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t\t", "");
                    json.append("\n\n" + indentString + letter);
                    break;
                case ',':
                    json.append(letter + "\n\n" + indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }
}