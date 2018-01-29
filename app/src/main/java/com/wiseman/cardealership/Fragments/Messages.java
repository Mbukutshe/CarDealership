package com.wiseman.cardealership.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wiseman.cardealership.Functions.Retrieve;
import com.wiseman.cardealership.Globals.Globals;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-12.
 */

public class Messages extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_messages,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.message_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager( view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), mLayoutManager.getOrientation()));
        Retrieve read = new Retrieve();
        read.fetch(view.getContext(),mRecyclerView,Globals.RETRIEVAL_MESSAGE_CONDITION);
        return view;
    }
}
