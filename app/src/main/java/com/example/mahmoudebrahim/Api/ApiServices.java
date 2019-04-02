package com.example.mahmoudebrahim.Api;


import com.example.mahmoudebrahim.Api.Model.NewsSourceItemResponse.NewsSourceItemResponse;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.NewsResponse;
import com.example.mahmoudebrahim.Api.Model.NewsSourceResponse.SourcesItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET ("sources")
    Call<NewsResponse> getSourcesNews(@Query("apiKey") String apiKey,
                                      @Query("language") String language);

    @GET ("everything")
    Call<NewsSourceItemResponse> getSourceItem(@Query("apiKey") String apikey,
                                               @Query("sources") String sourceId,
                                               @Query("language") String language);
}
