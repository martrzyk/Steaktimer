package com.martrzyk.steaktimer.main;

import android.app.ActivityOptions;
import android.support.v4.app.Fragment;
import android.view.View;

import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.flow.FlowActivity_;
import com.wefika.horizontalpicker.HorizontalPicker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {
    HashMap<Integer, Long> donenessMap, typeMap;

    @AfterViews
    void loadArrays() {
        int[] donenessArray = new int[]{
                R.string.rare,
                R.string.medium_rare,
                R.string.medium,
                R.string.well_done,
                R.string.test
        };

        int[] typeArray = new int[]{
                R.string.rump_cut,
                R.string.under_cut,
                R.string.brisket,
        };

        donenessMap = new HashMap<>();
        typeMap = new HashMap<>();

        for (int i = 0; i < donenessArray.length; i++) {
            donenessMap.put(i, (long) donenessArray[i]);
        }

        for (int i = 0; i < typeArray.length; i++) {
            typeMap.put(i, (long) typeArray[i]);
        }
    }

    @ViewById(R.id.meat_type)
    HorizontalPicker meatType;

    @ViewById(R.id.meat_doneness)
    HorizontalPicker meatDoneness;

    @Click(R.id.start_cooking_button)
    void startCookingClicked(View startCookingView) {
        String transitionName = getString(R.string.start_cooking);
        ActivityOptions transitionActivityOptions =
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), startCookingView, transitionName);

        FlowActivity_
                .intent(getActivity())
                .meatDoneness(donenessMap.get(meatDoneness.getSelectedItem()))
                .meatType(typeMap.get(meatType.getSelectedItem()))
                .withOptions(transitionActivityOptions.toBundle())
                .start();
    }
}
