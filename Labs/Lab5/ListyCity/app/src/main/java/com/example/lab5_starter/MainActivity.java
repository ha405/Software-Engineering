package com.example.lab5_starter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
public class MainActivity extends AppCompatActivity implements CityDialogFragment.CityDialogListener {

    private Button addCityButton;
    private ListView cityListView;
    private ArrayList<City> cityArrayList;
    private CityArrayAdapter cityArrayAdapter;

    private FirebaseFirestore firestoreDb;
    private CollectionReference citiesCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        configureLayout();
        initFirestoreConnection();
        setupListDisplay();
        attachUiListeners();
    }

    private void configureLayout() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initFirestoreConnection() {
        firestoreDb = FirebaseFirestore.getInstance();
        citiesCollection = firestoreDb.collection("cities");
        
        citiesCollection.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                Log.w("FirestoreSync", "Registration failed.", error);
                return;
            }

            if (snapshot != null) {
                refreshDataList(snapshot);
            }
        });
    }

    private void refreshDataList(QuerySnapshot snapshot) {
        cityArrayList.clear();
        for (QueryDocumentSnapshot document : snapshot) {
            String name = document.getId();
            String province = document.getString("province");
            cityArrayList.add(new City(name, province));
        }
        cityArrayAdapter.notifyDataSetChanged();
    }

    private void setupListDisplay() {
        cityArrayList = new ArrayList<>();
        cityArrayAdapter = new CityArrayAdapter(this, cityArrayList);
        cityListView = findViewById(R.id.listviewCities);
        cityListView.setAdapter(cityArrayAdapter);
    }

    private void attachUiListeners() {
        addCityButton = findViewById(R.id.buttonAddCity);
        addCityButton.setOnClickListener(v -> openCityManagementDialog(null, "ADD_NEW_CITY"));

        cityListView.setOnItemClickListener((parent, view, position, id) -> {
            City targetCity = cityArrayList.get(position);
            openCityManagementDialog(targetCity, "VIEW_OR_EDIT_CITY");
        });

        cityListView.setOnItemLongClickListener((parent, view, position, id) -> {
            City cityToDelete = cityArrayList.get(position);
            removeCity(cityToDelete);
            return true;
        });
    }

    private void openCityManagementDialog(City city, String fragmentTag) {
        CityDialogFragment dialog = (city == null) ? 
                new CityDialogFragment() : CityDialogFragment.newInstance(city);
        dialog.show(getSupportFragmentManager(), fragmentTag);
    }

    private void removeCity(City city) {
        citiesCollection.document(city.getName())
                .delete()
                .addOnSuccessListener(unused -> Log.i("FirestoreSync", "City removed: " + city.getName()))
                .addOnFailureListener(e -> Log.e("FirestoreSync", "Delete failed", e));
    }

    @Override
    public void addCity(City newCity) {
        persistCity(newCity);
    }

    @Override
    public void updateCity(City originalCity, String newName, String newProvince) {
        // Simple overwrite using city name as ID
        persistCity(new City(newName, newProvince));
    }

    private void persistCity(City city) {
        HashMap<String, Object> cityData = new HashMap<>();
        cityData.put("province", city.getProvince());

        citiesCollection.document(city.getName())
                .set(cityData)
                .addOnSuccessListener(unused -> Log.i("FirestoreSync", "Sync successful for " + city.getName()))
                .addOnFailureListener(e -> Log.e("FirestoreSync", "Sync failed", e));
    }
}