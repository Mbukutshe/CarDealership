package com.wiseman.cardealership.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiseman.cardealership.Functions.Retrieve;
import com.wiseman.cardealership.Globals.Globals;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-12.
 */

public class Documents extends Fragment{
    View view;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_documents,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.document_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        Configuration config = getResources().getConfiguration();
        if((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL)
        {
            mLayoutManager = new LinearLayoutManager(this.getContext());
        }
        else
        {
            mLayoutManager = new StaggeredGridLayoutManager(2,1);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);
        Retrieve read = new Retrieve();
        read.fetch(view.getContext(),mRecyclerView,Globals.RETRIEVAL_DOCUMENT_CONDITION);
        return view;
    }
}
