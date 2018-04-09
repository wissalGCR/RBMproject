package com.example.wissal1.rbmapplication.Codes.General;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wissal1.rbmapplication.Codes.Commands.Command;
import com.example.wissal1.rbmapplication.Codes.Humidity.HumHistory;
import com.example.wissal1.rbmapplication.Codes.Commands.Command;
import com.example.wissal1.rbmapplication.Codes.Temperature.TempHistory;
import com.example.wissal1.rbmapplication.R;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   // final String username="";
CardView TempCard,HumCard,CommandCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle b1= getIntent().getExtras();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TempCard = (CardView) findViewById(R.id.TempCard);
        TempCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =new Intent(Profile.this,TempHistory.class);
                startActivity(i1);
            }
        });
        HumCard = (CardView) findViewById(R.id.HumCard);
        HumCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 =new Intent(Profile.this,HumHistory.class);
                startActivity(i2);
            }
        });
        CommandCard = (CardView) findViewById(R.id.CommandCard);
        CommandCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 =new Intent(Profile.this,Command.class);
                startActivity(i3);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_send) {
            Intent i =new Intent(Profile.this,SendSMS.class);
            startActivity(i);}
           else if (id == R.id.nav_smslist) {

            Intent i =new Intent(Profile.this,MainActivity.class);
            startActivity(i);
            if (id == R.id.nav_logout) {
              /*  SharedPreferences preferences =getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                finish();*/
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*public void checkUser(){
        Boolean Check = Boolean.valueOf(UtilsClipCodes.readSharedSetting(Profile.this, "ClipCodes", "true"));

        Intent introIntent = new Intent(Profile.this, Login.class);
        introIntent.putExtra("ClipCodes", Check);

        if (Check) {
            startActivity(introIntent);
        }*/

}
