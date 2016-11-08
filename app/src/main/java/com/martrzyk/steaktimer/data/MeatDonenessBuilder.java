package com.martrzyk.steaktimer.data;

import android.content.Context;

import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.pojo.BaseItem;

import java.util.ArrayList;

/**
 * Created by Marek on 2016-08-20.
 */
public class MeatDonenessBuilder {

    private static MeatDonenessBuilder instance;
    public static MeatDonenessBuilder getInstance(Context context)
    {
        if(instance == null)
            instance = new MeatDonenessBuilder();

        instance.context = context;

        return instance;
    }

    private Context context;
    private ArrayList<BaseItem> items;
    public ArrayList<BaseItem> getMeatDoneness()
    {
        if(items == null)
            items = new ArrayList<>();
        else
            return items;

        items.add(new BaseItem(R.string.rare, context.getString(R.string.rare)) );
        items.add(new BaseItem(R.string.medium_rare, context.getString(R.string.medium_rare)) );
        items.add(new BaseItem(R.string.medium, context.getString(R.string.medium)) );
        items.add(new BaseItem(R.string.well_done, context.getString(R.string.well_done)) );

        return items;
    }
}
