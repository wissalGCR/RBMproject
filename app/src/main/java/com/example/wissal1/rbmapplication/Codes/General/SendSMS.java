package com.example.wissal1.rbmapplication.Codes.General;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wissal1.rbmapplication.R;

import info.hoang8f.widget.FButton;

public class SendSMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.wissal1.rbmapplication.R.layout.activity_send_sms);
        FButton btn;
         final EditText no,msg;
          if (ContextCompat.checkSelfPermission(SendSMS.this,
                android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SendSMS.this,
                    android.Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(SendSMS.this,
                        new String[]{android.Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(SendSMS.this,
                        new String[]{android.Manifest.permission.SEND_SMS}, 1);
            }
        } else {

        }
        no=(EditText)findViewById(R.id.number);
        msg=(EditText)findViewById(R.id.msg);
        btn = (FButton) findViewById(R.id.envoyer);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number=no.getText().toString();
                String message=msg.getText().toString();
                try{

                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    smsManager.sendTextMessage(number,null,message,null,null);
                    Toast.makeText(SendSMS.this,"sms sent!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(SendSMS.this," Failed!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode ,String[] permissions, int[] grantResults ) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(SendSMS.this,
                            android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(SendSMS.this, "permission granted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SendSMS.this, "NO permission granted!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }


    }
    }

