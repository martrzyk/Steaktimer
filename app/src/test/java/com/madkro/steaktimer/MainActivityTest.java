package com.madkro.steaktimer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.martrzyk.steaktimer.BuildConfig;
import com.martrzyk.steaktimer.FlowActivity;
import com.martrzyk.steaktimer.FlowFragment;
import com.martrzyk.steaktimer.MainActivity;
import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.TestActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {

    private MainActivity mActivity;
    private Context mContext;
    private Spinner meat_doneness, meat_type;
    private TextView start_cooking_button;


    @Before
    public void setUp() {
        mContext = Mockito.mock(Context.class);
        mActivity = Robolectric.setupActivity(MainActivity.class);

        meat_doneness = (Spinner) mActivity.findViewById(R.id.meat_doneness);
        meat_type = (Spinner) mActivity.findViewById(R.id.meat_type);
        start_cooking_button = (TextView) mActivity.findViewById(R.id.start_cooking_button);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mActivity);
    }

    @Test
    public void shouldHaveStartButton() {
        assertNotNull(start_cooking_button);
        assertEquals(View.VISIBLE, start_cooking_button.getVisibility());
        assertEquals(mActivity.getString(R.string.start_cooking), start_cooking_button.getText());
    }

    @Test
    public void shouldHaveSpinners() {
        assertNotNull(meat_doneness);
        assertEquals(View.VISIBLE, meat_doneness.getVisibility());

        assertNotNull(meat_type);
        assertEquals(View.VISIBLE, meat_type.getVisibility());
    }

    @Test
    public void shouldHaveDataInSpinners() {
        assertNotNull(meat_doneness);
        assertNotNull(meat_type);
//        assertEquals(mActivity.getString(R.string.default_coffee_count), mCoffeeCount.getText());
    }

    @Test
    public void shouldBeCreatedIntent() {
        final float TEST_PRICE = 12.0f;

        Intent intent = FlowActivity.intentCreator(mContext);
        assertNotNull(intent);
        Bundle bundle = intent.getExtras();
//        assertEquals(TEST_PRICE, bundle.getFloat(PaymentActivity.TOTAL_PRICE), 0.00001f);
    }

    @Test
    public void shouldStartActivityOnPayButton() {
        assertNotNull(start_cooking_button);
        start_cooking_button.performClick();

        ShadowActivity shadowActivity = shadowOf(mActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
//        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertEquals(FlowActivity.class.getName(),
                startedIntent.getComponent().getClassName());
    }
}