package com.example.gameofthrones.network;

import com.example.gameofthrones.Constants;
import com.example.gameofthrones.episodes.models.ResponseEpisodes;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jagadesh.seeram on 7/3/17.
 */
public class GOTApiWrapper {

    private GOTApi GOTApi;

    public GOTApiWrapper() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .baseUrl(Constants.WebConstants.BASE_URL).build();
        GOTApi = retrofit.create(GOTApi.class);
    }

    /**
     * Get the Episodes
     *
     * @param callback
     * @return
     */
    public Call<ResponseEpisodes> getEpisodes(Callback<ResponseEpisodes> callback) {
        Call<ResponseEpisodes> nonceCall = GOTApi.getEpisodes(Constants.API_KEY);
        nonceCall.enqueue(callback);
        return nonceCall;
    }

}
