package com.martrzyk.steaktimer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.martrzyk.steaktimer.pojo.BaseItem;

import java.util.ArrayList;

/**
 *
 * Created by Marek on 2016-08-20.
 */
public class GenericSpinnerAdapter<T extends BaseItem> extends ArrayAdapter<T> {

    // Vars
    private LayoutInflater mInflater;
    public GenericSpinnerAdapter(Context context, ArrayList<T> items) {
        super(context, android.R.layout.simple_dropdown_item_1line, items);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textView.setText(getItem(position).name);

        return convertView;
    }

    class ViewHolder {

        TextView textView;
        private ViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(android.R.id.text1);
        }
    }

}
