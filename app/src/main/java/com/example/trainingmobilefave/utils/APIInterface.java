package com.example.trainingmobilefave.utils;

import com.example.trainingmobilefave.model.TopCharacterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("top/characters")
    Call<TopCharacterResponse> getTopCharacters(@Query("page") int currentPage);
}
