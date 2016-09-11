package com.madkro.steaktimer;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.madkro.steaktimer.adapters.GenericSpinnerAdapter;
import com.madkro.steaktimer.data.MeatDonenessBuilder;
import com.madkro.steaktimer.data.MeatTypeBuilder;
import com.madkro.steaktimer.pojo.BaseItem;
import com.madkro.steaktimer.pojo.StaticValues;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadViews();
    }

    private void loadViews()
    {
        loadSpinners();
        loadButtons();
    }

    private Spinner mMeatType;
    private Spinner mMeatDoneness;

    private void loadSpinners()
    {
        mMeatType = (Spinner) getView().findViewById(R.id.meat_type);
        mMeatDoneness = (Spinner)getView().findViewById(R.id.meat_doneness);

        GenericSpinnerAdapter adapterForType = new GenericSpinnerAdapter<>(
                getActivity(),
                MeatTypeBuilder
                        .getInstance(getActivity())
                        .getMeatTypes());

        GenericSpinnerAdapter adapterForDoneness = new GenericSpinnerAdapter<>(
                getActivity(),
                MeatDonenessBuilder
                        .getInstance(getActivity())
                        .getMeatDoneness());

        mMeatType.setAdapter(adapterForType);
        mMeatDoneness.setAdapter(adapterForDoneness);
    }

    private void loadButtons()
    {
        TextView button = (TextView) getView().findViewById(R.id.start_cooking_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = FlowActivity.intentCreator(getActivity());
                intent.putExtra(StaticValues.intent_extra_meat_type, mMeatType.getSelectedItemId());
                intent.putExtra(StaticValues.intent_extra_meat_doneness, mMeatType.getSelectedItemId());
                getActivity().startActivity(intent);
            }
        });
    }

}
