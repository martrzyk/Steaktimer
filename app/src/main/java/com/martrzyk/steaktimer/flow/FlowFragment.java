package com.martrzyk.steaktimer.flow;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.anton46.stepsview.StepsView;
import com.hanks.htextview.HTextView;
import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.flow.steps.Step;
import com.martrzyk.steaktimer.flow.steps.StepFrying;
import com.martrzyk.steaktimer.flow.steps.StepResting;
import com.martrzyk.steaktimer.flow.steps.StepRotate;
import com.martrzyk.steaktimer.flow.steps.StepServing;
import com.martrzyk.steaktimer.flow.steps.StepWarming;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import lombok.val;

@EFragment(R.layout.fragment_flow)
public class FlowFragment extends Fragment {

    @ViewById(R.id.steps_view)
    StepsView mStepsView;

    @ViewById(R.id.timer_tv)
    HTextView mTimerView;

    private CookingFlow cookingSteps;
    private long mDoneness;
    private long mType;

    @Click(R.id.timer_tv)
    void onTimeClick() {
        cookingSteps.moveToNextStep();
        cookingSteps.executeStep(true);
    }

    @AfterViews
    void loadFromIntent() {
        if (getActivity() == null || getActivity().getIntent() == null)
            return;

        mDoneness = getActivity().getIntent().getLongExtra(FlowActivity_.MEAT_DONENESS_EXTRA, -1);
        mType = getActivity().getIntent().getLongExtra(FlowActivity_.MEAT_TYPE_EXTRA, -1);
    }

    @AfterViews
    void setupSteps() {

        Step[] steps = {
                new StepWarming(),
                new StepFrying(),
                new StepRotate(),
                new StepFrying(),
                new StepResting(),
                new StepServing()
        };

        cookingSteps =
                CookingFlow
                        .builder()
                        .context(getActivity())
                        .steps(new ArrayList<>(Arrays.asList(steps)))
                        .donenessId(mDoneness)
                        .typeId(mType)
                        .stepView(mStepsView)
                        .timerTextView(mTimerView)
                        .otherTextView(null)
                        .build();
    }

    @AfterViews
    void setupStepsView() {
        if (mStepsView == null)
            return;

        mStepsView
                .setLabels(cookingSteps.getStepsLabels(getActivity()))
                .setBarColorIndicator(ContextCompat.getColor(getActivity(), R.color.material_blue_grey_800))
                .setProgressColorIndicator(ContextCompat.getColor(getActivity(), R.color.orange))
                .setLabelColorIndicator(ContextCompat.getColor(getActivity(), R.color.orange))
                .setCompletedPosition(cookingSteps.getPosition())
                .drawView();
    }

    @AfterViews
    void loadStartTextAnimation() {
        mTimerView.animateText(getString(R.string.start_cooking)); // animate
    }

    public void loadEndTextAnimation() {
        mTimerView.animateText(""); // animate
    }
}
