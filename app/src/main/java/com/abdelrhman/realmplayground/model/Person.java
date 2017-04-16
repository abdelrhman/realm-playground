package com.abdelrhman.realmplayground.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

/**
 * @auther abdelrhman on 11/04/17.
 */

@Data
public class Person extends RealmObject {
    @PrimaryKey
    private String id;
    private String name;
    private int age;
    private Dog dog;
    // One-to-many relations is simply a RealmList of the objects which also subclass RealmObject
    private RealmList<Cat> cats;
    // You can instruct Realm to ignore a field and not persist it.
    @Ignore
    private int tempReference;

}
