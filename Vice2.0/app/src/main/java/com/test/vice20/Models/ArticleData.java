package com.test.vice20.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kitty on 8/3/16.
 */
public class ArticleData {

    @SerializedName("article")
    @Expose
    private Article article;

    /**
     *
     * @return
     * The article
     */
    public Article getArticle() {
        return article;
    }

    /**
     *
     * @param article
     * The article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

}
