package com.example.wissal1.rbmapplication.database;

/**
 * Created by WISSAL1 on 08/04/2018.
 */

public class Humidity {
    public static final String TABLE_NAME = "humidity";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NUMRUCHE = "numruche";
    public static final String COLUMN_VALEURHUMIDITY = "valeurhumdity";
    public static final String COLUMN_DATE   = "date";
    // Create table SQL query
    public static final String CREATE_TABLE_HUM =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NUMRUCHE + " TEXT,"
                    + COLUMN_VALEURHUMIDITY + " TEXT ,"+COLUMN_DATE+"DATETIME DEFAULT CURRENT_DATE"
                    + ")";




    private int id ;
    private String numruche ;
    private String valeurHumidity;
    private String date;
    public Humidity(){

    }
    public Humidity(int id ,String numruche,String valeurHumidity,String date){
        this.id=id ;
        this.numruche=numruche;
        this.valeurHumidity=valeurHumidity;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumruche() {
        return numruche;
    }

    public void setNumruche(String numruche) {
        this.numruche = numruche;
    }

    public String getValeurHumidity() {
        return valeurHumidity;
    }

    public void setValeurHumidity(String valeurTemperature) {
        this.valeurHumidity = valeurTemperature;
    }

    public String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

}
