package com.test.vice20.Interfaces;

import com.test.vice20.Models.Data;
import com.test.vice20.Models.Item;
import com.test.vice20.Models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kitty on 8/1/16.
 */
public interface NewsServiceInterface {

        @GET("/api/getvicetoday/{page}")
        Call<News> getTodayList(@Path("page") int page);

        @GET("/api/getmostpopular/{category}/{page}")
            // Categories: news, music, sports, tech, travel, fashion, guide, nsfw, photo, comics, stuff, film, festivals, gallery, noisey, thecreatorsproject, fightland, motherboard, food, interviews, culture, column
        Call<News> getPopularList(@Path("category") String category, @Path("page") int page);

        @GET("/api/getlatest/category/{category}/{page}")
            // news, music, sports, tech, travel, fashion, guide, nsfw, photo, comics, stuff, film, festivals, gallery, noisey, thecreatorsproject, fightland, motherboard, food, interviews, culture, column
        Call<News> getLatestList(@Path("category") String category, @Path("page") int page);

        @GET("/api/article/{id}")
        Call<Item> getArticle(@Path("id") int id);

}
