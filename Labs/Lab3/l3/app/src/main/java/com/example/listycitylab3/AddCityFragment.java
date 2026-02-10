package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    private EditText cityName;
    private EditText provinceName;
    private OnFragmentInteractionListener listener;
    private City cityToEdit; // storingg the city if we are editing

    // interface to talk to main activity
    public interface OnFragmentInteractionListener {
        void onOkPressed(City city); // adding new
        void onEditPressed(City city, String newName, String newProvince); // editing existing
    }

    // empty constructor
    public AddCityFragment() {}

    // constructor for editing ( pass the city object)
    public AddCityFragment(City city) {
        this.cityToEdit = city;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // inflate the layout
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_add_city, null);

        // find ids
        cityName = view.findViewById(R.id.city_name_edit_text);
        provinceName = view.findViewById(R.id.province_edit_text);

        // check if we are in "Edit Mode"
        if (cityToEdit != null) {
            // pre-fill the text boxes
            cityName.setText(cityToEdit.getCityName());
            provinceName.setText(cityToEdit.getProvinceName());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        return builder
                .setView(view)
                .setTitle(cityToEdit != null ? "Edit City" : "Add City") // can change title based on mode
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String city = cityName.getText().toString();
                        String province = provinceName.getText().toString();

                        // check if inputs are valid
                        if (!city.isEmpty() && !province.isEmpty()) {
                            if (cityToEdit != null) {
                                // edit mode
                                listener.onEditPressed(cityToEdit, city, province);
                            } else {
                                // add mode
                                listener.onOkPressed(new City(city, province));
                            }
                        }
                    }
                }).create();
    }
}