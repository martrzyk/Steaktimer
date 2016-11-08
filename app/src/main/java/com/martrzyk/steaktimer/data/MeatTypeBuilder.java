package com.martrzyk.steaktimer.data;

import android.content.Context;

import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.pojo.BaseItem;
import com.martrzyk.steaktimer.pojo.BaseValuedItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Created by Marek on 2016-08-20.
 */
public class MeatTypeBuilder {
    private static MeatTypeBuilder instance;
    public static MeatTypeBuilder getInstance(Context context)
    {
        if(instance == null)
            instance = new MeatTypeBuilder();

        instance.context = context;

        return instance;
    }

    private HashMap<Integer, Integer> resultMap;
    public Context context;
    public ArrayList<BaseItem> getMeatTypes()
    {
        ArrayList<BaseItem> items = new ArrayList<>();
        HashMap<Integer, Integer> timings = getTimings();

        //Types of meat + timing for every one of them
        items.add(new BaseItem(R.id.empty_value, context.getString(R.string.empty)));
        items.add(new BaseValuedItem<>(R.string.rump_cut, context.getString(R.string.rump_cut), timings));
        items.add(new BaseValuedItem<>(R.string.brisket, context.getString(R.string.brisket), timings));
        items.add(new BaseValuedItem<>(R.string.under_cut, context.getString(R.string.under_cut), timings));

        return items;
    }


    public HashMap<Integer,Integer> getTimings()
    {
        resultMap = new HashMap<>();

        //Creating items, where for every 1cm of thickness we add 1minute per side of time
        resultMap.put(R.string.brisket, 60);
        resultMap.put(R.string.rump_cut, 60);
        resultMap.put(R.string.under_cut, 60);

        return resultMap;
    }
}
