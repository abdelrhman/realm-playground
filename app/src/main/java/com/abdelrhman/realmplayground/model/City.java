package com.abdelrhman.realmplayground.model;

import io.realm.RealmObject;
import lombok.Data;

/**
 * @auther abdelrhman on 16/04/17.
 */

@Data
public class City extends RealmObject {

    private String name;
    private int votes;
}
