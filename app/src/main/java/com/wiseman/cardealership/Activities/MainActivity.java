package com.wiseman.cardealership.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.wiseman.cardealership.Fragments.NewGroup;
import com.wiseman.cardealership.Fragments.Settings;
import com.wiseman.cardealership.Fragments.SignUp;
import com.wiseman.cardealership.Fragments.UploadVehicle;
import com.wiseman.cardealership.Fragments.Vehicles;
import com.wiseman.cardealership.Functions.Animation;
import com.wiseman.cardealership.Functions.Functions;
import com.wiseman.cardealership.Functions.InsertToDatabase;
import com.wiseman.cardealership.Functions.Visible;
import com.wiseman.cardealership.Location.MapsActivity;
import com.wiseman.cardealership.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ViewPager viewPager;
    FragmentManager fragmentManager;
    Functions functions;
    Visible visible;
    LinearLayout open_groups;
    EditText input_message,desc;
    RelativeLayout message,document;
    Animation animation;
    InsertToDatabase insertToDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        functions = new Functions(this);
        animation = new Animation(this);
        insertToDatabase = new InsertToDatabase(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        input_message = (EditText)findViewById(R.id.text_message);
        input_message.setOnKeyListener(functions);
        desc = (EditText)findViewById(R.id.text_description);
        functions.setDoneAction(input_message);
        functions.setDoneAction(desc);
        message = (RelativeLayout)findViewById(R.id.message);
        message.getBackground().setAlpha(150);
        document = (RelativeLayout)findViewById(R.id.document);
        document.getBackground().setAlpha(150);

        insertToDatabase.insertDeviceId(FirebaseInstanceId.getInstance().getToken());
        FirebaseMessaging.getInstance().subscribeToTopic("test");
        // setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.wheel).setText("Vehicles"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.messages).setText("Messages"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.document).setText("Documents"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.gallery).setText("Media"));
        tabLayout.setOnTabSelectedListener(functions);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset != 0)
                {

                }
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main,new Vehicles()).commit();
        functions.setFragmentManager(fragmentManager);

        open_groups = (LinearLayout)findViewById(R.id.open_groups);
        open_groups.setOnClickListener(functions);
        visible =new Visible();

}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else
            if(message.getVisibility()== View.VISIBLE)
            {
                visible.gone(message);
            }
            else
                if(document.getVisibility()== View.VISIBLE)
                {
                    visible.gone(document);
                }
                else
                    {
                        super.onBackPressed();
                    }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            functions.setDoneAction(input_message);
            visible.visible(message);

            visible.gone(document);

        } else if (id == R.id.nav_gallery) {
            functions.setDoneAction(desc);

            visible.visible(document);

            visible.gone(message);

        }
        else if (id == R.id.nav_vehicle) {
            fragmentManager.beginTransaction().replace(R.id.content_main,new UploadVehicle()).commit();

        }
        else if (id == R.id.nav_settings) {
            startActivity(new Intent(getApplicationContext(),Settings.class));

        }
        else if (id == R.id.nav_admin) {
            startActivity(new Intent(getApplicationContext(),AdministratorDashboard.class));

        }
        else if (id == R.id.nav_sign_up) {
            startActivity(new Intent(getApplicationContext(),SignUp.class));

        }
        else if (id == R.id.nav_media) {
            startActivity(new Intent(getApplicationContext(),UploadMedia.class));

        } else if (id == R.id.nav_new_group) {
            startActivity(new Intent(getApplicationContext(), NewGroup.class));
        } else if (id == R.id.nav_map) {
            //startActivity(new Intent(getApplicationContext(), MapsActivity.class));
            fragmentManager.beginTransaction().replace(R.id.content_main,new MapsActivity()).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
