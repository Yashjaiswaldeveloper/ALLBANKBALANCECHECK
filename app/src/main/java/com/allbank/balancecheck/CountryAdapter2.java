package com.allbank.balancecheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CountryAdapter2 extends ArrayAdapter<CountryItem2> {

    public CountryAdapter2(Context context, ArrayList<CountryItem2> countryList2) {
        super(context, 0, countryList2);
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

        private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_item_dropdown, parent, false
            );
        }

            ImageView imageViewFlag = convertView.findViewById(R.id.flag);
            ImageView imageViewFlag2 = convertView.findViewById(R.id.symbo);
            TextView textViewName = convertView.findViewById(R.id.countryname);

        CountryItem2 currentItem2 = getItem(position);

        if (currentItem2 != null) {
            imageViewFlag.setImageResource(currentItem2.getCountryFlag2());
            imageViewFlag2.setImageResource(currentItem2.getCountryFlag3());
            textViewName.setText(currentItem2.getCountryName2());
        }

        return convertView;
    }
}
