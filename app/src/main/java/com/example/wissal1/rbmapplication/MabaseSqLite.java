package com.example.wissal1.rbmapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.wissal1.rbmapplication.User;
/**
 * Created by WISSAL1 on 03/04/2018.
 */

 public class MabaseSqLite extends SQLiteOpenHelper {

    public MabaseSqLite(Context context) {
        super(context, "RBMdatabase", null, 1);
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_RBMdatabase =
                "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT,email VARCHAR(100), username VARCHAR(100), " +
                        "password VARCHAR(100));" ;
                  String smsTable= "CREATE TABLE sms (id INTEGER PRIMARY KEY AUTOINCREMENT,number varchar(100),msg varchar(200)) ";
            sqLiteDatabase.execSQL(CREATE_RBMdatabase);
        sqLiteDatabase.execSQL(smsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user;");
        onCreate(sqLiteDatabase);
    }
public void addUser(User user){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("username",user.getUsername());
    values.put("email",user.getEmail());
    values.put("password",user.getPassword());
    db.insert("user",null,values);
    db.close();
}
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",new String[]{"id","email","username","password"}," id+=?",
                new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        return new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));

    }

}




