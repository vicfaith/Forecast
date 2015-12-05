package com.westpac.android.codingtest.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.westpac.android.codingtest.R;
import com.westpac.android.codingtest.models.WeatherForecast;
import com.westpac.android.codingtest.utils.DateUtils;
import com.westpac.android.codingtest.utils.DegreeFormatter;
import com.westpac.android.codingtest.utils.MeteoconsConverter;

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

    public static class ForecastHeaderItem implements IForecast {
        WeatherForecast mForecast;

        public ForecastHeaderItem(WeatherForecast forecast) {
            this.mForecast = forecast;
        }

        @Override
        public int getItemType() {
            return IForecast.ITEM_TYPE_HEADER_FORMAT;
        }
    }

    public static class ForecastSimpleItem implements IForecast {
        WeatherForecast.Forecast mForecast;

        public ForecastSimpleItem(WeatherForecast.Forecast forecast) {
            this.mForecast = forecast;
        }

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
                holder.setData(getItem(position));
                break;

            case IForecast.ITEM_TYPE_SIMPLE_FORMAT:
                holder.setData(getItem(position));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getItemType();
    }

    static class HeaderViewHolder extends BaseViewHolder<IForecast> {
        TextView tvIcon;
        TextView tvTemperature;
        TextView tvCurrentForecast;
        TextView tvNextHourForecast;
        TextView tvNext24HourForecast;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            tvIcon = (TextView) itemView.findViewById(R.id.icon);
            tvTemperature = (TextView) itemView.findViewById(R.id.temperature);
            tvCurrentForecast = (TextView) itemView.findViewById(R.id.current_forecast);
            tvNextHourForecast = (TextView) itemView.findViewById(R.id.next_hour_forecast);
            tvNext24HourForecast = (TextView) itemView.findViewById(R.id.next_24_hour_forecast);
        }

        @Override
        public void setData(final IForecast data) {
            WeatherForecast forecast = ((ForecastHeaderItem) data).mForecast;
            Typeface meteocons = Typeface.createFromAsset(itemView.getContext().getAssets(), "meteocons.ttf");
            tvIcon.setTypeface(meteocons);
            tvIcon.setText(MeteoconsConverter.from(forecast.getCurrently().getIcon()));

            tvTemperature.setText(DegreeFormatter.getDegree(forecast.getCurrently().getTemperature()));
            String description = forecast.getCurrently().getSummary() + ". Feels like " + DegreeFormatter.getDegree(forecast.getCurrently().getApparentTemperature());
            tvCurrentForecast.setText(description);
            tvNextHourForecast.setText(forecast.getHourly().getSummary());
            tvNext24HourForecast.setText(forecast.getDaily().getSummary());
        }
    }

    static class SimpleViewHolder extends BaseViewHolder<IForecast> {
        TextView tvIcon;
        TextView tvDate;
        TextView tvForecast;

        public SimpleViewHolder(View itemView) {
            super(itemView);

            tvIcon = (TextView) itemView.findViewById(R.id.icon);
            tvDate = (TextView) itemView.findViewById(R.id.date);
            tvForecast = (TextView) itemView.findViewById(R.id.forecast);
        }

        @Override
        public void setData(final IForecast data) {
            WeatherForecast.Forecast forecast = ((ForecastSimpleItem) data).mForecast;
            Typeface meteocons = Typeface.createFromAsset(itemView.getContext().getAssets(), "meteocons.ttf");
            tvIcon.setTypeface(meteocons);
            tvIcon.setText(MeteoconsConverter.from(forecast.getIcon()));

            tvDate.setText(DateUtils.getDateFromTime(forecast.getTime()).toUpperCase());
            tvForecast.setText(forecast.getSummary());
        }
    }
}
