package com.wiseman.cardealership.Functions;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.wiseman.cardealership.Activities.MainActivity;
import com.wiseman.cardealership.Fragments.Documents;
import com.wiseman.cardealership.Fragments.Gallery;
import com.wiseman.cardealership.Fragments.Groups;
import com.wiseman.cardealership.Fragments.Messages;
import com.wiseman.cardealership.Fragments.Vehicles;
import com.wiseman.cardealership.R;

/**
 * Created by Wiseman on 2017-10-12.
 */

public class Functions implements View.OnClickListener,TabLayout.OnTabSelectedListener,View.OnKeyListener{
    Context context;
    FragmentManager fragmentManager;
    static CardView group_options;
    InsertToDatabase insertToDatabase;
    EditText group_name;
    String group_users;
    public Functions(Context context)
    {
        this.context = context;
    }
    public void setFragmentManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    public void setDoneAction(EditText editText)
    {
        editText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
    }
    public void passGroupViews(EditText group_name,String group_users)
    {
        this.group_name = group_name;
        this.group_users = group_users;
    }
    @Override
    public void onClick(View view)
    {
        Visible visible = new Visible();
        Animation animation = new Animation(context);
        int id = view.getId();
        switch(id)
        {
            case R.id.btn_login:
                context.startActivity(new Intent(context, MainActivity.class));
             break;
            case R.id.open_groups:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Groups()).commit();
            break;
            case R.id.disclaimer_ok:
                animation.setAlphaAnimation(view);
                insertToDatabase = new InsertToDatabase(view.getContext());
                insertToDatabase.createGroup(group_name,group_users);
            break;
            case R.id.close_new_group:
                animation.setAlphaAnimation(view);
            break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition())
        {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Vehicles()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Messages()).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Documents()).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Gallery()).commit();
                break;
        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        switch (tab.getPosition())
        {
            case 0:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Vehicles()).commit();
                break;
            case 1:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Messages()).commit();
                break;
            case 2:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Documents()).commit();
                break;
            case 3:
                fragmentManager.beginTransaction().replace(R.id.content_main,new Gallery()).commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i == keyEvent.KEYCODE_ENTER){
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
            return true;
        }
      return false;
    }
}
