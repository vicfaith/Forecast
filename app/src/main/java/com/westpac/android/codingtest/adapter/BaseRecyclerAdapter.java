package com.westpac.android.codingtest.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkang on 4/12/15.
 */
public abstract class BaseRecyclerAdapter<M, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<M> mItems = new LinkedList<>();

    public BaseRecyclerAdapter() {
        setHasStableIds(true);
    }

    public void add(M object) {
        mItems.add(object);
    }

    public void add(int index, M object) {
        mItems.add(index, object);
    }

    public void addAll(Collection<? extends M> collection) {
        if (collection != null) {
            mItems.addAll(collection);
        }
    }

    public void addAll(M... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void remove(M object) {
        mItems.remove(object);
    }

    public void removeItem(int position) {
        mItems.remove(position);
    }

    public List<M> getItems() {
        return mItems;
    }

    public M getItem(int position) {
        return mItems.get(position);
    }

    public boolean isEmpty() {
        return mItems.size() == 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}