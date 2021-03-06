package com.example.wissal1.rbmapplication.Codes.Temperature;
import com.example.wissal1.rbmapplication.Codes.Humidity.HumHistory;
import com.example.wissal1.rbmapplication.R;
import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wissal1.rbmapplication.database.MabaseSqLite;
import com.example.wissal1.rbmapplication.database.Temperature;
import com.example.wissal1.rbmapplication.utils.MyDividerItemDecoration;
import com.example.wissal1.rbmapplication.utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class TempHistory extends AppCompatActivity {
    final static String RBM_NUMBER="21102281";
    private static TempHistory inst;
    EditText input;
    Context context;
    SmsManager smsManager = SmsManager.getDefault();
    private static final int READ_SMS_PERMISSIONS_REQUEST = 1;
    EditText inputTemperature ;
    private TemperaturesAdapter mAdapter;
    private List<Temperature> temperaturesList = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private TextView noTemperaturesView;
    private MabaseSqLite db;
  /*  public static TempHistory instance() {
        return inst;
    }*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_history);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        recyclerView = findViewById(R.id.recycler_view);
        noTemperaturesView = findViewById(R.id.empty_tem_view);
        db = new MabaseSqLite(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTemperatureDialog(false, null, 1);
            }
        });

        temperaturesList.addAll(db.getAllTemperatures());
        mAdapter = new TemperaturesAdapter(this, temperaturesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(mAdapter);

        toggleEmptyNotes();

        /**
         * On long press on RecyclerView item, open alert dialog
         * with options to choose
         * Edit and Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));}




    /**
     * Inserting new note in db
     * and refreshing the list
     */

    String num ="20" ;
    String temperature="20";




    public void createTemperature(String num ,String temperature) {
        // inserting note in db and getting
        // newly inserted temperature id
        long id = db.insertTemperature(num ,temperature);

        // get the newly inserted temperature from db
        Temperature t = db.getTemperature(id);

        if (t != null) {
            // adding new temperature to array list at 0 position
            temperaturesList.add(0, t);

            // refreshing the list
            mAdapter.notifyDataSetChanged();

            toggleEmptyNotes();
        }

    }

    /**
     * Updating temperature in db and updating
     * item in the list by its position
     */
    private void updateTemperature(String temperature, int position) {
        Temperature t = temperaturesList.get(position);
        // updating temperature text
        t.setValeurTemperature(temperature);

        // updating temperature in db
        //db.updateNote(n);

        // refreshing the list
        temperaturesList.set(position, t);
        mAdapter.notifyItemChanged(position);

        toggleEmptyNotes();
    }
    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose option");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showTemperatureDialog(true, temperaturesList.get(position), position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }

    /**
     * Deleting note from SQLite and removing the
     * item from the list by its position
     */
    private void deleteNote(int position) {
        // deleting the note from db
        db.deleteTemperature(temperaturesList.get(position));

        // removing the note from the list
        temperaturesList.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyNotes();
    }
    private void showTemperatureDialog(final boolean shouldUpdate, final Temperature temperature, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.temperature_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(TempHistory.this);
        alertDialogBuilderUserInput.setView(view);
        final EditText phonenumber = view.findViewById(R.id.phonenumber);

        //inputTemperature = view.findViewById(R.id.input);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(!shouldUpdate ? getString(R.string.lbl_new_note_title) : getString(R.string.lbl_edit_note_title));

      /* if (shouldUpdate && note != null) {
            inputNote.setText(note.getNote());
        }*/
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(shouldUpdate ? "update" : "send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(phonenumber.getText().toString())) {
                    Toast.makeText(TempHistory.this, "Enter numero telephone!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                    try{

                        android.telephony.SmsManager smsManager = android.telephony.SmsManager.getDefault();
                        smsManager.sendTextMessage(RBM_NUMBER,null,"TC",null,null);
                        Toast.makeText(TempHistory.this,"sms sent!",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(TempHistory.this," Failed!",Toast.LENGTH_SHORT).show();
                    }

                } }
        });
    }





    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToReadSMS() {
        if (ContextCompat.checkSelfPermission(TempHistory.this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_SMS)) {
                Toast.makeText(this, "Please allow permission!", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_SMS},
                    READ_SMS_PERMISSIONS_REQUEST);
        }
    }




    /**
     * Toggling list and empty notes view
     */
    private void toggleEmptyNotes() {
        // you can check notesList.size() > 0

        if (db.getTemperaturesCount() > 0) {
            noTemperaturesView.setVisibility(View.GONE);
        } else {
            noTemperaturesView.setVisibility(View.VISIBLE);
        }
    }


}