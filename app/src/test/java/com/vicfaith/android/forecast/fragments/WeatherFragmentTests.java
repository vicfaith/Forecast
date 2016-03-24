package com.vicfaith.android.forecast.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.android.gms.common.ConnectionResult;
import com.vicfaith.android.forecast.BuildConfig;
import com.vicfaith.android.forecast.R;
import com.vicfaith.android.forecast.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.gms.ShadowGooglePlayServicesUtil;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = "src/main/AndroidManifest.xml")
public class WeatherFragmentTests {
    private MainActivity mActivity;

    @Before
    public void setUp() {
        ShadowGooglePlayServicesUtil.setIsGooglePlayServicesAvailable(ConnectionResult.SUCCESS);
        ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class).create();
        mActivity = activityController.get();
    }

    @Test
    public void testCreateMainActivity() throws Exception {
        assertTrue(mActivity != null);
    }

    @Test
    public void testWeatherFragment() {
        Fragment fragment = mActivity.getSupportFragmentManager().findFragmentById(R.id.container);
        assertNotNull(fragment);
    }

    @Test
    public void testShouldInflateMenu() {
        Menu menu = new MenuBuilder(mActivity);
        new MenuInflater(mActivity).inflate(R.menu.menu_main, menu);
        mActivity.onCreateOptionsMenu(menu);
        assertEquals("Refresh", menu.findItem(R.id.action_refresh).getTitle());
    }

    @Test
    public void testShouldClickRefresh() {
        Menu menu = new MenuBuilder(mActivity);
        new MenuInflater(mActivity).inflate(R.menu.menu_main, menu);
        mActivity.onCreateOptionsMenu(menu);
        ShadowActivity activity = shadowOf(mActivity);
        activity.clickMenuItem(R.id.action_refresh);
    }
}