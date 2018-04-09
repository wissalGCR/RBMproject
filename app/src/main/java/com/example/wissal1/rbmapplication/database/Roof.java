package com.example.wissal1.rbmapplication.database;

/**
 * Created by WISSAL1 on 08/04/2018.
 */

public class Roof {
    public static final String TABLE_NAME = "roof";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NUMRUCHE = "numruche";
    public static final String COLUMN_VALEURROOF = "valeurroof";
    public static final String COLUMN_DATE   = "date";
    // Create table SQL query
    public static final String CREATE_TABLE_ROOF =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NUMRUCHE + " TEXT,"
                    + COLUMN_VALEURROOF + " TEXT ,"+COLUMN_DATE+"DATETIME DEFAULT CURRENT_DATE"
                    + ")";




    private int id ;
    private String numruche ;
    private String valeurRoof;
    private String date;
    public Roof(){

    }
    public Roof(int id ,String numruche,String valeurRoof,String date){
        this.id=id ;
        this.numruche=numruche;
        this.valeurRoof=valeurRoof;
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

    public String getValeurRoof() {
        return valeurRoof;
    }

    public void setValeurRoof(String valeurRoof) {
        this.valeurRoof = valeurRoof;
    }

    public String getDate() {
        return date;
    }

    public  void setDate(String date) {
        this.date = date;
    }

}
