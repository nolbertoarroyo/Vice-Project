package com.test.vice20.Activities;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.test.vice20.DataBaseHelper;
import com.test.vice20.Fragments.ArticleListFragment;
import com.test.vice20.Fragments.DetailsFragment;
import com.test.vice20.Interfaces.ItemClickedInterface;
import com.test.vice20.Interfaces.NewsServiceInterface;
import com.test.vice20.Models.Article;
import com.test.vice20.Models.ArticleNews;
import com.test.vice20.NotificationJobService;
import com.test.vice20.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemClickedInterface {

    public static String baseURL = "http://vice.com/";
    private DetailsFragment detailFragment;
    Article favoriteArticle;
    NewsServiceInterface newsServiceInterface;
    String favArticleId;
    DataBaseHelper helper;
    private static final int JOB_INFO = 13;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleIntent(getIntent());

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

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(MainActivity.baseURL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        newsServiceInterface = retrofit.create(NewsServiceInterface.class);
//
//        newsServiceInterface.getArticle(favArticleId).enqueue(new Callback<ArticleNews>() {
//            @Override
//            public void onResponse(Call<ArticleNews> call, Response<ArticleNews> response) {
//                favoriteArticle= response.body().getData().getArticle();
//            }
//
//            @Override
//            public void onFailure(Call<ArticleNews> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Article API call failed", Toast.LENGTH_SHORT).show();
//            }
//        });

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

        // Find searchManager and searchableInfo
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(getComponentName());

        // Associate searchable info with the SearchView
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchableInfo);

        // Return true to show menu, returning false will not show it.
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                return true;

            case R.id.action_share:

                return true;
            case R.id.action_favorite:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(MainActivity.baseURL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                newsServiceInterface = retrofit.create(NewsServiceInterface.class);

                newsServiceInterface.getArticle(favArticleId).enqueue(new Callback<ArticleNews>() {
                    @Override
                    public void onResponse(Call<ArticleNews> call, Response<ArticleNews> response) {
                        favoriteArticle= response.body().getData().getArticle();
                        helper = DataBaseHelper.getInstance(MainActivity.this);
                        helper.insertRowFavorities(favoriteArticle);
                        Toast.makeText(MainActivity.this,""+helper.getFavoritesList().getCount(),Toast.LENGTH_SHORT).show();
                        Log.i("hey","hhh");
                        item.setIcon(android.R.drawable.btn_star_big_on);
                    }

                    @Override
                    public void onFailure(Call<ArticleNews> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Article API call failed", Toast.LENGTH_SHORT).show();
                    }
                });

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
        favArticleId=selectedArticleID;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            FragmentManager fragmentManager = getSupportFragmentManager();
            ArticleListFragment listFragment = new ArticleListFragment();

            listFragment.setQuery(query);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, listFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
