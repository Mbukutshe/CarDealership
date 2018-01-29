package com.wiseman.cardealership.Functions;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-17.
 */
public class Animation {
    Context context;
    android.view.animation.Animation animation;
    public Animation(Context context)
    {
        this.context = context;
    }
    public void setAlphaAnimation(View view)
    {
        animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
        view.startAnimation(animation);
    }
    public void setZoomInAnimation(View view)
    {
        animation = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        view.startAnimation(animation);
    }
    public void setZoomOutAnimation(View view)
    {
        animation = AnimationUtils.loadAnimation(context, R.anim.zoom_out);
        view.startAnimation(animation);
    }
    public void setFadeInAnimation(View view)
    {
        animation = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        view.startAnimation(animation);
    }
    public void setFadeOutAnimation(View view)
    {
        animation = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        view.startAnimation(animation);
    }
}
