package com.devian.network;

import com.devian.psexample.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixaBayAPI {

    @GET("/api/?key="+ Constants.API_KEY)
        Call<APIResponse> getImagesforQuery(@Query("q") String queryString, @Query("image_type") String imageType);
}
