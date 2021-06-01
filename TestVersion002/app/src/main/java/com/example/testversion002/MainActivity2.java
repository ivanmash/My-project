package com.example.testversion002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void OnClickButtonON(View v) {
        String messageText= "On";
        SmsManager.getDefault()
                .sendTextMessage("+79854591506", null, messageText.toString(), null, null);
    }
    public void OnClickButtonOFF(View v) {
        String messageText= "Off";
        SmsManager.getDefault()
                .sendTextMessage("+79854591506", null, messageText.toString(), null, null);
    }
}