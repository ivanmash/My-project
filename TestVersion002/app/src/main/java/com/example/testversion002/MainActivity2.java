package com.example.testversion002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;

import android.telephony.gsm.SmsManager;

import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    Numbers alldata = new Numbers();
    String messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void OnClickButtonON(View v) {
        messageText = "On" + alldata.GetUNumb();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                SmsManager.getDefault()
                        .sendTextMessage(alldata.GetANumb(), null, messageText, null, null);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    public void OnClickButtonOFF(View v) {
        messageText = "Off";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                SmsManager.getDefault()
                        .sendTextMessage(alldata.GetANumb(), null, messageText, null, null);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(alldata.GetANumb(), null, messageText, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}