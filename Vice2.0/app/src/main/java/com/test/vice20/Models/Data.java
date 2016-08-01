package com.test.vice20.Models;

/**
 * Created by kitty on 8/1/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;

    /**
     *
     * @return
     * The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     * The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     *
     * @return
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

}
