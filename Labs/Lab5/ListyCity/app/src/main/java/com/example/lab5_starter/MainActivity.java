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

    private FirebaseFirestore db;
    private CollectionReference citiesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        setupWindowInsets();
        initializeFirestore();
        setupListView();
        setupEventListeners();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeFirestore() {
        db = FirebaseFirestore.getInstance();
        citiesRef = db.collection("cities"); // Updated to lowercase as per instructions
        
        citiesRef.addSnapshotListener((querySnapshots, error) -> {
            if (error != null) {
                Log.e("Firestore", "Listen failed.", error);
                return;
            }

            if (querySnapshots != null) {
                updateCityList(querySnapshots);
            }
        });
    }

    private void updateCityList(QuerySnapshot querySnapshots) {
        cityArrayList.clear();
        for (QueryDocumentSnapshot doc : querySnapshots) {
            String city = doc.getId();
            String province = doc.getString("province");
            cityArrayList.add(new City(city, province));
        }
        cityArrayAdapter.notifyDataSetChanged();
    }

    private void setupListView() {
        cityArrayList = new ArrayList<>();
        cityArrayAdapter = new CityArrayAdapter(this, cityArrayList);
        cityListView = findViewById(R.id.listviewCities);
        cityListView.setAdapter(cityArrayAdapter);
    }

    private void setupEventListeners() {
        addCityButton = findViewById(R.id.buttonAddCity);
        addCityButton.setOnClickListener(view -> showCityDialog(null, "Add City"));

        cityListView.setOnItemClickListener((adapterView, view, position, id) -> {
            City selectedCity = cityArrayList.get(position);
            showCityDialog(selectedCity, "City Details");
        });

        cityListView.setOnItemLongClickListener((parent, view, position, id) -> {
            City cityToDelete = cityArrayList.get(position);
            deleteCityFromFirestore(cityToDelete);
            return true;
        });
    }

    private void showCityDialog(City city, String tag) {
        CityDialogFragment dialogFragment = city == null ? 
                new CityDialogFragment() : CityDialogFragment.newInstance(city);
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    private void deleteCityFromFirestore(City city) {
        citiesRef.document(city.getName())
                .delete()
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "City deleted successfully"))
                .addOnFailureListener(e -> Log.e("Firestore", "Error deleting city", e));
    }

    @Override
    public void addCity(City city) {
        saveCityToFirestore(city);
    }

    @Override
    public void updateCity(City city, String cityName, String province) {
        // If city name (ID) changed, we should delete old and add new, 
        // but for this lab we assume name is the unique ID.
        saveCityToFirestore(new City(cityName, province));
    }

    private void saveCityToFirestore(City city) {
        HashMap<String, String> data = new HashMap<>();
        data.put("province", city.getProvince());

        citiesRef.document(city.getName())
                .set(data)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "City saved successfully"))
                .addOnFailureListener(e -> Log.e("Firestore", "Error saving city", e));
    }
}