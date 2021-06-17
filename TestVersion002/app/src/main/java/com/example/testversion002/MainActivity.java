package com.example.testversion002;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Numbers alldata = new Numbers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        EditText number1 = (EditText) findViewById(R.id.Arduino_number);
        alldata.GetANumb(number1.getText().toString());
        EditText number2 = (EditText) findViewById(R.id.user_number);
        alldata.GetUNumb(number2.getText().toString());
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }
}

