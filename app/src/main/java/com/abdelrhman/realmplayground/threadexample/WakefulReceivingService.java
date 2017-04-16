package com.abdelrhman.realmplayground.threadexample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.abdelrhman.realmplayground.model.Person;

import io.realm.Realm;

/**
 * @auther abdelrhman on 16/04/17.
 */

public class WakefulReceivingService extends IntentService {
    public WakefulReceivingService() {
        super("WakefulReceivingService");
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
                    Toast.makeText(getApplicationContext(), "Loaded Person from broadcast-receiver->intent-service: " + output, Toast.LENGTH_LONG).show();
                }
            });
            realm.close();
        }

        // All the work is done, release the wake locks/etc
        WakefulBroadcastReceiver.completeWakefulIntent(intent);

    }
}
