package com.test.vice20.Fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.vice20.Activities.MainActivity;
import com.test.vice20.Adapters.CustomRecyclerViewAdapter;
import com.test.vice20.DataBaseHelper;
import com.test.vice20.Interfaces.ItemClickedInterface;
import com.test.vice20.Interfaces.NewsServiceInterface;
import com.test.vice20.Models.Article;
import com.test.vice20.Models.ArticleNews;
import com.test.vice20.R;

import java.util.ArrayList;
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

    private List<Article> favorites = new ArrayList<>();
    private DataBaseHelper dataBaseHelper;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    private NewsServiceInterface newsServiceInterface;

    private ItemClickedInterface itemClickedInterface;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            itemClickedInterface = (ItemClickedInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement interface  ");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper = DataBaseHelper.getInstance(getActivity());

        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        rootView.setTag(TAG);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        rvLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLayoutManager);

        rvAdapter = new CustomRecyclerViewAdapter((ItemClickedInterface) getActivity(), favorites);
        recyclerView.setAdapter(rvAdapter);

        return rootView;
    }

    //get data depending on if query or default from VICE API
    private void getData() {

        final Cursor cursor = dataBaseHelper.getFavoritesList();

        //check internet connection
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MainActivity.baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            newsServiceInterface = retrofit.create(NewsServiceInterface.class);

            for (int i = 0; i<cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                newsServiceInterface.getArticle(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_ITEM_ID))).enqueue(new Callback<ArticleNews>() {
                    @Override
                    public void onResponse(Call<ArticleNews> call, Response<ArticleNews> response) {
                        favorites.add(response.body().getData().getArticle());
                        rvAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(Call<ArticleNews> call, Throwable t) {
                        Toast.makeText(getActivity(), "Fav API call failed", Toast.LENGTH_SHORT);
                    }
                });
            }
            Log.d("checking value: ", String.valueOf(favorites));
        } else {
            for (int i = 0; i<cursor.getCount(); i++) {
                cursor.moveToPosition(i);
                Article temp = new Article();
                temp.setAuthor(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_AUTHOR)));
                temp.setTitle(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_TITLE)));
                temp.setPreview(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_PREVIEW)));
                temp.setPubDate(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_PUBDATE)));
                temp.setBody(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_BODY)));
                temp.setId(Integer.toString(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_ITEM_ID))));
                //temp.setImage(cursor.getString(cursor.getColumnIndex(DataBaseHelper.DataEntryFavorites.COL_DEFAULT_IMAGE)));
                favorites.add(temp);
            }
        }
    }
}
