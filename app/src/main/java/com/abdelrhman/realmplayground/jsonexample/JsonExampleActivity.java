package com.abdelrhman.realmplayground.jsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.abdelrhman.realmplayground.R;
import com.abdelrhman.realmplayground.gridview.CityAdapter;
import com.abdelrhman.realmplayground.model.City;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class JsonExampleActivity extends AppCompatActivity {


    private GridView mGridView;
    private CityAdapter mAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_example);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Load from file "cities.json" first time
        if(mAdapter == null) {
            List<City> cities = null;
            try {
                cities = loadCities();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //This is the GridView adapter
            mAdapter = new CityAdapter(this);
            mAdapter.setData(cities);

            //This is the GridView which will display the list of cities
            mGridView = (GridView) findViewById(R.id.cities_list);
            mGridView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mGridView.invalidate();
        }
    }



    public List<City> loadCities() throws IOException {

        loadJsonFromStream();
        loadJsonFromJsonObject();
        loadJsonFromString();

        return realm.where(City.class).findAll();
    }


    private void loadJsonFromStream() throws IOException {
        // Use streams if you are worried about the size of the JSON whether it was persisted on disk
        // or received from the network.
        InputStream stream = getAssets().open("cities.json");

        // Open a transaction to store items into the realm
        realm.beginTransaction();
        try {
            realm.createAllFromJson(City.class, stream);
            realm.commitTransaction();
        } catch (IOException e) {
            // Remember to cancel the transaction if anything goes wrong.
            realm.cancelTransaction();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private void loadJsonFromJsonObject() {
        Map<String, String> city = new HashMap<String, String>();
        city.put("name", "København");
        city.put("votes", "9");
        final JSONObject json = new JSONObject(city);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(City.class, json);
            }
        });
    }
    private void loadJsonFromString() {
        final String json = "{ name: \"Aarhus\", votes: 99 }";

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.createObjectFromJson(City.class, json);
            }
        });
    }

}
