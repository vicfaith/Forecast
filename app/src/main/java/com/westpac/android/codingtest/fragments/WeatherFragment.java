package com.westpac.android.codingtest.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.westpac.android.codingtest.R;
import com.westpac.android.codingtest.adapter.WeatherForecastAdapter;
import com.westpac.android.codingtest.loader.WeatherForecastLoader;
import com.westpac.android.codingtest.models.AsyncTaskResult;
import com.westpac.android.codingtest.models.WeatherForecast;
import com.westpac.android.codingtest.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkang on 3/12/15.
 */
public class WeatherFragment extends Fragment implements LoaderManager.LoaderCallbacks<AsyncTaskResult<WeatherForecast>>,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback {
    private static final String BASE_URL = "https://api.forecast.io/forecast/";
    private static final String API_KEY = "59926891c212be1eb69cb852853cd850";
    private static final int REQUEST_LOCATION = 0;

    private RecyclerView mRecyclerView;
    private ContentLoadingProgressBar mProgressBar;
    private WeatherForecastAdapter mAdapter;

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected Location mLastLocation;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Snackbar.make(getView(), getString(R.string.permission_locaation_rationale),
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Okay", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        }
                    })
                    .show();
        } else {
            // Location permission has not been granted yet. Request it directly.
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            // Check if the only required permission has been granted
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                        .addApi(LocationServices.API)
                        .addConnectionCallbacks(this)
                        .addOnConnectionFailedListener(this)
                        .build();
                mGoogleApiClient.connect();
            } else {
                Snackbar.make(getView(), getString(R.string.permission_not_granted), Snackbar.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        setupViews(view);

        if (!PermissionUtils.hasLocationPermission(getActivity())) {
            requestLocationPermission();
        } else {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    protected void setupViews(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recylerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new WeatherForecastAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mProgressBar = (ContentLoadingProgressBar) rootView.findViewById(R.id.progressBar);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            reloadForecast();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            loadForecast();
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
            loadForecast();
        } else {
            mLastLocation = location;
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void loadForecast() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("location", mLastLocation);
        getLoaderManager().initLoader(0, bundle, this);
        getLoaderManager().getLoader(0).startLoading();
    }

    private void reloadForecast() {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();

        if (mLastLocation != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("location", mLastLocation);
            getLoaderManager().restartLoader(0, bundle, this);
        } else {
            requestLocationPermission();
        }
    }

    private void showForecast(WeatherForecast forecast) {
        List<WeatherForecastAdapter.IForecast> arrayList = new ArrayList<>();
        if (forecast != null) {
            arrayList.add(new WeatherForecastAdapter.ForecastHeaderItem(forecast));
        }

        // just show daily forecast for this code test app
        for (WeatherForecast.Forecast item : forecast.getDaily().getData()) {
            arrayList.add(new WeatherForecastAdapter.ForecastSimpleItem(item));
        }
        mAdapter.clear();
        mAdapter.addAll(arrayList);
    }

    private void showError(Throwable e) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error").setMessage(e.getMessage()).setPositiveButton("Okay", null).show();
    }

    @Override
    public Loader<AsyncTaskResult<WeatherForecast>> onCreateLoader(int id, Bundle args) {
        showProgressBar();
        Location location = args.getParcelable("location");
        String url = BASE_URL + API_KEY + "/" + location.getLatitude() + "," + location.getLongitude();
        Log.i(WeatherFragment.class.getSimpleName(), url);
        return new WeatherForecastLoader(getActivity(), url);
    }

    @Override
    public void onLoadFinished(Loader<AsyncTaskResult<WeatherForecast>> loader, AsyncTaskResult<WeatherForecast> result) {
        hideProgressBar();

        if (result.getError() != null) {
            showError((result.getError()));
        } else {
            showForecast(result.getResult());
        }
    }

    @Override
    public void onLoaderReset(Loader<AsyncTaskResult<WeatherForecast>> loader) {

    }

    private void showProgressBar() {
        mProgressBar.show();
    }

    private void hideProgressBar() {
        mProgressBar.hide();
    }
}