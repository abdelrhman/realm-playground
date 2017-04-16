package com.abdelrhman.realmplayground.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abdelrhman.realmplayground.R;
import com.abdelrhman.realmplayground.model.City;

import java.util.List;
import java.util.Locale;

/**
 * @auther abdelrhman on 16/04/17.
 */

public class CityAdapter extends BaseAdapter {


    private LayoutInflater inflater;

    private List<City> cities = null;

    public CityAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<City> details) {
        this.cities = details;
    }

    @Override
    public int getCount() {
        if (cities == null) {
            return 0;
        }
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        if (cities == null || cities.get(position) == null) {
            return null;
        }
        return cities.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent) {
        if (currentView == null) {
            currentView = inflater.inflate(R.layout.city_listitem, parent, false);
        }

        City city = cities.get(position);

        if (city != null) {
            ((TextView) currentView.findViewById(R.id.name)).setText(city.getName());
            ((TextView) currentView.findViewById(R.id.votes)).setText(String.format(Locale.US, "%d",city.getVotes()));
        }

        return currentView;
    }
}
