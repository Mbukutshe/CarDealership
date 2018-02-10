package com.wiseman.cardealership.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wiseman.cardealership.Objects.ClarityObject;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2018-02-08.
 */

public class ResponderHolder  extends RecyclerView.ViewHolder {
    public TextView time,message;
    public ResponderHolder(View v)
    {
        super(v);
        time = (TextView)v.findViewById(R.id.time);
        message = (TextView)v.findViewById(R.id.clarity_message);
    }
    public void bind(ClarityObject message) {
        time.setText(message.time);
        this.message.setText(message.message);
    }
}
