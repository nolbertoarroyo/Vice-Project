package com.test.vice20;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.test.vice20.Activities.MainActivity;
import com.test.vice20.Models.Data;
import com.test.vice20.Models.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kitty on 8/1/16.
 */
public class ArticleListFragment extends android.support.v4.app.ListFragment {

    private List<Item> results;
    private MyCustomAdapter customAdapter;

    private String query = null;

    private NewsServiceInterface newsServiceInterface;
    private ItemClickedInterface itemClickedInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemClickedInterface = (ItemClickedInterface) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement interface  ");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (query == null) {
            //default today news
            getData(true, null);
        } else {
            //get articles based on query
            getData(false, query);
            query = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        customAdapter = new MyCustomAdapter(results, getActivity());
        setListAdapter(customAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //passing the article id of the position selected
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String articleClicked = ((Item) l.getAdapter().getItem(position)).getId();
        itemClickedInterface.onItemClicked(articleClicked);
    }

    //get data depending on if query or default from VICE API
    private void getData(boolean isDefault, String query) {

        //check internet connection
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsServiceInterface = retrofit.create(NewsServiceInterface.class);

            //if no query, populate with today's latest news
            if (isDefault) {
                newsServiceInterface.getTodayList(1).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        results = response.body().getItems();
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            } else { //if query entered populate with the category's latest news
                newsServiceInterface.getLatestList(query, 1).enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        results = response.body().getItems();
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {

                    }
                });
            }

        } else {
            Toast.makeText(getActivity(), "No network connection", Toast.LENGTH_LONG).show();
        }
    }

}