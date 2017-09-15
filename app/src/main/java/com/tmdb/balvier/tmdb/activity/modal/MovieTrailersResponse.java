package com.tmdb.balvier.tmdb.activity.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Balvier on 9/15/2017.
 */

public class MovieTrailersResponse implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    public final static Parcelable.Creator<MovieTrailersResponse> CREATOR = new Creator<MovieTrailersResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieTrailersResponse createFromParcel(Parcel in) {
            MovieTrailersResponse instance = new MovieTrailersResponse();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (com.tmdb.balvier.tmdb.activity.modal.Result.class.getClassLoader()));
            return instance;
        }

        public MovieTrailersResponse[] newArray(int size) {
            return (new MovieTrailersResponse[size]);
        }

    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}


