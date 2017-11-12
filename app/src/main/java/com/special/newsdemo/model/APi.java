package com.special.newsdemo.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Special on 2017/11/12.
 */

public interface APi {
    @GET("news/{type}/page/{page}")
    Call<NewsResponse> getNewsList(@Path("type") int type, @Path("page") int page);

    @GET("news/{index}")
    Call<NewContentResponse> getNewContent(@Path("index") int index);
}
