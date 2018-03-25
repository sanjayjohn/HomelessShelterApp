package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReserveRoom extends AppCompatActivity {

    DatabaseReference mRef;
    private EditText roomsNeeded;
    private EditText roomsCancelled;
    private String neededString;
    private int capacity;
    private int max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_room);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        roomsNeeded   = (EditText)findViewById(R.id.roomsNeeded);
        roomsCancelled = (EditText)findViewById(R.id.roomsCancelled);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ShelterDetail.class);
                startActivity(i);
            }
        });

        Button reserve = (Button) findViewById(R.id.reserveRoom);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptReserve();
            }
        });

        Button cancel = (Button) findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptCancel();
            }
        });



        mRef = FirebaseDatabase.getInstance().getReference();

    }

    public void attemptReserve() {
        neededString = roomsNeeded.getText().toString().trim();
        mRef.child("Shelters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                capacity = dataSnapshot.child(AppActivity.theName).getValue(ShelterData.class).toInt();
                Log.w("capacity", ""+ capacity);
                Log.w("needed", ""+ neededString);
                int total = (capacity - Integer.parseInt(neededString));
                Log.w("currentCapacity", ""+ capacity);
                Log.w("currentNeeded", ""+ neededString);
                Log.w("total", ""+ total);
                if (total >= 0) {
                    HashMap<String, Object> result = new HashMap<>();
                    Log.w("total", ""+ total);
                    result.put("Capacity", Integer.toString(total));
                    mRef.child("Shelters").child(AppActivity.theName).updateChildren(result);
                }
                Intent i = new Intent(getApplicationContext(), ShelterDetail.class);
                startActivity(i);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


    }
    public void attemptCancel() {
        final String cancelString = roomsCancelled.getText().toString().trim();
        mRef.child("Shelters").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                capacity = dataSnapshot.child(AppActivity.theName).getValue(ShelterData.class).toInt();
                max = dataSnapshot.child(AppActivity.theName).getValue(ShelterData.class).toMax();
                int sum = capacity + Integer.parseInt(cancelString);
                if (sum <= max) {
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("Capacity", Integer.toString(sum));
                    mRef.child("Shelters").child(AppActivity.theName).updateChildren(result);
                }
                Intent i = new Intent(getApplicationContext(), ShelterDetail.class);
                startActivity(i);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

}
