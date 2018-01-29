package com.wiseman.cardealership.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2018-01-29.
 */

public class AdminHolder extends RecyclerView.ViewHolder {
    public TextView name,proceed,icon;
    public AdminHolder(View v)
    {
        super(v);
        name = (TextView)v.findViewById(R.id.admin_name);
        proceed = (TextView)v.findViewById(R.id.proceed);
        icon = (TextView)v.findViewById(R.id.admin_icon);
    }
}
