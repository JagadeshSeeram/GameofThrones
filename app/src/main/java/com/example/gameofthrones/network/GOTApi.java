package com.example.gameofthrones.network;


import com.example.gameofthrones.episodes.models.ResponseEpisodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jagadesh.seeram on 7/3/17.
 */
public interface GOTApi {

    @GET("3/tv/1399/season/1")
    Call<ResponseEpisodes> getEpisodes(@Query("api_key") String apiKey);

}
