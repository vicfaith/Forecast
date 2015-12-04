package com.westpac.android.codingtest.fragments;

import android.app.AlertDialog;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.westpac.android.codingtest.loader.WeatherForecastLoader;
import com.westpac.android.codingtest.models.AsyncTaskResult;
import com.westpac.android.codingtest.models.WeatherForecast;

/**
 * Created by dkang on 3/12/15.
 */
public class WeatherFragment extends Fragment implements LoaderManager.LoaderCallbacks<AsyncTaskResult<WeatherForecast>>, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String BASE_URL = "https://api.forecast.io/forecast/";
    private static final String API_KEY = "59926891c212be1eb69cb852853cd850";

    private RecyclerView mRecyclerView;

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected Location mLastLocation;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            getForecast();
        } else {
            // create Location request
            mLocationRequest = LocationRequest.create();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(1000 * 60); // 1min
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (mLastLocation == null) {
            mLastLocation = location;
            getForecast();
        } else {
            mLastLocation = location;
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void getForecast() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("location", mLastLocation);
        getLoaderManager().initLoader(0, bundle, this);
        getLoaderManager().getLoader(0).startLoading();
    }

    private void showForecast(WeatherForecast forecast) {

    }

    private void showError(Throwable e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error").setMessage(e.getMessage()).setPositiveButton("Okay", null).show();
    }

    @Override
    public Loader<AsyncTaskResult<WeatherForecast>> onCreateLoader(int id, Bundle args) {
        Location location = args.getParcelable("location");
        String url = BASE_URL + API_KEY + "/" + location.getLatitude() + "," + location.getLongitude();
        return new WeatherForecastLoader(getActivity(), url);
    }

    @Override
    public void onLoadFinished(Loader<AsyncTaskResult<WeatherForecast>> loader, AsyncTaskResult<WeatherForecast> result) {
        if (result.getError() != null) {
            showError((result.getError()));
        } else {
            showForecast(result.getResult());
        }
    }

    @Override
    public void onLoaderReset(Loader<AsyncTaskResult<WeatherForecast>> loader) {

    }
}