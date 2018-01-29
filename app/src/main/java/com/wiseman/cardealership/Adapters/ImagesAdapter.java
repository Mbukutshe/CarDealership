package com.wiseman.cardealership.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.wiseman.cardealership.Holders.ImagesHolder;
import com.wiseman.cardealership.Objects.Item;
import com.wiseman.cardealership.R;

import java.util.List;

/**
 * Created by Wiseman on 2017-10-28.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesHolder>{
    List<Item> mDataset;
    Context context,c;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    Animation upAnim;
    ProgressDialog myProgressDialog;
    String url;
    Bitmap bitmap=null;
    public ImagesAdapter(Context context, List<Item> mDataset, RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, ProgressDialog myProgressDialog) {
        this.mDataset = mDataset;
        this.context=context;
        this.recyclerView = recyclerView;
        this.layoutManager = layoutManager;
        this.myProgressDialog = myProgressDialog;
    }
    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        c = parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_images, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ImagesHolder vh = new ImagesHolder(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(final ImagesHolder holder, final int position) {
        if (mDataset.get(position).getAttach().toString().equalsIgnoreCase("image")) {
            holder.message.setText(mDataset.get(position).getMessage());
            holder.author.setText(mDataset.get(position).getAuthor());
            holder.date.setText(mDataset.get(position).getDate());
            holder.subject.setText("" + mDataset.get(position).getSubject());
            holder.link.setText(mDataset.get(position).getLink());
            holder.filename.setText(mDataset.get(position).getFilename());

            Picasso.with(context).load(mDataset.get(position).getLink()).into(holder.video_thumbnail);

            holder.play.getBackground().setAlpha(150);
            upAnim = AnimationUtils.loadAnimation(context, R.anim.fromtop_translation);
            holder.itemView.clearAnimation();
            holder.itemView.startAnimation(upAnim);
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upAnim = AnimationUtils.loadAnimation(context, R.anim.alpha);
                    holder.play.startAnimation(upAnim);
                    ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                    if(isConnected)
                    {
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.parse(mDataset.get(position).getLink()) );
                        shareIntent.setType("image/jpeg");
                        context.startActivity(Intent.createChooser(shareIntent, "Share Via:"));
                    }
                    else
                    {
                        LayoutInflater infla = LayoutInflater.from(holder.itemView.getContext());
                        View layout =infla.inflate(R.layout.toast_container_layout,(ViewGroup)holder.itemView.findViewById(R.id.toast_layout));
                        TextView textview = (TextView)layout.findViewById(R.id.toast_message);
                        textview.setText("No Internet Connection!");
                        Toast toast = new Toast(context);
                        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(layout);
                        toast.show();
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
