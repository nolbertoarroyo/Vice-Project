package com.test.vice20.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kitty on 8/2/16.
 */
public class Media {

    @SerializedName("gallery")
    @Expose
    private List<Gallery> gallery = new ArrayList<Gallery>();

    /**
     *
     * @return
     * The gallery
     */
    public List<Gallery> getGallery() {
        return gallery;
    }

    /**
     *
     * @param gallery
     * The gallery
     */
    public void setGallery(List<Gallery> gallery) {
        this.gallery = gallery;
    }

}
