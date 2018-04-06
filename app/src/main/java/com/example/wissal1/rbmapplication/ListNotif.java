package com.example.wissal1.rbmapplication;

import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class ListNotif extends AppCompatActivity {
  //  public static final String RBM_NUMBER = "55555";
  MabaseSqLite maBaseSQLite = new MabaseSqLite(this);
    SQLiteDatabase bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notif);
        bdd =maBaseSQLite.getWritableDatabase();
        TextView tv = (TextView) findViewById(R.id.tv) ;

      //  Cursor cr=bdd.rawQuery("SELECT * FROM sms WHERE msg='test' ",null) ;
       // cr.moveToFirst();

            Toast.makeText(getApplicationContext(),"nooo",Toast.LENGTH_SHORT).show();


     //  tv.setText(cr.getString(cr.getColumnIndex("number")));

        }
       /* ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
       String number = cursor.getString(cursor.getColumnIndexOrThrow("address"));
        String body =  cursor.getString(cursor.getColumnIndexOrThrow("body"));

        if (RBM_NUMBER.equals(number)){
            if(body.equals("test"))
            {
                notificationcall();
            }
        }

    }
      public void notificationcall() {

            NotificationCompat.Builder notifBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setDefaults(NotificationCompat.DEFAULT_ALL).setSmallIcon(R.drawable.notification_icon_background)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification_icon_background))
                    .setContentTitle("Notification from wissal")
                    .setContentText("you got a notification !");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notifBuilder.build());
        }*/
    }

