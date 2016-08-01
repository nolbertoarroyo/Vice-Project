package com.test.vice20;

import com.test.vice20.Models.Data;
import com.test.vice20.Models.Item;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kitty on 8/1/16.
 */
public interface NewsServiceInterface {

    @GET("//api/getvicetoday/{page}")
    Call<Data> getTodayList(@Path("page") int page);

    @GET("/api/getmostpopular/{category}/{page}")
        // Categories: news, music, sports, tech, travel, fashion, guide, nsfw, photo, comics, stuff, film, festivals, gallery, noisey, thecreatorsproject, fightland, motherboard, food, interviews, culture, column
    Call<Data> getPopularList(@Path("category") String category, @Path("page") String page);

    @GET("/api/getlatest/category/{category}/{page}")
        // news, music, sports, tech, travel, fashion, guide, nsfw, photo, comics, stuff, film, festivals, gallery, noisey, thecreatorsproject, fightland, motherboard, food, interviews, culture, column
    Call<Data> getLatestList(@Path("category") String category, @Path("page") String page);

    @GET("/api/article/{id}")
    Call<Item> getArticle(@Path("id") String id);

}
