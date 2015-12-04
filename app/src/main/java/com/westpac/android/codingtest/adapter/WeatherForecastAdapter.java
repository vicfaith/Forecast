package com.westpac.android.codingtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.westpac.android.codingtest.R;
import com.westpac.android.codingtest.models.WeatherForecast;

/**
 * Created by dkang on 4/12/15.
 */
public class WeatherForecastAdapter extends BaseRecyclerAdapter<WeatherForecastAdapter.IForecast, BaseViewHolder> {

    public interface IForecast {
        int ITEM_TYPE_HEADER_FORMAT = 0;
        int ITEM_TYPE_SIMPLE_FORMAT = 1;

        int getItemType();
    }

    private Context mContext;

    public WeatherForecastAdapter(Context context) {
        mContext = context;
    }

    static class ForecastHeaderItem implements IForecast {

        @Override
        public int getItemType() {
            return IForecast.ITEM_TYPE_HEADER_FORMAT;
        }
    }

    static class ForecastSimpleItem implements IForecast {

        @Override
        public int getItemType() {
            return IForecast.ITEM_TYPE_SIMPLE_FORMAT;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder;

        switch (viewType) {
            case IForecast.ITEM_TYPE_HEADER_FORMAT: {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_header, parent, false);
                holder = new HeaderViewHolder(view);
                break;
            }
            case IForecast.ITEM_TYPE_SIMPLE_FORMAT: {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_simple, parent, false);
                holder = new SimpleViewHolder(view);
                break;
            }
            default:
                throw new IllegalStateException();
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case IForecast.ITEM_TYPE_HEADER_FORMAT:
                holder.setData(null);
                break;

            case IForecast.ITEM_TYPE_SIMPLE_FORMAT:
                holder.setData(null);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    static class HeaderViewHolder extends BaseViewHolder<WeatherForecast.CurrentlyForecast> {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(final WeatherForecast.CurrentlyForecast data) {

        }
    }

    static class SimpleViewHolder extends BaseViewHolder<WeatherForecast.Forecast> {

        public SimpleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(final WeatherForecast.Forecast data) {

        }
    }
}
