package com.martrzyk.steaktimer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.martrzyk.steaktimer.flow.StepFrying;
import com.martrzyk.steaktimer.flow.StepResting;
import com.martrzyk.steaktimer.flow.StepRotate;
import com.martrzyk.steaktimer.flow.StepServing;
import com.martrzyk.steaktimer.flow.StepWarming;
import com.martrzyk.steaktimer.pojo.StaticValues;

/**
 * A placeholder fragment containing a simple view.
 */
public class FlowFragment extends Fragment {

    public FlowFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flow, container, false);
    }

    private StepsView mStepsView;
    private TextView mTimerView;
    private CookingFlow cookingSteps;
    private int mDoneness;
    private int mType;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadFromIntent();

        loadViews();
        loadSteps();
        setupViews();
    }

    private void loadFromIntent()
    {
        if(getActivity() == null || getActivity().getIntent() == null)
            return;

        mDoneness = getActivity().getIntent().getIntExtra(StaticValues.intent_extra_meat_doneness, -1);
        mType = getActivity().getIntent().getIntExtra(StaticValues.intent_extra_meat_type, -1);
    }

    private void loadViews() {
        if (getView() == null)
            return;

        mStepsView = (StepsView) getView().findViewById(R.id.steps_view);
        mTimerView = (TextView) getView().findViewById(R.id.timer_tv);
    }


    private void setupViews() {
        if (mStepsView == null || mTimerView == null)
            return;

        mStepsView.setLabels(cookingSteps.getStepsLabels(getActivity()))
                .setBarColorIndicator(ContextCompat.getColor(getActivity(), R.color.material_blue_grey_800))
                .setProgressColorIndicator(ContextCompat.getColor(getActivity(), R.color.orange))
                .setLabelColorIndicator(ContextCompat.getColor(getActivity(), R.color.orange))
                .setCompletedPosition(cookingSteps.getPosition())
                .drawView();

        mTimerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookingSteps.moveToNextStep();
                cookingSteps.executeStep(false);
            }
        });
    }

    public void loadSteps() {
        cookingSteps =
                new CookingFlow
                        .CookingFlowBuilder(getActivity())
                        .addSteps(
                                new StepWarming(),
                                new StepFrying(),
                                new StepRotate(),
                                new StepFrying(),
                                new StepResting(),
                                new StepServing())
                        .setOptions(mDoneness, mType)
                        .setStepView(mStepsView)
                        .setTimerTextView(mTimerView)
                        .setInformationTextView(null)
                        .build();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
