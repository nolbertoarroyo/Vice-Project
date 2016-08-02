
//still working on main activity, 8/2 1:38

package package com.test.vice20;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        private void configureDrawer(ArrayList<Boolean> chkd) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarhere);
            setSupportActionBar(toolbar);
            if (getSupportActionBar()!= null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            List<OpenDrawer> drawerEntries = new ArrayList<>();

            drawerEntries.add(new DrawerContent(arrayhere[0]));
            drawerEntries.add(new Separator());
            drawerEntries.add(new Toggle(arrayhere[2]));
            drawerEntries.add(new Toggle(arrayhere[3]));
            drawerEntries.add(new Toggle(arrayhere[4]));
            drawerEntries.add(new Toggle(arrayhere[5]));


            DrawerFragment drawerFragment = (DrawerFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.drawerfrag);

        }
    }
}
