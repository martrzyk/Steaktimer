package com.martrzyk.steaktimer.pojo;

import java.util.HashMap;

/**
 * Created by Marek on 2016-08-20.
 */
public class BaseValuedItem<K, V> extends BaseItem {
    public BaseValuedItem(int id, String name, String value)
    {
        super(id, name);
        this.value = value;
    }

    public BaseValuedItem(int id, String name, HashMap<K, V> value)
    {
        super(id, name);

        if(this.hashMap == null)
            this.hashMap = new HashMap<>();

        if(value != null)
            this.hashMap.putAll(value);
    }

    public String value;
    public HashMap<K, V> hashMap;
}
