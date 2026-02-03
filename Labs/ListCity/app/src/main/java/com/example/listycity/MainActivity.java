package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ListView listViewCities;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> cityNames;
    private int currentlySelectedPosition = -1;

    private Button btnAdd;
    private Button btnRemove;
    private LinearLayout inputContainer;
    private EditText inputField;
    private Button btnConfirmAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupCityList();
        configureButtonListeners();
    }

    private void initializeViews() {
        listViewCities = findViewById(R.id.city_list);
        btnAdd = findViewById(R.id.add_city_button);
        btnRemove = findViewById(R.id.delete_city_button);
        inputContainer = findViewById(R.id.add_city_layout);
        inputField = findViewById(R.id.add_city_edit_text);
        btnConfirmAdd = findViewById(R.id.confirm_button);
    }

    private void setupCityList() {
        String[] initialCities = {"Edmonton", "Vancouver", "Montréal", "Calgary", "Toronto"};
        cityNames = new ArrayList<>(Arrays.asList(initialCities));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityNames);
        listViewCities.setAdapter(adapter);

        listViewCities.setOnItemClickListener(this::onCityItemSelected);
    }

    private void configureButtonListeners() {
        btnAdd.setOnClickListener(view -> showInputContainer());
        btnConfirmAdd.setOnClickListener(view -> handleCityAddition());
        btnRemove.setOnClickListener(view -> handleCityRemoval());
    }

    private void onCityItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentlySelectedPosition = position;
    }

    private void showInputContainer() {
        inputContainer.setVisibility(View.VISIBLE);
    }

    private void hideInputContainer() {
        inputContainer.setVisibility(View.GONE);
        inputField.setText("");
    }

    private void handleCityAddition() {
        String enteredCityName = inputField.getText().toString().trim();

        if (enteredCityName.length() > 0) {
            cityNames.add(enteredCityName);
            adapter.notifyDataSetChanged();
            hideInputContainer();
        }
    }

    private void handleCityRemoval() {
        if (currentlySelectedPosition >= 0 && currentlySelectedPosition < cityNames.size()) {
            cityNames.remove(currentlySelectedPosition);
            adapter.notifyDataSetChanged();
            currentlySelectedPosition = -1;
        }
    }
}