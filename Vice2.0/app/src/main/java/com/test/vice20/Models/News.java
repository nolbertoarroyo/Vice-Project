package com.test.vice20.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kitty on 8/2/16.
 */
public class News {

    public final Data data;

    public News(Data data) {
        this.data = data;
    }
}
