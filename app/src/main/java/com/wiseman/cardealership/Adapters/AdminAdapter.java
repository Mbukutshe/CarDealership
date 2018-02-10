package com.wiseman.cardealership.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiseman.cardealership.Activities.UploadMedia;
import com.wiseman.cardealership.Fragments.NewGroup;
import com.wiseman.cardealership.Fragments.UploadDocuments;
import com.wiseman.cardealership.Fragments.UploadVehicle;
import com.wiseman.cardealership.Fragments.UsersChat;
import com.wiseman.cardealership.Functions.Animation;
import com.wiseman.cardealership.Functions.Functions;
import com.wiseman.cardealership.Holders.AdminHolder;
import com.wiseman.cardealership.Location.MapsActivity;
import com.wiseman.cardealership.Objects.Dashboard;
import com.wiseman.cardealership.R;

import java.util.List;

/**
 * Created by Wiseman on 2018-01-29.
 */

public class AdminAdapter extends RecyclerView.Adapter<AdminHolder> implements View.OnClickListener{
    List<Dashboard> mDataset;
    Context context;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Animation anim;
    public static  int COUNT_DOWN=200;
    CountDownTimer countDownTimer;
    Functions functions;
    android.support.v4.app.FragmentManager fragmentManager;
    ActionBar actionBar;
    public AdminAdapter(Context context, List<Dashboard> mDataset, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, final android.support.v4.app.FragmentManager fragmentManager, final ActionBar actionBar)
    {
        this.mDataset = mDataset;
        this.context=context;
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.fragmentManager = fragmentManager;
        this.actionBar = actionBar;
        functions = new Functions(this.context);
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
    public void onBindViewHolder(final AdminHolder holder, final int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.icon.setBackgroundResource(mDataset.get(position).getIcon());
        holder.text_message.setOnKeyListener(functions);
        functions.setDoneAction(holder.text_message);
        switch (position)
        {
            case 0:
                holder.proceed.setVisibility(View.VISIBLE);
            break;
            case 2:
                holder.proceed.setVisibility(View.VISIBLE);
            break;
        }
        holder.car.setOnClickListener(this);
        holder.document.setOnClickListener(this);
        holder.media.setOnClickListener(this);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim = new Animation(context);
                switch (position)
                {
                    case 0:
                        if(holder.message.getVisibility()==View.VISIBLE)
                        {
                            anim.slideUp(holder.message);
                            countDownTimer = new CountDownTimer(COUNT_DOWN,16) {
                                @Override
                                public void onTick(long l) {

                                }
                                @Override
                                public void onFinish() {
                                    holder.message.setVisibility(View.GONE);
                                }
                            };
                            countDownTimer.start();
                            holder.proceed.setRotation(90);
                        }
                        else
                        {
                            holder.message.setVisibility(View.VISIBLE);
                            anim.slideDown(holder.message);
                            holder.proceed.setRotation(270);
                            functions.setDoneAction(holder.text_message);
                        }
                    break;
                    case 1:
                        actionBar.setTitle("Chats");
                        fragmentManager.beginTransaction().replace(R.id.admin_fragment,new UsersChat()).addToBackStack("Chats").commit();
                    break;
                    case 2:
                        if(holder.collapsing.getVisibility()==View.VISIBLE)
                        {
                            anim.slideUp(holder.collapsing);
                            countDownTimer = new CountDownTimer(COUNT_DOWN,20) {
                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    holder.collapsing.setVisibility(View.GONE);
                                }
                            };
                            countDownTimer.start();
                            holder.proceed.setRotation(90);
                        }
                        else
                        {
                            holder.collapsing.setVisibility(View.VISIBLE);
                            anim.slideDown(holder.collapsing);
                            holder.proceed.setRotation(270);
                        }
                    break;
                    case 3:
                        context.startActivity(new Intent(context, NewGroup.class));
                    break;
                    case 4:
                        context.startActivity(new Intent(context, MapsActivity.class));
                    break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.upload_car:
                actionBar.setTitle("New Car");
                fragmentManager.beginTransaction().replace(R.id.admin_fragment,new UploadVehicle()).addToBackStack("New Car").commit();
            break;
            case R.id.upload_document:
                actionBar.setTitle("New Document");
                fragmentManager.beginTransaction().replace(R.id.admin_fragment,new UploadDocuments()).addToBackStack("New Document").commit();
            break;
            case R.id.media:
               context.startActivity(new Intent(context, UploadMedia.class));
            break;
        }
    }
}
