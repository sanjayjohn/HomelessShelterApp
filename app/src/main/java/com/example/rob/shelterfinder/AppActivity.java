package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.util.Log;

public class AppActivity extends AppCompatActivity {
    private ArrayList<String> array;
    DatabaseReference mRef;
    ListView listview1;
    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mRef = FirebaseDatabase.getInstance().getReference();
        array = new ArrayList<>();
        final ListView listView1 = (ListView) findViewById(R.id.listView1);

        mRef.child("Shelters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    ShelterData value = child.getValue(ShelterData.class);
                    String newValue = value.toString();
//                    Log.w("Item", ""+ newValue);
                    array.add(newValue);
//                    Log.w("Item", ""+ array.get(0));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, array);

                listView1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, list);
//
//        listView1.setAdapter(adapter);
//        if (array == null) {
//            return;
//        } else {
//            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
//            listview.setAdapter(adapter);
//        }
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void showData(DataSnapshot dataSnapshot) {
//        ArrayList<String> array = new ArrayList<>();
//        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//            ShelterData data = new ShelterData();
//            for (int i = 0; i < 13; i++) {
//                data.setShelterName(ds.child(Integer.toString(i)).getValue(ShelterData.class).getShelterName());
//                data.setAddress(ds.child(Integer.toString(i)).getValue(ShelterData.class).getAddress());
//                data.setCapacity(ds.child(Integer.toString(i)).getValue(ShelterData.class).getCapacity());
//                data.setLatitude(ds.child(Integer.toString(i)).getValue(ShelterData.class).getLatitude());
//                data.setLongitude(ds.child(Integer.toString(i)).getValue(ShelterData.class).getLongitude());
//                data.setPhone(ds.child(Integer.toString(i)).getValue(ShelterData.class).getPhone());
//                data.setRestrictions(ds.child(Integer.toString(i)).getValue(ShelterData.class).getRestrictions());
//                data.setSpecialNotes(ds.child(Integer.toString(i)).getValue(ShelterData.class).getSpecialNotes());
//                data.setUniqueKey(ds.child(Integer.toString(i)).getValue(ShelterData.class).getUniqueKey());
//
//                array.add(data.getShelterName());
//
//
//            }
//
//            ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
//            listview.setAdapter(adpater);
//        }
//    }

}
