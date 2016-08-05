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
import android.support.v4.app.Fragment;
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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.test.vice20.DataBaseHelper;
import com.test.vice20.Fragments.ArticleListFragment;
import com.test.vice20.Fragments.DetailsFragment;
import com.test.vice20.Fragments.FavoritesRecyclerViewFragment;
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
import com.facebook.FacebookSdk;

public class MainActivity extends AppCompatActivity implements ItemClickedInterface {

    public static String baseURL = "http://vice.com/";
    private DetailsFragment detailFragment;
    Article favoriteArticle;
    NewsServiceInterface newsServiceInterface;
    String favArticleId;
    DataBaseHelper helper;
    private static final int JOB_INFO = 13;

    private static final String fragTag = "firstFragTag";
    private static final String searchFragTag = "searchTag";
    CallbackManager callbackManager;
    Menu menu;
    LoginButton loginButton;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setUpFacebook();



        // create a new fragment
        ArticleListFragment fragment = new ArticleListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(fragmentManager.findFragmentByTag(searchFragTag) == null) {

            // add fragment to the container ( there is nothing there yet, that is why we add )
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(fragTag);
            Log.d(fragTag, "we're in onCreate with first Frag");
            fragmentTransaction.commit();
        }

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);  //sterling added toolbar on wed night
        setSupportActionBar(toolbar);

        //schedule article updates evey 100 secs (for testing purposes)
        JobInfo jobInfo = new JobInfo.Builder(JOB_INFO,
                new ComponentName(getPackageName(),
                        NotificationJobService.class.getName()))
                .setPersisted(true)
                .setPeriodic(100000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);

        //handling search intent
        handleIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
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
                FavoritesRecyclerViewFragment favfrag= new FavoritesRecyclerViewFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,favfrag);
                fragmentTransaction.commit();

                return true;
            case R.id.action_favorite:
               if(helper.exists(favArticleId)){
                   helper.deleteFavoritesItem(favArticleId);
                   item.setIcon(android.R.drawable.btn_star_big_off);
                   Toast.makeText(MainActivity.this,"deleted"+helper.getFavoritesList().getCount(),Toast.LENGTH_SHORT).show();
               }else{
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
                        item.setIcon(android.R.drawable.btn_star_big_on);
                    }

                    @Override
                    public void onFailure(Call<ArticleNews> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Article API call failed", Toast.LENGTH_SHORT).show();
                    }
                });}

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
        helper= DataBaseHelper.getInstance(MainActivity.this);

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
        if (helper.exists(selectedArticleID)) {
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_favorite).setIcon(android.R.drawable.btn_star_big_on);
            detailFragment.setIsFavOn(true);
            Log.i("STAR", "item existes");
        }
        }



    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);


            ArticleListFragment listFragment = new ArticleListFragment();

            listFragment.setQuery(query);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, listFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();


        }
    }
    public void setUpFacebook(){
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
