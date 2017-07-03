package com.example.gameofthrones.episodes;

import com.example.gameofthrones.episodes.models.Episode;

import java.util.ArrayList;

/**
 * Created by jagadeshseeram on 7/3/17.
 */

public interface EpisodeView {
    void showProgress();

    void hideProgress();

    void showErrorResponse();

    void showEpisodes(ArrayList<Episode> episodes);

    void navigateToDetails(Episode episode);
}
