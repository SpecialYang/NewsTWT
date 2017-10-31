package com.special.newsdemo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Special on 2017/10/30.
 */

public class New {
    public int index;

    public String subject;

    public String pic;

    @SerializedName("visitcount")
    public int visitCount;

    public int comments;

    public String summary;
}
