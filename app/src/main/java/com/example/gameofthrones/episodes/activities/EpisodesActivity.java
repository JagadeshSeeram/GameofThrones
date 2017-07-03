package com.example.gameofthrones.episodes.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.gameofthrones.R;
import com.example.gameofthrones.databinding.ActivityEpisodesBinding;
import com.example.gameofthrones.episodes.EpisodePresenter;
import com.example.gameofthrones.episodes.EpisodeView;
import com.example.gameofthrones.episodes.adapters.EpisodesAdapter;
import com.example.gameofthrones.episodes.models.Episode;

import java.util.ArrayList;


/**
 * Created by jagadesh.seeram on 7/3/2017.
 */

public class EpisodesActivity extends AppCompatActivity implements EpisodeView {

    private EpisodesAdapter episodesAdapter;
    private ProgressDialog progressDialog;
    private ActivityEpisodesBinding episodesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        episodesBinding = DataBindingUtil.setContentView(this, R.layout.activity_episodes);
        initViews();
        new EpisodePresenter(this);
    }

    /**
     * Initialze the Views
     */
    private void initViews() {
        episodesBinding.episodesList.setLayoutManager(new LinearLayoutManager(this));
        episodesAdapter = new EpisodesAdapter(this);
        episodesBinding.episodesList.setAdapter(episodesAdapter);
    }


    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showErrorResponse() {
        Toast.makeText(this, "Error while fetching the Episodes", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEpisodes(ArrayList<Episode> episodes) {
        episodesAdapter.setEpisodes(episodes);
    }

    @Override
    public void navigateToDetails(Episode episode) {
        Intent intent = new Intent(this, EpisodeDetailActivity.class);
        intent.putExtra(EpisodeDetailActivity.KEY_EPISODE, episode);
        startActivity(intent);
    }
}
