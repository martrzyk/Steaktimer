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

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

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
                .meatDoneness(meatDoneness.getSelectedItem())
                .meatType(meatType.getSelectedItem())
                .withOptions(transitionActivityOptions.toBundle())
                .start();
    }
}
