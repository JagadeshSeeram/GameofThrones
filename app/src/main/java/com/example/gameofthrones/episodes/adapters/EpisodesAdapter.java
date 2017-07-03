package com.example.gameofthrones.episodes.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gameofthrones.R;
import com.example.gameofthrones.episodes.EpisodeView;
import com.example.gameofthrones.episodes.models.Episode;

import java.util.ArrayList;

/**
 * Created by jagadesh.seeram on 7/3/2017.
 * <p>
 * Populate the Episode into the RecyclerView
 */

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder> {


    private final LayoutInflater inflater;
    private ArrayList<Episode> episodes;
    private EpisodeView episodeView;

    public EpisodesAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        if (context instanceof EpisodeView) {
            episodeView = (EpisodeView) context;
        }
        episodes = new ArrayList<>();
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        if (episodes != null && episodes.size() > 0) {
            this.episodes.addAll(episodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_episode, parent, false);
        return new EpisodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode episode = episodes.get(position);
        if (episode != null) {
            holder.episodeTitle.setText(episode.name);
        }
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    public Episode getItem(int position) {
        if (episodes != null && episodes.size() > position) {
            return episodes.get(position);
        }
        return null;
    }

    /**
     * ViewHolder to represent the data into the RecyclerView
     */
    class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView episodeTitle;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeTitle = (TextView) itemView.findViewById(R.id.text_episode_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            episodeView.navigateToDetails(episodes.get(getLayoutPosition()));
        }
    }

}
