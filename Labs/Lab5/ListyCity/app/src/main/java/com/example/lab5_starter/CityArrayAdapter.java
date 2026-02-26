package com.example.lab5_starter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<City> {
    private final ArrayList<City> cities;
    private final Context context;

    public CityArrayAdapter(Context context, ArrayList<City> cities){
        super(context, 0, cities);
        this.cities = cities;
        this.context = context;
    }

    private static class ViewHolder {
        TextView cityNameView;
        TextView provinceView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        ViewHolder holder;
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_city, parent, false);
            holder = new ViewHolder();
            holder.cityNameView = view.findViewById(R.id.textCityName);
            holder.provinceView = view.findViewById(R.id.textCityProvince);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        City city = cities.get(position);
        holder.cityNameView.setText(city.getName());
        holder.provinceView.setText(city.getProvince());

        return view;
    }
}
