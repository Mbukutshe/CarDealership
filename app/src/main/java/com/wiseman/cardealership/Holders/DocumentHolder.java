package com.wiseman.cardealership.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-15.
 */

public class DocumentHolder extends RecyclerView.ViewHolder {

    public TextView messageId,message,subject,link,author,date,urgent,filename;
    public RelativeLayout card;
    public FrameLayout document_download;
    public DocumentHolder(View tv) {
        super(tv);
        message = (TextView)tv.findViewById(R.id.message_message);
        author = (TextView)tv.findViewById(R.id.author_message);
        date = (TextView)tv.findViewById(R.id.time_message);
        messageId = (TextView)tv.findViewById(R.id.messageid);
        urgent = (TextView)tv.findViewById(R.id.urgent);
        subject =(TextView)tv.findViewById(R.id.subject_message);
        card = (RelativeLayout) tv.findViewById(R.id.card_view_message);
        link = (TextView)tv.findViewById(R.id.link);
        filename = (TextView)tv.findViewById(R.id.filename);
        document_download = (FrameLayout)tv.findViewById(R.id.play_document_layout);

    }
}