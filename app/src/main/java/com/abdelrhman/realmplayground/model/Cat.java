package com.abdelrhman.realmplayground.model;

import io.realm.RealmObject;
import lombok.Data;

/**
 * @auther abdelrhman on 11/04/17.
 */

@Data
public class Cat extends RealmObject {
    private String name;

}
