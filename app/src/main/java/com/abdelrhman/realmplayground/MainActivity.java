package com.abdelrhman.realmplayground;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abdelrhman.realmplayground.model.Person;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private LinearLayout rootLayout;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();
        basicCRUD(realm);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");
//
//        // All writes must be wrapped in a transaction to facilitate safe multi threading
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                // Add a person
//                Person person = realm.createObject(Person.class);
//                person.setId(1);
//                person.setName("Young Person");
//                person.setAge(14);
//
//            }
//        });

        // Find the first person (no query conditions) and read a field
//        final Person person = realm.where(Person.class).findFirst();
//        showStatus(person.getName() + ":" + person.getAge());

//        // Update person in a transaction
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                person.setName("Senior Person");
//                person.setAge(99);
//                showStatus(person.getName() + " got older: " + person.getAge());
//            }
//        });

        // Delete all persons
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(Person.class);
//            }
//        });
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

}
