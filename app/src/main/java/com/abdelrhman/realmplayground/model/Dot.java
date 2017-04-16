package com.abdelrhman.realmplayground.model;

import io.realm.RealmObject;
import lombok.Data;

/**
 * @auther abdelrhman on 16/04/17.
 */

@Data
public class Dot extends RealmObject {

    private int x;
    private int y;
    private int color;
    private long timestamp;
}
