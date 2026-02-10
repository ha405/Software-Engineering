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

public class ManageCityFragment extends DialogFragment {

    private static final String ARG_CITY = "city_arg";
    private static final String ARG_POSITION = "position_arg"; // New argument
    private EditText cityNameInput;
    private EditText provinceNameInput;
    private OnCityInteractionListener listener;
    private City cityToEdit;
    private int position = -1; // Default to -1 (invalid position)

    public interface OnCityInteractionListener {
        void onCityAdded(City city);
        void onCityEdited(City originalCity, String newName, String newProvince, int position);
    }

    public ManageCityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param city City to edit, or null to add a new city.
     * @param position Position of the city in the list (if editing), or -1 if adding.
     * @return A new instance of fragment ManageCityFragment.
     */
    public static ManageCityFragment newInstance(City city, int position) {
        ManageCityFragment fragment = new ManageCityFragment();
        Bundle args = new Bundle();
        if (city != null) {
            args.putSerializable(ARG_CITY, city);
            args.putInt(ARG_POSITION, position);
        }
        fragment.setArguments(args);
        return fragment;
    }

    // Overloaded for convenience (adding new city)
    public static ManageCityFragment newInstance(City city) {
        return newInstance(city, -1);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCityInteractionListener) {
            listener = (OnCityInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCityInteractionListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_manage_city, null);
        cityNameInput = view.findViewById(R.id.edit_text_city_name);
        provinceNameInput = view.findViewById(R.id.edit_text_province_name);

        String title = "Add City";
        if (getArguments() != null) {
            cityToEdit = (City) getArguments().getSerializable(ARG_CITY);
            position = getArguments().getInt(ARG_POSITION, -1);
            
            if (cityToEdit != null) {
                title = "Edit City";
                cityNameInput.setText(cityToEdit.getName());
                provinceNameInput.setText(cityToEdit.getProvince());
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle(title)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String city = cityNameInput.getText().toString();
                        String province = provinceNameInput.getText().toString();

                        if (city.isEmpty() || province.isEmpty()) {
                            // Ideally show error, but for now just ignore
                            return;
                        }

                        if (cityToEdit != null) {
                            listener.onCityEdited(cityToEdit, city, province, position);
                        } else {
                            listener.onCityAdded(new City(city, province));
                        }
                    }
                }).create();
    }
}
