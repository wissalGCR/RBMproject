package com.example.wissal1.rbmapplication.Codes.General;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.example.wissal1.rbmapplication.database.MabaseSqLite;
import com.example.wissal1.rbmapplication.R;

import info.hoang8f.widget.FButton;

public class Login extends AppCompatActivity {
    MabaseSqLite maBaseSQLite = new MabaseSqLite(this);
    private EditText txtEmailLogin;
    private EditText txtPwd;
    private FButton btn;
    SQLiteDatabase bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bdd = maBaseSQLite.getWritableDatabase();
        setContentView(R.layout.activity_login);
        ContentValues values = new ContentValues();
        values.put("id", 1);
        values.put("email", "aaa@");
        values.put("username", "rbm");
        values.put("password", "123");
        bdd.insert("user", null, values);

        txtEmailLogin = (EditText) findViewById(R.id.user);
        txtPwd = (EditText) findViewById(R.id.pass);
        btn = (FButton) findViewById(R.id.login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(Login.this,Profile.class);
                startActivity(profileIntent);

            }
        });

        }

    }









              /*  Cursor cr=bdd.query("user",new String[]{"id","email","username","password"},null,null,null,null,null) ;
                cr.moveToFirst();
                boolean test=false;

                txtEmailLogin = (EditText) findViewById(R.id.user);
                txtPwd = (EditText) findViewById(R.id.pass);
             String mail =  txtEmailLogin.getText().toString();
                String pwd =txtPwd.getText().toString();
                while(cr.isAfterLast()==false) {
                    if((cr.getString(2).equals(mail)) && (cr.getString(3).equals(pwd)))
                    {
                        test=true;
                    }

                    cr.moveToNext();
                }

                if(test==true){
                    Toast.makeText(Login.this,"ok",Toast.LENGTH_SHORT);
                  //  User u=new User(cr.getInt(0),cr.getString(1),cr.getString(2),cr.getString(3));
                    Intent i1=new Intent(Login.this,Profile.class);
                   // i1.putExtra("username",cr.getString(2));
                    startActivity(i1);
                }

                else{
                    Toast.makeText(Login.this,"vous n'avez pas le droit d'accés",Toast.LENGTH_LONG).show();
                }
            }*/



