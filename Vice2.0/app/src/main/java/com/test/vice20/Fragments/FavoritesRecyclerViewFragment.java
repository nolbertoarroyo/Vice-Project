package com.test.vice20.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.test.vice20.Activities.MainActivity;
import com.test.vice20.CustomRecyclerViewAdapter;
import com.test.vice20.DataBaseHelper;
import com.test.vice20.Interfaces.ItemClickedInterface;
import com.test.vice20.Interfaces.NewsServiceInterface;
import com.test.vice20.Models.Data;
import com.test.vice20.Models.Item;
import com.test.vice20.MyCustomAdapter;
import com.test.vice20.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kitty on 8/2/16.
 */
public class FavoritesRecyclerViewFragment extends Fragment {

    private static final String TAG = "RecyclerViewFragment";

    private List<Item> favorites;
    private DataBaseHelper dataBaseHelper;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private NewsServiceInterface newsServiceInterface;
    private ItemClickedInterface itemClickedInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper = DataBaseHelper.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        rootView.setTag(TAG);

        getData();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapter = new CustomRecyclerViewAdapter(favorites);
        recyclerView.setAdapter(rvAdapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //passing the article id of the position selected
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String articleClicked = ((Item) l.getAdapter().getItem(position)).getId();
        itemClickedInterface.onItemClicked(articleClicked);
    }

    //get data depending on if query or default from VICE API
    private void getData() {

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
