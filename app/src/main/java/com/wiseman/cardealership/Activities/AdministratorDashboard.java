package com.wiseman.cardealership.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wiseman.cardealership.Adapters.AdminAdapter;
import com.wiseman.cardealership.Objects.Dashboard;
import com.wiseman.cardealership.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wiseman on 2018-01-29.
 */

public class AdministratorDashboard extends AppCompatActivity {
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    List<Dashboard>names;
    android.support.v4.app.FragmentManager fragmentManager;
    ActionBar actionBar;
    Fragment currentFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrator_fragment_layout);
        getSupportActionBar().setTitle("Administrator");
        getSupportActionBar().setIcon(R.drawable.admin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.admin_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        names = getAdmin();
        fragmentManager = getSupportFragmentManager();
        actionBar = getSupportActionBar();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        AdminAdapter adapter = new AdminAdapter(this,names,mRecyclerView,mLayoutManager,fragmentManager,actionBar);
        mRecyclerView.setAdapter(adapter);

    }
    private List<Dashboard> getAdmin()
    {
        List<Dashboard> names = new ArrayList<>();
        names.add(new Dashboard("Send Messages",R.drawable.send));
        names.add(new Dashboard("Chats",R.drawable.chat));
        names.add(new Dashboard("Upload",R.drawable.uploadvehicle));
        names.add(new Dashboard("New Group",R.drawable.newgroup));
        names.add(new Dashboard("Locate Employees",R.drawable.locate));
        return names;
    }

    @Override
    public void onBackPressed() {
        getSupportActionBar().setTitle("Administrator");
        super.onBackPressed();
    }
}
