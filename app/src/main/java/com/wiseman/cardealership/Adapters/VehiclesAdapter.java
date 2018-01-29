package com.wiseman.cardealership.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.wiseman.cardealership.Activities.SingleVehicle;
import com.wiseman.cardealership.Holders.VehiclesHolder;
import com.wiseman.cardealership.Objects.VehicleObject;
import com.wiseman.cardealership.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Wiseman on 2018-01-25.
 */

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesHolder> {
    List<VehicleObject> mDataset;
    Context context,c;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Animation upAnim;
    ProgressDialog myProgressDialog;
    public VehiclesAdapter(Context context,List<VehicleObject> mDataset,RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager) {
        this.mDataset = mDataset;
        this.context=context;
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.myProgressDialog = myProgressDialog;
    }
    @Override
    public VehiclesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_vehicles, parent, false);
        // set the view's size, margins, paddings and layout parameters

        VehiclesHolder vh = new VehiclesHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final VehiclesHolder holder, final int position) {
        holder.video_thumbnail.setImageResource( mDataset.get(position).getImage());
        holder.subject.setText(mDataset.get(position).getName());
        holder.message.setText(mDataset.get(position).getFeaturers());
        holder.video_duration.setText(mDataset.get(position).getPrice());
        holder.video_footer.getBackground().setAlpha(180);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), mDataset.get(position).getImage());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                Intent intent = new Intent(context, SingleVehicle.class);
                intent.putExtra("vehicle",b);
                intent.putExtra("name",mDataset.get(position).getName());
                intent.putExtra("price",mDataset.get(position).getPrice());
                intent.putExtra("features",mDataset.get(position).getFeaturers());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    private Bitmap convertImageViewToBitmap(ImageView v){

        Bitmap bm=((BitmapDrawable)v.getDrawable()).getBitmap();

        return bm;
    }
}
