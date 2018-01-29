package com.wiseman.cardealership.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wiseman.cardealership.Functions.Visible;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-30.
 */

public class UploadMedia extends AppCompatActivity implements View.OnClickListener{
    LinearLayout upload_Image,layout_choose;
    FrameLayout layout_Caption;
    RelativeLayout upload_background;
    ImageView upload_image_view;
    EditText caption;
    FloatingActionButton fab_video,fab_image,fab_send;
    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMAGE=1;
    private static final int RESULT_LOAD_VIDEO=2;
    Visible visible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_media_layout);
        caption = (EditText)findViewById(R.id.title_image);
        upload_background = (RelativeLayout)findViewById(R.id.upload_background);
        upload_Image = (LinearLayout)findViewById(R.id.layout_image);
        layout_choose = (LinearLayout)findViewById(R.id.layout_choose);
        layout_Caption = (FrameLayout)findViewById(R.id.layout_caption);
        upload_image_view = (ImageView)findViewById(R.id.upload_image_view);
        fab_image = (FloatingActionButton)findViewById(R.id.fab_image);
        fab_video = (FloatingActionButton)findViewById(R.id.fab_video);
        fab_send = (FloatingActionButton)findViewById(R.id.fab_send);
        fab_image.setOnClickListener(this);
        fab_video.setOnClickListener(this);
        visible = new Visible();
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch(view.getId())
        {
            case R.id.fab_image:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,RESULT_LOAD_IMAGE);
            break;
            case R.id.fab_video:
                intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Choose a Video"), 2);
            break;
            case R.id.fab_send:

            break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK )
        {
            Uri photo =  data.getData();
            visible.gone(layout_choose);
            visible.visible(upload_Image);
            visible.visible(layout_Caption);
            upload_background.setBackgroundColor(Color.BLACK);
            upload_background.getBackground().setAlpha(150);
            upload_image_view.setDrawingCacheEnabled(true);
            upload_image_view.buildDrawingCache();
            upload_image_view.setImageURI(photo);
            /*
            Bitmap bitmap= ((BitmapDrawable) upload_image_view.getDrawable()).getBitmap();
            bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);*/
        }
        else
            if(requestCode == RESULT_LOAD_VIDEO && resultCode == RESULT_OK )
            {
                Uri photo =  data.getData();
                visible.gone(layout_choose);
                visible.visible(upload_Image);
                visible.visible(layout_Caption);
                upload_background.setBackgroundColor(Color.BLACK);
                upload_background.getBackground().setAlpha(150);
                upload_image_view.setDrawingCacheEnabled(true);
                upload_image_view.buildDrawingCache();
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(getPath(photo), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
                upload_image_view.setImageBitmap(bitmap);
                /*
                Bitmap bitmap= ((BitmapDrawable) upload_image_view.getDrawable()).getBitmap();
                bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);*/
            }

    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}
