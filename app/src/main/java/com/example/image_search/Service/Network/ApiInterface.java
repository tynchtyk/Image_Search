package com.example.image_search.Service.Network;

import com.example.image_search.Service.Model.Response_Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v2/search/image?")
    Call<Response_Data> getSearchImages(
            @Query("query") String query);
}
