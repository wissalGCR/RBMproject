package com.example.wissal1.rbmapplication.Codes.Commands;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.wissal1.rbmapplication.Codes.General.SendSMS;
import com.example.wissal1.rbmapplication.R;

public class Command extends AppCompatActivity {
 Switch heater,lampe,roof,fan;
    final static String RBM_NUMBER="5554";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        if (ContextCompat.checkSelfPermission(Command.this,
                android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Command.this,
                    android.Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(Command.this,
                        new String[]{android.Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(Command.this,
                        new String[]{android.Manifest.permission.SEND_SMS}, 1);
            }
        } else {

        }
      //command the heater
        heater= (Switch) findViewById(R.id.heater);
        heater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String open_heater="ho";
                String close_heater="hc";
            if(heater.isChecked()){

                try{

                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    smsManager.sendTextMessage(RBM_NUMBER,null,open_heater,null,null);
                    Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                }

            }
            else{
                try{

                    android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                    smsManager.sendTextMessage(RBM_NUMBER,null,close_heater,null,null);
                    Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                }
            }
            }
        });
        fan= (Switch) findViewById(R.id.fan);
        fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String open_fan="fo";
            String close_fan="fc";
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(fan.isChecked()){

                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,open_fan,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,close_fan,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // command the lampe
        lampe= (Switch) findViewById(R.id.lampe);
        lampe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String open_lampe="lo";
            String close_lampe="lc";
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(lampe.isChecked()){
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,open_lampe,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,close_lampe,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //comman the roof
        roof= (Switch) findViewById(R.id.roof);
        roof.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            String open_roof="ro";
            String close_roof="rc";
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(roof.isChecked()){
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,open_roof,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,close_roof,null,null);
                        Toast.makeText(getApplicationContext(),"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext()," Failed!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


       // command the fan

    }
    @Override
    public void onRequestPermissionsResult(int requestCode ,String[] permissions, int[] grantResults ) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(Command.this,
                            android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "permission granted!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "NO permission granted!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        }


    }
}
