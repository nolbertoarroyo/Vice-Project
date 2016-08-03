package com.test.vice20.Activities;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.test.vice20.Fragments.ArticleListFragment;
import com.test.vice20.Fragments.DetailsFragment;
import com.test.vice20.Interfaces.ItemClickedInterface;
import com.test.vice20.NotificationJobService;
import com.test.vice20.R;

public class MainActivity extends AppCompatActivity implements ItemClickedInterface {

    public static String baseURL = "http://vice.com/";
    private DetailsFragment detailFragment;
    private static final int JOB_INFO = 13;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // create a new fragment
        ArticleListFragment fragment = new ArticleListFragment();

        // add fragment to the container ( there is nothing there yet, that is why we add )
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //schedule article updates evey 5 secs (for testing purposes)
        JobInfo jobInfo = new JobInfo.Builder(JOB_INFO,
                new ComponentName(getPackageName(),
                        NotificationJobService.class.getName()))
                .setPersisted(true)
                .setPeriodic(5000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;

            case R.id.action_share:

                return true;
            case R.id.action_favorite:

                return true;
            case R.id.search:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClicked(String selectedArticleID) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(detailFragment == null){
            detailFragment = new DetailsFragment();
        }
        detailFragment.setId(selectedArticleID);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
