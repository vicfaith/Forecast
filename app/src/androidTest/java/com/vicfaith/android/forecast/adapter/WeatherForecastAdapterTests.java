package com.vicfaith.android.forecast.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.vicfaith.android.forecast.R;
import com.vicfaith.android.forecast.activities.MainActivity;
import com.vicfaith.android.forecast.models.WeatherForecast;
import com.vicfaith.android.forecast.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkang on 5/12/15.
 */
public class WeatherForecastAdapterTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private WeatherForecast mForecast;

    public WeatherForecastAdapterTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mForecast = TestUtils.loadWeatherForecastFromAsset(getInstrumentation().getContext(), "sydney_forecast.json");
    }

    public void testPreConditions() {
        assertNotNull(mForecast);
    }

    public void testShouldBindDataToView() {
        WeatherForecastAdapter adapter = createAdapter(mForecast);
        View itemView = createView(adapter);

        TextView tvIcon = (TextView) itemView.findViewById(R.id.icon);
        assertEquals("B", tvIcon.getText());
        TextView tvTemperature = (TextView) itemView.findViewById(R.id.temperature);
        assertEquals("81°", tvTemperature.getText());
        TextView tvCurrentForecast = (TextView) itemView.findViewById(R.id.current_forecast);
        assertEquals("Clear. Feels like 80°", tvCurrentForecast.getText());
        TextView tvNextHourForecast = (TextView) itemView.findViewById(R.id.next_hour_forecast);
        assertEquals("Clear throughout the day.", tvNextHourForecast.getText());
        TextView tvNext24HourForecast = (TextView) itemView.findViewById(R.id.next_24_hour_forecast);
        assertEquals("Light rain on Wednesday through Saturday, with temperatures peaking at 97°F on Friday.", tvNext24HourForecast.getText());
    }

    private WeatherForecastAdapter createAdapter(WeatherForecast forecast) {
        List<WeatherForecastAdapter.IForecast> arrayList = new ArrayList<>();
        arrayList.add(new WeatherForecastAdapter.ForecastHeaderItem(forecast));

        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getActivity());
        adapter.addAll(arrayList);
        return adapter;
    }

    private View createView(WeatherForecastAdapter adapter) {
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        WeatherForecastAdapter.ForecastHeaderItem item = new WeatherForecastAdapter.ForecastHeaderItem(mForecast);
        BaseViewHolder headerViewHolder = adapter.createViewHolder(recyclerView, WeatherForecastAdapter.IForecast.ITEM_TYPE_HEADER_FORMAT);
        headerViewHolder.setData(item);
        return headerViewHolder.itemView;
    }
}
