package com.example.lab5_starter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class CityDialogFragment extends DialogFragment {
    
    interface CityDialogListener {
        void updateCity(City city, String cityName, String province);
        void addCity(City city);
    }

    private CityDialogListener listener;
    private EditText editCityName;
    private EditText editProvince;

    public static CityDialogFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        CityDialogFragment fragment = new CityDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CityDialogListener) {
            listener = (CityDialogListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_city_details, null);
        editCityName = view.findViewById(R.id.edit_city_name);
        editProvince = view.findViewById(R.id.edit_province);

        Bundle arguments = getArguments();
        City existingCity = (arguments != null) ? (City) arguments.getSerializable("city") : null;

        if (existingCity != null) {
            populateFields(existingCity);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        return builder
                .setView(view)
                .setTitle(existingCity == null ? "Add City" : "Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm", (dialog, which) -> handleConfirmAction(existingCity))
                .create();
    }

    private void populateFields(City city) {
        editCityName.setText(city.getName());
        editProvince.setText(city.getProvince());
    }

    private void handleConfirmAction(City existingCity) {
        String name = editCityName.getText().toString();
        String province = editProvince.getText().toString();

        if (existingCity != null) {
            listener.updateCity(existingCity, name, province);
        } else {
            listener.addCity(new City(name, province));
        }
    }
}
