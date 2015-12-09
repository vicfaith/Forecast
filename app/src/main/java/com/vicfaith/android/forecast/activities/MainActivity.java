package com.vicfaith.android.forecast.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vicfaith.android.forecast.R;
import com.vicfaith.android.forecast.fragments.WeatherFragment;

/**
 * ß
 * Created by dkang on 3/12/15.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WeatherFragment())
                    .commit();
        }
    }
}