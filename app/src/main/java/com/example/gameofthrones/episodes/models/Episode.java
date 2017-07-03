package com.example.gameofthrones.episodes.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jagadesh.seeram on 7/3/2017.
 */

public class Episode implements Parcelable {

    public String name;
    public String air_date;
    public int episode_number;
    public String overview;
    public int id;
    public int season_number;
    public float vote_average;
    public int vote_count;

    public Episode(){

    }
    protected Episode(Parcel in) {
        name = in.readString();
        air_date = in.readString();
        episode_number = in.readInt();
        overview = in.readString();
        id = in.readInt();
        season_number = in.readInt();
        vote_average = in.readFloat();
        vote_count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(air_date);
        dest.writeInt(episode_number);
        dest.writeString(overview);
        dest.writeInt(id);
        dest.writeInt(season_number);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Episode> CREATOR = new Parcelable.Creator<Episode>() {
        @Override
        public Episode createFromParcel(Parcel in) {
            return new Episode(in);
        }

        @Override
        public Episode[] newArray(int size) {
            return new Episode[size];
        }
    };
}
