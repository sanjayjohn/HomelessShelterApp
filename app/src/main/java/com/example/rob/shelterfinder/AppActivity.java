package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {
    private ArrayList<String> array;
    static ArrayList<ShelterData> menArray;
    static ArrayList<ShelterData> womenArray;
    static ArrayList<ShelterData> familyArray;
    static ArrayList<ShelterData> childrenArray;
    static ArrayList<ShelterData> youngAdultArray;
    static ArrayList<ShelterData> anyoneArray;
    static ArrayList<ShelterData> allShelters;
    ArrayList<String> menString;
    ArrayList<String> womenString;
    ArrayList<String> familyString;
    ArrayList<String> childrenString;
    ArrayList<String> youngAdultString;
    ArrayList<String> anyoneString;

    DatabaseReference mRef;
    public static String theName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signAccount = findViewById(R.id.logout);
        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map(view);
            }
        });
        mRef = FirebaseDatabase.getInstance().getReference();
        array = new ArrayList<>();
        menArray = new ArrayList<>();
        womenArray = new ArrayList<>();
        familyArray = new ArrayList<>();
        childrenArray = new ArrayList<>();
        youngAdultArray = new ArrayList<>();
        anyoneArray = new ArrayList<>();
        allShelters = new ArrayList<>();
        menString = new ArrayList<>();
        womenString = new ArrayList<>();
        familyString = new ArrayList<>();
        childrenString = new ArrayList<>();
        youngAdultString = new ArrayList<>();
        anyoneString = new ArrayList<>();


        final ListView listView1 = findViewById(R.id.listView1);
        final SearchView inputSearch = findViewById(R.id.inputSearch);

        mRef.child("Shelters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                final Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    ShelterData value = child.getValue(ShelterData.class);
                    allShelters.add(value);
                    String newValue = value.toString();
                    array.add(newValue);
                    if (value.getRestrictions().contains("Men")) {
                        menArray.add(value);
                    }
                    if (value.getRestrictions().contains("Women")) {
                        womenArray.add(value);
                    }
                    if (value.getRestrictions().contains("Families")) {
                        familyArray.add(value);
                    }
                    if (value.getRestrictions().contains("Young adult")) {
                        youngAdultArray.add(value);
                    }
                    if (value.getRestrictions().contains("Children")) {
                        childrenArray.add(value);
                    }
                    if (value.getRestrictions().contains("Anyone")) {
                        anyoneArray.add(value);
                    }
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_list_item_1, array);

                listView1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                theName = parent.getItemAtPosition(position).toString().trim();
                Intent nextActivity = new Intent(getApplicationContext(), ShelterDetail.class);
                startActivity(nextActivity);
            }
        });

        inputSearch.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (menArray.size() > 0 && query.equals("Men")) {
                            for (int i = 0; i < menArray.size(); i++) {
                                menString.add(menArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, menString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (womenArray.size() > 0 && query.equals("Women")) {
                            for (int i = 0; i < womenArray.size(); i++) {
                                womenString.add(womenArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, womenString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (childrenArray.size() > 0 && query.equals("Children")) {
                            for (int i = 0; i < childrenArray.size(); i++) {
                                childrenString.add(childrenArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, childrenString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (familyArray.size() > 0 && query.equals("Family")) {
                            for (int i = 0; i < familyArray.size(); i++) {
                                familyString.add(familyArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, familyString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (youngAdultArray.size() > 0 && query.equals("Young Adult")) {
                            for (int i = 0; i < youngAdultArray.size(); i++) {
                                youngAdultString.add(youngAdultArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, youngAdultString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (anyoneArray.size() > 0 && query.equals("Anyone")) {
                            for (int i = 0; i < anyoneArray.size(); i++) {
                                anyoneString.add(anyoneArray.get(i).getShelterName());
                            }
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, anyoneString);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (allShelters.size() > 0) {
                            ArrayList<String> allSheltersString = new ArrayList<>();
                            for (int i = 0; i < allShelters.size(); i++) {
                                if (query.equals(allShelters.get(i).getShelterName())) {
                                    allSheltersString.add(allShelters.get(i).getShelterName());
                                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, allSheltersString);

                                    listView1.setAdapter(adapter2);
                                    return true;
                                }
                            }
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (newText.isEmpty()) {
                            final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, array);

                            listView1.setAdapter(adapter);
                            return true;
                        }
                        return false;
                    }
                }
        );



    }



    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void map(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }


}
