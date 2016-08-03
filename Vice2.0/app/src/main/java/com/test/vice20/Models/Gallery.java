package com.test.vice20.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kitty on 8/2/16.
 */
public class Gallery {

    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("credit")
    @Expose
    private Object credit;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("image")
    @Expose
    private String image;

    /**
     *
     * @return
     * The title
     */
    public Object getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(Object title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The credit
     */
    public Object getCredit() {
        return credit;
    }

    /**
     *
     * @param credit
     * The credit
     */
    public void setCredit(Object credit) {
        this.credit = credit;
    }

    /**
     *
     * @return
     * The caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     *
     * @param caption
     * The caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     *
     * @return
     * The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     * The thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     *
     * @param thumb
     * The thumb
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
