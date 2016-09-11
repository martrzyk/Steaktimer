package com.madkro.steaktimer.pojo;

/**
 * Created by Marek on 2016-08-20.
 */
public class BaseItem {
    public long id;
    public String name;

    public BaseItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
