package com.example.wissal1.rbmapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WISSAL1 on 03/04/2018.
 */

 public class MabaseSqLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "iot_db";


    public MabaseSqLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(Temperature.CREATE_TABLE_TEMP);
        db.execSQL(Humidity.CREATE_TABLE_HUM);
        db.execSQL(Roof.CREATE_TABLE_ROOF);

    }
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Temperature.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Humidity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Roof.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }


    public long insertTemperature(String num,String temperature) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `date` will be inserted automatically.
        // no need to add them
        values.put(Temperature.COLUMN_VALEURTEMPERATURE, temperature);
        values.put(Temperature.COLUMN_NUMRUCHE, num);

        // insert row
        long id = db.insert(Temperature.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public Temperature getTemperature(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Temperature.TABLE_NAME,
                new String[]{Temperature.COLUMN_ID, Temperature.COLUMN_NUMRUCHE, Temperature.COLUMN_VALEURTEMPERATURE ,Temperature.COLUMN_DATE},
                Temperature.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Temperature temperature = new Temperature(
                cursor.getInt(cursor.getColumnIndex(Temperature.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_NUMRUCHE)),

                cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_VALEURTEMPERATURE)),
                cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_DATE)));

        // close the db connection
        cursor.close();

        return temperature;
    }

    public List<Temperature> getAllTemperatures() {
        List<Temperature> temperatures = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Temperature.TABLE_NAME ;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Temperature temperature = new Temperature();
                temperature.setId(cursor.getInt(cursor.getColumnIndex(Temperature.COLUMN_ID)));
                temperature.setValeurTemperature(cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_VALEURTEMPERATURE)));
                temperature.setDate(cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_DATE)));
                temperature.setNumruche(cursor.getString(cursor.getColumnIndex(Temperature.COLUMN_NUMRUCHE)));

                temperatures.add(temperature);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return temperatures;
    }
    public int getTemperaturesCount() {
        String countQuery = "SELECT  * FROM " + Temperature.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
    public int updateTemperature(Temperature temperature) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Temperature.COLUMN_VALEURTEMPERATURE, temperature.getValeurTemperature());

        // updating row
        return db.update(Temperature.TABLE_NAME, values, Temperature.COLUMN_ID + " = ?",
                new String[]{String.valueOf(temperature.getId())});
    }

    public void deleteTemperature(Temperature temperature) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Temperature.TABLE_NAME, Temperature.COLUMN_ID + " = ?",
                new String[]{String.valueOf(temperature.getId())});
        db.close();
    }


/********************************************************************/



public long insertHumidity(String num,String humidity) {
    // get writable database as we want to write data
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    // `id` and `date` will be inserted automatically.
    // no need to add them
    values.put(Humidity.COLUMN_VALEURHUMIDITY, humidity);
    values.put(Humidity.COLUMN_NUMRUCHE, num);

    // insert row
    long id = db.insert(Humidity.TABLE_NAME, null, values);

    // close db connection
    db.close();

    // return newly inserted row id
    return id;
}


    public Humidity getHumidity(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Temperature.TABLE_NAME,
                new String[]{Humidity.COLUMN_ID, Humidity.COLUMN_NUMRUCHE, Humidity.COLUMN_VALEURHUMIDITY ,Humidity.COLUMN_DATE},
                Humidity.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Humidity humidity = new Humidity(
                cursor.getInt(cursor.getColumnIndex(Humidity.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_NUMRUCHE)),

                cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_VALEURHUMIDITY)),
                cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_DATE)));

        // close the db connection
        cursor.close();

        return humidity;
    }

    public List<Humidity> getAllHumidity() {
        List<Humidity> humidityList = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Humidity.TABLE_NAME ;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Humidity humidity = new Humidity();
                humidity.setId(cursor.getInt(cursor.getColumnIndex(Humidity.COLUMN_ID)));
                humidity.setValeurHumidity(cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_VALEURHUMIDITY)));
                humidity.setDate(cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_DATE)));
                humidity.setNumruche(cursor.getString(cursor.getColumnIndex(Humidity.COLUMN_NUMRUCHE)));

                humidityList.add(humidity);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return humidityList;
    }
    public int getHumidityCount() {
        String countQuery = "SELECT  * FROM " + Humidity.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
    public int updateHumidity(Humidity humidity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Humidity.COLUMN_VALEURHUMIDITY, humidity.getValeurHumidity());

        // updating row
        return db.update(Humidity.TABLE_NAME, values, Humidity.COLUMN_ID + " = ?",
                new String[]{String.valueOf(humidity.getId())});
    }

    public void deleteHumidity(Humidity humidity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Humidity.TABLE_NAME, Humidity.COLUMN_ID + " = ?",
                new String[]{String.valueOf(humidity.getId())});
        db.close();
    }
}