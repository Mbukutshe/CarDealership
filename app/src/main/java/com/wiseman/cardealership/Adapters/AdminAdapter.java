package com.wiseman.cardealership.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiseman.cardealership.Holders.AdminHolder;
import com.wiseman.cardealership.Objects.Dashboard;
import com.wiseman.cardealership.R;

import java.util.List;

/**
 * Created by Wiseman on 2018-01-29.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminHolder> {
    List<Dashboard> mDataset;
    Context context,c;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    public AdminAdapter(Context context, List<Dashboard> mDataset, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager)
    {
        this.mDataset = mDataset;
        this.context=context;
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
    }
    @Override
    public AdminHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.administrator_fragment_items, parent, false);
        // set the view's size, margins, paddings and layout parameters

        AdminHolder vh = new AdminHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(AdminHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.icon.setBackgroundResource(mDataset.get(position).getIcon());
        if(position==1)
        {
            holder.proceed.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
