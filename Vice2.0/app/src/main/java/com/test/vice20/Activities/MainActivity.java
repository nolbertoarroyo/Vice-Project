package com.test.vice20.Activities;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
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

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemClickedInterface, NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView = null;
    private Toolbar toolbar = null;

    public static String baseURL = "http://vice.com/";
    private DetailsFragment detailFragment;
    private Article favoriteArticle;
    private NewsServiceInterface newsServiceInterface;
    private String favArticleId;
    private DataBaseHelper helper;
    private static final int JOB_INFO = 13;
    private static final String fragTag = "firstFragTag";
    private static final String searchFragTag = "searchTag";
    private static final String favFragTag = "favTag";
    private CallbackManager callbackManager;
    private Menu menu;
    private ShareDialog shareDialog;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //initializing facebook sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setFacebook();
        //starting home fragment
        startArticleListFragment();

        setToolBar();
        setJobScheduler();

        //Drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //handling search intent
        handleIntent(getIntent());
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        //how we handle the drawer clicks
        if (id == R.id.tech) { //if click on tech
            String query = "tech";
            //whatever you type it passes in , say list fragment query "tech"

            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setQuery(query);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleListFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();
        } else if (id == R.id.music) {
            String query = "music";
            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setQuery(query);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleListFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();
        } else if (id == R.id.travel) {
            String query = "travel";

            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setQuery(query);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleListFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();


        } else if (id == R.id.photo) {
            String query = "photo";
            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setQuery(query);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleListFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();

        } else if (id == R.id.festivals) {
            String query = "festivals";
            ArticleListFragment articleListFragment = new ArticleListFragment();
            articleListFragment.setQuery(query);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleListFragment);
            fragmentTransaction.addToBackStack(searchFragTag);
            fragmentTransaction.commit();

        } else if (id == R.id.favorites) {
            FavoritesRecyclerViewFragment fragment = new FavoritesRecyclerViewFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                shareToFacebook();

                return true;
            case R.id.action_favorite:
                //checking if current article is already in database, if so, deleting it
                if (helper.exists(favArticleId)) {
                    helper.deleteFavoritesItem(favArticleId);
                    item.setIcon(android.R.drawable.btn_star_big_off);
                    Toast.makeText(MainActivity.this, "Article removed from favorites", Toast.LENGTH_SHORT).show();
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(MainActivity.baseURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    newsServiceInterface = retrofit.create(NewsServiceInterface.class);

                    newsServiceInterface.getArticle(favArticleId).enqueue(new Callback<ArticleNews>() {
                        @Override
                        public void onResponse(Call<ArticleNews> call, Response<ArticleNews> response) {
                            //getting article from api and inserting to database favorites table
                            favoriteArticle = response.body().getData().getArticle();
                            helper = DataBaseHelper.getInstance(MainActivity.this);
                            helper.insertRowFavorities(favoriteArticle);
                            Toast.makeText(MainActivity.this, "Article added to favorites", Toast.LENGTH_SHORT).show();
                            item.setIcon(android.R.drawable.btn_star_big_on);
                        }

                        @Override
                        public void onFailure(Call<ArticleNews> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Article API call failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return true;
            case R.id.search:

                return true;
            case R.id.action_facebook:
                //checking if someone is logged in to facebook, if so, loging out on click and setting new text
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    LoginManager.getInstance().logOut();
                    item.setTitle(R.string.com_facebook_loginview_log_in_button_long);
                } else {
                    LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
                    item.setTitle(R.string.action_fb_logout);
                }

                return true;
            case R.id.action_favorites:
                startFavFragment();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClicked(String selectedArticleID) {
        helper = DataBaseHelper.getInstance(MainActivity.this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (detailFragment == null) {
            detailFragment = new DetailsFragment();
        }

        detailFragment.setId(selectedArticleID);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        favArticleId = selectedArticleID;

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

    //method prepares shareDialog to share to facebook
    public void shareToFacebook() {
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle(favoriteArticle.getTitle())
                    .setContentDescription(favoriteArticle.getPreview())
                    .setContentUrl(Uri.parse(favoriteArticle.getUrl()))
                    .build();

            shareDialog.show(linkContent);
        }

    }

    //Method sets up facebook login call
    public void setFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void startFavFragment() {
        FavoritesRecyclerViewFragment fragment = new FavoritesRecyclerViewFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(favFragTag);
        fragmentTransaction.commit();
    }


    public void startArticleListFragment() {
        // create a new fragment
        ArticleListFragment fragment = new ArticleListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (fragmentManager.findFragmentByTag(searchFragTag) == null) {

            // add fragment to the container
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(fragTag);
            Log.d(fragTag, "we're in onCreate with first Frag");
            fragmentTransaction.commit();
        }
    }

    public void setToolBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setJobScheduler() {
        //schedule article updates evey 100 secs (for testing purposes)
        JobInfo jobInfo = new JobInfo.Builder(JOB_INFO,
                new ComponentName(getPackageName(),
                        NotificationJobService.class.getName()))
                .setPersisted(true)
                .setPeriodic(100000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }

}
