package com.westpac.android.codingtest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.westpac.android.codingtest.R;
import com.westpac.android.codingtest.fragments.WeatherFragment;

/**
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