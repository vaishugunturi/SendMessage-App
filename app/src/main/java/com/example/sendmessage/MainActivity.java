package com.example.sendmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    Button send;
    EditText message,number;

    String user_message;

    String user_number;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.buttonsend);
        message=  findViewById(R.id.editTextmessage);
        number = findViewById(R.id.editTextNumber);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user_message = message.getText().toString();
                user_number = number.getText().toString();

                sendMessage(user_message,user_number);



            }
        });

    }

    public void sendMessage(String user_message,String user_number)
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        }
        else
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(user_number,null,user_message,null,null);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==1 && grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(user_number,null,user_message,null,null);
        }
    }
}