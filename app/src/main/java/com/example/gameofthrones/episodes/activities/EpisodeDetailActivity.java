package com.example.gameofthrones.episodes.activities;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.gameofthrones.R;
import com.example.gameofthrones.databinding.ActivityEpisodeDetailsBinding;
import com.example.gameofthrones.episodes.models.Episode;

/**
 * Created by jagadesh.seeram on 7/3/2017.
 */

public class EpisodeDetailActivity extends AppCompatActivity {

    public static final String KEY_EPISODE = "KEY_EPISODE";
    private ActivityEpisodeDetailsBinding detailsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_episode_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getBundleData();
    }

    /**
     * Get the bundle and bind it to the layout
     */
    private void getBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Episode episode = bundle.getParcelable(KEY_EPISODE);
            setTitle(episode.name);
            detailsBinding.setEpisode(episode);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
