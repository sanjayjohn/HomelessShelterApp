package com.example.rob.shelterfinder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.SearchView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    static ArrayList<ShelterData> allShelters = new ArrayList<>();
    static ArrayList<ShelterData> menArray = new ArrayList<>();
    static ArrayList<ShelterData> womenArray = new ArrayList<>();
    static ArrayList<ShelterData> familyArray = new ArrayList<>();
    static ArrayList<ShelterData> childrenArray = new ArrayList<>();
    static ArrayList<ShelterData> youngAdultArray = new ArrayList<>();
    static ArrayList<ShelterData> anyoneArray = new ArrayList<>();

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }
    }


    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 4f;
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    DatabaseReference mRef;
    ArrayList<Marker> array = new ArrayList<>();
    ArrayList<Marker> array2 = new ArrayList<>();
    ArrayList<Marker> array3 = new ArrayList<>();
    ArrayList<Marker> menPin = new ArrayList<>();
    ArrayList<Marker> womenPin = new ArrayList<>();
    ArrayList<Marker> familyPin = new ArrayList<>();
    ArrayList<Marker> youngAdultPin = new ArrayList<>();
    ArrayList<Marker> anyonePin = new ArrayList<>();
    ArrayList<Marker> childrenPin = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getLocationPermission();


        final SearchView inputSearch = findViewById(R.id.inputSearch1);


        mRef = FirebaseDatabase.getInstance().getReference();

        mRef.child("Shelters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    ShelterData value = child.getValue(ShelterData.class);
                    allShelters.add(value);
                    double lat = Double.parseDouble(value.getLatitude());
                    double lon = Double.parseDouble(value.getLongitude());
                    LatLng coord = new LatLng(lat, lon);
                    Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                    pin.setTag(0);
                    array.add(pin);
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

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        inputSearch.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        for (int i = 0; i < array.size(); i++) {
                            array.get(i).setVisible(false);
                        }
                        for (int i = 0; i < array2.size(); i++) {
                            array2.get(i).setVisible(false);
                        }
                        for (int i = 0; i < array3.size(); i++) {
                            array3.get(i).setVisible(false);
                        }
                        for (int i = 0; i < menPin.size(); i++) {
                            menPin.get(i).setVisible(false);
                        }
                        for (int i = 0; i < womenPin.size(); i++) {
                            womenPin.get(i).setVisible(false);
                        }
                        for (int i = 0; i < childrenPin.size(); i++) {
                            childrenPin.get(i).setVisible(false);
                        }
                        for (int i = 0; i < familyPin.size(); i++) {
                            familyPin.get(i).setVisible(false);
                        }
                        for (int i = 0; i < youngAdultPin.size(); i++) {
                            youngAdultPin.get(i).setVisible(false);
                        }
                        for (int i = 0; i < anyonePin.size(); i++) {
                            anyonePin.get(i).setVisible(false);
                        }
                        if (menArray.size() > 0 && query.equals("Men")) {
                            menPin.clear();
                            for (int i = 0; i < menArray.size(); i++) {
                                ShelterData value = menArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                menPin.add(pin);
                            }
                            return true;
                        } else if (womenArray.size() > 0 && query.equals("Women")) {
                            womenPin.clear();
                            for (int i = 0; i < womenArray.size(); i++) {
                                ShelterData value = womenArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                womenPin.add(pin);
                            }

                            return true;
                        } else if (AppActivity.childrenArray.size() > 0 && query.equals("Children")) {
                            childrenPin.clear();
                            for (int i = 0; i < childrenArray.size(); i++) {
                                ShelterData value = childrenArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                childrenPin.add(pin);

                            }
                            return true;
                        } else if (familyArray.size() > 0 && query.equals("Family")) {
                            familyPin.clear();
                            for (int i = 0; i < familyArray.size(); i++) {
                                ShelterData value = familyArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                familyPin.add(pin);
                            }
                            return true;
                        } else if (youngAdultArray.size() > 0 && query.equals("Young Adult")) {
                            youngAdultPin.clear();
                            for (int i = 0; i < youngAdultArray.size(); i++) {
                                ShelterData value = youngAdultArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                youngAdultPin.add(pin);
                            }
                            return true;
                        } else if (anyoneArray.size() > 0 && query.equals("Anyone")) {
                            anyonePin.clear();
                            for (int i = 0; i < anyoneArray.size(); i++) {
                                ShelterData value = anyoneArray.get(i);
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                anyonePin.add(pin);
                            }
                            return true;
                        } else if (allShelters.size() > 0) {
                            array3.clear();
                            for (int i = 0; i < allShelters.size(); i++) {
                                if (query.equals(allShelters.get(i).getShelterName())) {
                                    ShelterData value = allShelters.get(i);
                                    double lat = Double.parseDouble(value.getLatitude());
                                    double lon = Double.parseDouble(value.getLongitude());
                                    LatLng coord = new LatLng(lat, lon);
                                    Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                    pin.setTag(0);
                                    array3.add(pin);
                                }
                            }
                            return true;
                        }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (newText.isEmpty()) {
                            array2.clear();
                            for (int i = 0; i < allShelters.size(); i++) {
                                ShelterData value = allShelters.get(i);
                                Log.w("Item", ""+ value.getShelterName());
                                double lat = Double.parseDouble(value.getLatitude());
                                double lon = Double.parseDouble(value.getLongitude());
                                LatLng coord = new LatLng(lat, lon);
                                Marker pin = mMap.addMarker(new MarkerOptions().position(coord).title(value.getShelterName()).snippet("Capacity:   " + value.getCapacity() + " " + "Gender:   " + value.getRestrictions() + " " + "Address:   " + value.getAddress()));
                                pin.setTag(0);
                                array2.add(pin);
                            }
                            return true;
                        }
                        return false;
                    }
                }
        );


    }

    private void getDeviceLocation(){

        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()) {
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);

                        }
                    }
                });
            }
        }catch (SecurityException e){
            System.out.print("Security Error");
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void getLocationPermission(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


}

