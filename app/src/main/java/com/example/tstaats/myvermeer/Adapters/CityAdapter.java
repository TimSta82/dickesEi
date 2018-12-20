package com.example.tstaats.myvermeer.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.City;
import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<City> {

    public CityAdapter(Context context, ArrayList<City> cityList){
        super(context, 0, cityList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);

    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.country_spinner_row, parent, false
            );
        }
        ImageView imageViewFlag = convertView.findViewById(R.id.image_flag);
        TextView cityTv = convertView.findViewById(R.id.text_city);
        TextView countryTv = convertView.findViewById(R.id.text_country);

        City currentCity = getItem(position);

        if (currentCity != null){
            imageViewFlag.setImageResource(currentCity.getCountryFlag());
            cityTv.setText(currentCity.getCityName());
            countryTv.setText(currentCity.getCountryName());
        }

        return convertView;
    }
}
