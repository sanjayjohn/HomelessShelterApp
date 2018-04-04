package com.example.rob.shelterfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppActivity extends AppCompatActivity {
    private ArrayList<String> array;
    static ArrayList<String> menArray;
    static ArrayList<String> womenArray;
    static ArrayList<String> familyArray;
    static ArrayList<String> childrenArray;
    static ArrayList<String> youngAdultArray;
    static ArrayList<String> anyoneArray;
    static ArrayList<String> s0;
    static ArrayList<String> s1;
    static ArrayList<String> s2;
    static ArrayList<String> s3;
    static ArrayList<String> s4;
    static ArrayList<String> s5;
    static ArrayList<String> s6;
    static ArrayList<String> s7;
    static ArrayList<String> s8;
    static ArrayList<String> s9;
    static ArrayList<String> s10;
    static ArrayList<String> s11;
    static ArrayList<String> s12;

    DatabaseReference mRef;
    ListView listview1;
    ArrayList<String> list = new ArrayList<>();
    public static String theName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button signAccount = (Button) findViewById(R.id.logout);
        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });

        Button goMap = (Button) findViewById(R.id.map);
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
        s0 = new ArrayList<>();
        s1 = new ArrayList<>();
        s2 = new ArrayList<>();
        s3 = new ArrayList<>();
        s4 = new ArrayList<>();
        s5 = new ArrayList<>();
        s6 = new ArrayList<>();
        s7 = new ArrayList<>();
        s8 = new ArrayList<>();
        s9 = new ArrayList<>();
        s10 = new ArrayList<>();
        s11 = new ArrayList<>();
        s12 = new ArrayList<>();

        final ListView listView1 = (ListView) findViewById(R.id.listView1);
        final SearchView inputSearch = findViewById(R.id.inputSearch);

        mRef.child("Shelters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                array.clear();
                final Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    ShelterData value = child.getValue(ShelterData.class);
                    String newValue = value.toString();
//                    Log.w("Item", ""+ newValue);
                    array.add(newValue);
                    if (value.getUniqueKey().equals("3")
                            || value.getUniqueKey().equals("4") || value.getUniqueKey().equals("5")
                            || value.getUniqueKey().equals("6") || value.getUniqueKey().equals("11")) {
                        Log.w("ActualItem", ""+ newValue);
                        menArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("2") || value.getUniqueKey().equals("5")
                            || value.getUniqueKey().equals("6") || value.getUniqueKey().equals("7")
                            || value.getUniqueKey().equals("10")) {
                        womenArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("0") || value.getUniqueKey().equals("6")
                            || value.getUniqueKey().equals("8") || value.getUniqueKey().equals("9")) {
                        familyArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("1") || value.getUniqueKey().equals("2")
                            || value.getUniqueKey().equals("6") || value.getUniqueKey().equals("7")
                            || value.getUniqueKey().equals("10")) {
                        childrenArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("1") || value.getUniqueKey().equals("6")
                            || value.getUniqueKey().equals("12")) {
                        youngAdultArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("6")) {
                        anyoneArray.add(newValue);
                    }
                    if (value.getUniqueKey().equals("0")) {
                        s0.add(newValue);
                    } else if (value.getUniqueKey().equals("1")) {
                        s1.add(newValue);
                    } else if (value.getUniqueKey().equals("2")) {
                        s2.add(newValue);
                    } else if (value.getUniqueKey().equals("3")) {
                        s3.add(newValue);
                    } else if (value.getUniqueKey().equals("4")) {
                        s4.add(newValue);
                    } else if (value.getUniqueKey().equals("5")) {
                        s5.add(newValue);
                    } else if (value.getUniqueKey().equals("6")) {
                        s6.add(newValue);
                    } else if (value.getUniqueKey().equals("7")) {
                        s7.add(newValue);
                    } else if (value.getUniqueKey().equals("8")) {
                        s8.add(newValue);
                    } else if (value.getUniqueKey().equals("9")) {
                        s9.add(newValue);
                    } else if (value.getUniqueKey().equals("10")) {
                        s10.add(newValue);
                    } else if (value.getUniqueKey().equals("11")) {
                        s11.add(newValue);
                    } else if (value.getUniqueKey().equals("12")) {
                        s12.add(newValue);
                    }
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
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
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, menArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (womenArray.size() > 0 && query.equals("Women")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, womenArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (childrenArray.size() > 0 && query.equals("Children")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, childrenArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (familyArray.size() > 0 && query.equals("Family")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, familyArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (youngAdultArray.size() > 0 && query.equals("Young Adult")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, youngAdultArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (anyoneArray.size() > 0 && query.equals("Anyone")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, anyoneArray);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s0.size() > 0 && query.equals("Atlanta Children Center")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s0);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s1.size() > 0 && query.equals("Covenant House Georgia")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s1);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s2.size() > 0 && query.equals("Eden Village")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s2);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s3.size() > 0 && query.equals("Fuqua Hall")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s3);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s4.size() > 0 && query.equals("Gateway Center")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s4);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s5.size() > 0 && query.equals("Homes of Light")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s5);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s6.size() > 0 && query.equals("Hope Atlanta")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s6);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s7.size() > 0 && query.equals("My Sisters House")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s7);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s8.size() > 0 && query.equals("Nicholas House")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s8);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s9.size() > 0 && query.equals("Our House")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s9);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s10.size() > 0 && query.equals("Atlanta Day Center for Women & Children")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s10);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s11.size() > 0 && query.equals("The Shepherds Inn")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s11);

                            listView1.setAdapter(adapter2);
                            return true;
                        } else if (s12.size() > 0 && query.equals("Young Adult Guidance Center")) {
                            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, s12);

                            listView1.setAdapter(adapter2);
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (newText.isEmpty()) {
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
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
