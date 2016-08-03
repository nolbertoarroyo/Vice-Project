package com.test.vice20.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kitty on 8/3/16.
 */
public class ArticleNews {

    @SerializedName("data")
    @Expose
    private ArticleData data;

    /**
     *
     * @return
     * The data
     */
    public ArticleData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(ArticleData data) {
        this.data = data;
    }

}
