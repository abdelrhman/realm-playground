package com.abdelrhman.realmplayground.realmadapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.abdelrhman.realmplayground.R;
import com.abdelrhman.realmplayground.realmadapters.ui.listview.ListViewExampleActivity;
import com.abdelrhman.realmplayground.realmadapters.ui.recyclerview.RecyclerViewExampleActivity;


public class RealmAdaptersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_realm_adapters);

        setupButton(R.id.button_listview, ListViewExampleActivity.class);
        setupButton(R.id.button_recyclerview, RecyclerViewExampleActivity.class);
    }

    void startActivity(Class<? extends Activity> activityClass) {
        startActivity(new Intent(this, activityClass));
    }

    private void setupButton(int id, final Class<? extends Activity> activityClass) {
        findViewById(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activityClass);
            }
        });
    }
}
