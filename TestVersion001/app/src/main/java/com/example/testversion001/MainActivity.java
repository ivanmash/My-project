package com.example.testversion001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.net.Uri;
import android.telephony.gsm.SmsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void smsSend(View v) {

        EditText number=(EditText)findViewById(R.id.number);
        EditText message=(EditText)findViewById(R.id.message);

        String numberText = number.getText().toString();
        String messageText= message.getText().toString();

        SmsManager.getDefault()
                .sendTextMessage(numberText, null, messageText.toString(), null, null);
    }
}