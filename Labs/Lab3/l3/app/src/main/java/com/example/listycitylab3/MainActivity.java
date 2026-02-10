package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ManageCityFragment.OnCityInteractionListener {

    private ListView cityListView;
    private CityAdapter adapter;
    private ArrayList<City> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize data
        cityList = new ArrayList<>();
        cityList.add(new City("Edmonton", "AB"));
        cityList.add(new City("Vancouver", "BC"));
        cityList.add(new City("Toronto", "ON"));

        // Initialize adapter
        cityListView = findViewById(R.id.city_list);
        adapter = new CityAdapter(this, cityList);
        cityListView.setAdapter(adapter);

        // Setup Floating Action Button for Adding Cities
        FloatingActionButton addButton = findViewById(R.id.add_city_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show ManageCityFragment for adding a new city (pass null)
                ManageCityFragment.newInstance(null).show(getSupportFragmentManager(), "ADD_CITY");
            }
        });

        // Setup Item Click Listener for Editing Cities
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City selectedCity = cityList.get(position);
                // Show ManageCityFragment for editing (pass selected city and its position)
                ManageCityFragment.newInstance(selectedCity, position).show(getSupportFragmentManager(), "EDIT_CITY");
            }
        });
    }

    @Override
    public void onCityAdded(City city) {
        adapter.add(city);
    }

    @Override
    public void onCityEdited(City originalCity, String newName, String newProvince, int position) {
        // Validate position
        if (position >= 0 && position < cityList.size()) {
            City cityToUpdate = cityList.get(position);
            cityToUpdate.setName(newName);
            cityToUpdate.setProvince(newProvince);
            
            // Notify adapter that data has changed
            adapter.notifyDataSetChanged();
        }
    }
}