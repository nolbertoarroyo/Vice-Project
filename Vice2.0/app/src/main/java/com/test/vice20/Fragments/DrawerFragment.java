package com.test.vice20.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.vice20.Adapters.DrawerAdapter;
import com.test.vice20.Models.OpenDrawer;
import com.test.vice20.Models.Toggle;
import com.test.vice20.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sterlinggerritz on 8/1/16.
 */


//this fragment holds whats inside of each drawer
    // each drawer contains a category title (parent drawer is favorite articles selected by user)
// Subsequent drawers contain a category title (opens up list view when title is clicked)
// and a toggle switch - the toggle switch controls which categories are
    //displayed in the main feed by default



    public class DrawerFragment extends Fragment {

        private View view;
        private Toggle toggle;
        private RecyclerView recyclerView;
        private DrawerLayout drawerLayout;
        private DrawerAdapter drawerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.drawerfragment_aka_parentdrawers,container,false);
        return view;
    }
    @Override
    public void onPause() {

        // booleans are used to indicate whether the user hits the toggle to switch on or off a category
        //the booleans are converted to a string so that the toggle status info can be passed back to the main activity


            ToggleInterface toggleInterface = (ToggleInterface) getActivity();
            toggleInterface.setToggleChoice(createToggleChoiceString(isToggledArray));
            ArrayList<Boolean> toggleStatus = drawerAdapter.getIsToggledArray();


            super.onPause();

            //this interface enables the fragment to share preferences about
            //children item to be displayed to the main activity
            public interface ToggleInterface {
                void setToggleChoice(String toggleInterface);
            }

            //this is teh method that converts the boolean preferences into a string so that
            //it is in a format can be stored in saved preferences

        private String createToggleChoiceString(ArrayList<Boolean> isToggledArray);

        {
            String strToggleChoice = "";
            String[] strArrayCategories = getResources().getStringArray(R.array.categories);
            for (int i = 0; i < isToggledArray.size(); i++) {
                if (isToggledArray.get(i)) {
                    strToggleChoice = strToggleChoice + strArrayCategories[i] + ",";
                }
            }
            if (!strToggleChoice.equals("")) {
                strToggleChoice = strToggleChoice.substring(0, strToggleChoice.length() - 1);
            }
            return strToggleChoice;
        }


        //activates drawer

    public void activatesDrawer(DrawerLayout drawerLayout, final Toolbar toolbar, List<OpenDrawer> openDrawerList,
                           ArrayList<Boolean> toolbar) {
        drawerLayout = drawerLayout;
        toggle = new Toggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
    }

    //when door opens the children items (subcategories etc) are displayed in their special view
    @Override
    public void onDrawerOpened(View view) {
        super.onDrawerOpened(view);
    }

    //when the door closes a list of boolean toggle indicators gets saved to notification preferences.
    //preferences are stored so that the appropriate children items are updated accordingly in the main/home article feed
    @Override
    public void onDrawerClosed(View view) {
        ArrayList<Boolean> isToggledArray = drawerAdapter.getIsToggledArray();
        ToggleInterface toggleInterface = (ToggleInterface) getActivity();
       toggleInterface.setToggleChoice(createToggleChoiceString(isToggledArray));
        super.onDrawerClosed(view);


        drawerLayout.addDrawerListener(toggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                toggle.syncState();
            }
        });

        //move to separate method
        recyclerView = (RecyclerView) view.findViewById(R.id.drawerlist);
        drawerAdapter = new DrawerAdapter(getActivity(), openDrawerList, toolbar);
        recyclerView.setAdapter(drawerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
    }}




