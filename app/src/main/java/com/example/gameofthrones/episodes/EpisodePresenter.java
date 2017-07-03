package com.example.gameofthrones.episodes;

import com.example.gameofthrones.episodes.models.ResponseEpisodes;
import com.example.gameofthrones.network.GOTApiWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jagadeshseeram on 7/3/17.
 */

public class EpisodePresenter {

    private final EpisodeView episodeView;

    public EpisodePresenter(EpisodeView episodeView) {
        this.episodeView = episodeView;
        getEpisodes();
    }

    /**
     * Make an API Call to get the Episodes
     */
    private void getEpisodes() {
        episodeView.showProgress();
        GOTApiWrapper gotApiWrapper = new GOTApiWrapper();
        gotApiWrapper.getEpisodes(new Callback<ResponseEpisodes>() {
            @Override
            public void onResponse(Call<ResponseEpisodes> call, Response<ResponseEpisodes> response) {
                if (response.isSuccessful()) {
                    episodeView.showEpisodes(response.body().episodes);
                } else {
                    episodeView.showErrorResponse();
                }
                episodeView.hideProgress();
            }

            @Override
            public void onFailure(Call<ResponseEpisodes> call, Throwable t) {
                episodeView.hideProgress();
                episodeView.showErrorResponse();
            }
        });
    }
}
