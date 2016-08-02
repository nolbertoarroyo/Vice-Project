package com.sterling.layouts;

/**
 * Created by sterlinggerritz on 8/1/16.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.sterling.layouts.startdrawer;
import com.sterling.layouts.draweradapter;




public class DrawerFragment extends Fragment {
    DrawerLayout myDrawerLayout;
    myDrawerRecyclerView.setHasFixedSize(true);

    Draweradapter myDrawerAdapter;
    View myFragmentView;
    ActionBarDrawerToggle myDrawerToggle;
     RecyclerView myDrawerRecyclerView;
    myDrawerRecyclerView = (RecyclerView) myFragmentView.findViewById(R.id.nav_list);
    myDrawerAdapter = new Draweradapter(getActivity(), DrawerList,booleanArrayList);
    myDrawerRecyclerView.setAdapter(myDrawerAdapter);
    myDrawerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myFragmentView = inflater.inflate(R.layout.drawer,container,false);
        return myFragmentView;
    }

    @Override
    public void onPause() {
        ArrayList<Boolean> chkit = myDrawerAdapter.getChkd();

        super.onPause();
    }


    public void theDrawer(DrawerLayout drawerLayout, final Toolbar toolbar, List<OpenDrawer> navDrawerEntryList,
                           ArrayList<Boolean> booleanArrayList){

        myDrawerLayout = drawerLayout;
        myDrawerToggle = new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar, R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                ArrayList<Boolean> chkd = myDrawerAdapter.getChkd();

                super.onDrawerClosed(drawerView);

            }
        };

        myDrawerLayout.addDrawerListener(myDrawerToggle);
        myDrawerLayout.post(new Runnable() {
            @Override
            public void run() {

                myDrawerToggle.syncState();
            }
        });



    }}



