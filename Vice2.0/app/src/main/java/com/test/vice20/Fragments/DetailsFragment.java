package com.test.vice20.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.test.vice20.Activities.MainActivity;
import com.test.vice20.DataBaseHelper;
import com.test.vice20.Interfaces.NewsServiceInterface;
import com.test.vice20.Models.Article;
import com.test.vice20.Models.ArticleNews;
import com.test.vice20.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nolbertoarroyo on 8/1/16.
 */
public class DetailsFragment extends Fragment {
    private NewsServiceInterface newsServiceInterface;
    private Article currentItem;
    private String id;
    private ImageView articleImage;
    private TextView titleText, authorText, contentText, categoryText, pubDateText;
    private Boolean fav = false;
    private DataBaseHelper dataBaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper = DataBaseHelper.getInstance(getActivity());
        setHasOptionsMenu(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details, container, false);
        setViews(v);
        getItem(id);
        return v;
    }

    public void populateViews() {
        //setting article properties to views
        titleText.setText(currentItem.getTitle());
        authorText.setText(currentItem.getAuthor());
        categoryText.setText(currentItem.getCategory());
        pubDateText.setText(currentItem.getPubDate());

        String htmlTextStr = Html.fromHtml(currentItem.getBody()).toString();
        contentText.setText(htmlTextStr);

        // uses picasso for image when there's internet
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Picasso.with(getContext())
                    .load(currentItem.getImage())
                    .into(articleImage);
        } else {}
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        //setting visibility for menu items
        if (!fav) {
            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_share).setVisible(true);
        } else {

            menu.findItem(R.id.action_favorite).setVisible(true);
            menu.findItem(R.id.action_favorite).setIcon(android.R.drawable.btn_star_big_on);
            menu.findItem(R.id.action_share).setVisible(true);
            super.onPrepareOptionsMenu(menu);
        }
    }

    //getItem takes article id and runs a callback to retrieve article from api, runs populateViews() to set article properties to views
    public void getItem(String id) {

        //check internet connection
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsServiceInterface = retrofit.create(NewsServiceInterface.class);

            newsServiceInterface.getArticle(id).enqueue(new Callback<ArticleNews>() {
                @Override
                public void onResponse(Call<ArticleNews> call, Response<ArticleNews> response) {
                    currentItem = response.body().getData().getArticle();
                    populateViews();
                }

                @Override
                public void onFailure(Call<ArticleNews> call, Throwable t) {
                    Toast.makeText(getActivity(), "Article API call failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Article tempArticle = new Article();
            Cursor cursor = dataBaseHelper.getArticleById(id);
            cursor.moveToFirst();
            tempArticle.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_TITLE)));
            tempArticle.setAuthor(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_AUTHOR)));
            tempArticle.setBody(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_BODY)));
            tempArticle.setPubDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_PUBDATE)));
            currentItem = tempArticle;
            populateViews();
        }
    }

    // setter method to receive article position from listFragment
    public void setId(String id) {
        this.id = id;
    }


    public void setViews(View v) {
        //finding views from xml
        titleText = (TextView) v.findViewById(R.id.details_frag_title);
        authorText = (TextView) v.findViewById(R.id.details_frag_author);
        categoryText = (TextView) v.findViewById(R.id.details_frag_category);
        contentText = (TextView) v.findViewById(R.id.details_frag_content);
        articleImage = (ImageView) v.findViewById(R.id.details_frag_image);
        pubDateText = (TextView) v.findViewById(R.id.details_frag_pub_date);

    }

    public void setIsFavOn(Boolean fav) {
        this.fav = fav;
    }


}
