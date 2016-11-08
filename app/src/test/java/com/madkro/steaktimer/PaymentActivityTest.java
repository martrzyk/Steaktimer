package com.madkro.steaktimer;

import android.view.View;
import android.widget.TextView;

import com.martrzyk.steaktimer.BuildConfig;
import com.martrzyk.steaktimer.flow.FlowActivity;
import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.flow.FlowActivity_;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PaymentActivityTest {
    private static final float TOTAL_PRICE = 15.0f;

    private FlowActivity mActivity;
    private TextView mTotalPrice;

    @Before
    public void setUp() {
        mActivity = Robolectric.buildActivity(FlowActivity.class)
                .withIntent(FlowActivity_.intent(RuntimeEnvironment.application).get())
                .create()
                .start()
                .resume()
                .visible()
                .get();

        mTotalPrice = (TextView) mActivity.findViewById(R.id.steps_view);
    }

    @Test
    public void shouldNotBeNull() {
        assertNotNull(mActivity);
    }

    @Test
    public void shouldHaveTotalPrice() {
        assertNotNull(mTotalPrice);
        assertEquals(View.VISIBLE, mTotalPrice.getVisibility());
        assertEquals(mActivity.getString(R.string.under_cut), mTotalPrice.getText());
    }
}