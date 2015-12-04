package com.westpac.android.codingtest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by dkang on 4/12/15.
 */
public abstract class BaseViewHolder<D> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(final D data);
}
