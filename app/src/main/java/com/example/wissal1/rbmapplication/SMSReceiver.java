package com.example.wissal1.rbmapplication;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.Logger;


// Todo : BroadcastReceiver Class To Receive And Read SMS

public class SMSReceiver extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    // private static final Logger logger = Logger.getLogger();
    public static final String RBM_NUMBER = "55555";

    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";

            for (int i = 0; i < sms.length; ++i) {


                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);
                String smsBody = smsMessage.getMessageBody().toString();
                String sender = smsMessage.getOriginatingAddress();


               /* if (RBM_NUMBER.equals(sender)) {*/

                    smsMessageStr += "SMS From: " + sender + "\n";
                    smsMessageStr += smsBody + "\n";
                    Toast.makeText(context, smsMessageStr, Toast.LENGTH_SHORT).show();
                   // this.abortBroadcast();

                }
              /*  else
                {
                    Toast.makeText(context, "Not from needed number", Toast.LENGTH_SHORT).show();
                    MainActivity inst = MainActivity.instance();
                    inst.removeFromList();}
            }*/
            //this will update the UI with message
            MainActivity inst = MainActivity.instance();
            inst.updateList(smsMessageStr);


        }

    }


}
