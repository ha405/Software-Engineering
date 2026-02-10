package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * Adapter for displaying a list of cities.
 */
public class CityAdapter extends ArrayAdapter<City> {

    private final Context context;
    private final ArrayList<City> cities;

    // View Holder pattern to improve performance
    private static class ViewHolder {
        TextView cityName;
        TextView provinceName;
    }

    public CityAdapter(Context context, ArrayList<City> cities) {
        super(context, 0, cities);
        this.cities = cities;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.content, parent, false);
            holder = new ViewHolder();
            holder.cityName = view.findViewById(R.id.city_text);
            holder.provinceName = view.findViewById(R.id.province_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        City city = cities.get(position);

        if (city != null) {
            holder.cityName.setText(city.getName());
            holder.provinceName.setText(city.getProvince());
        }

        return view;
    }
}
