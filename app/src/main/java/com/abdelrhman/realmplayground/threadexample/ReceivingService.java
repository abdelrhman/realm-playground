package com.abdelrhman.realmplayground.threadexample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.abdelrhman.realmplayground.model.Person;

import io.realm.Realm;

/**
 * @auther abdelrhman on 16/04/17.
 */

public class ReceivingService extends IntentService {
    public ReceivingService() {
        super("ReceivingService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getExtras() != null) {
            String personId = intent.getStringExtra("person_id");
            Realm realm = Realm.getDefaultInstance();
            Person person = realm.where(Person.class).equalTo("id", personId).findFirst();
            final String output = person.toString();
            new Handler(getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Loaded Person from intent service: " + output, Toast.LENGTH_LONG).show();
                }
            });
            realm.close();
        }
    }
}
