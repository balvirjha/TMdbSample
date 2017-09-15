package com.tmdb.balvier.tmdb.activity.modal.youtuberesponse;

/**
 * Created by Balvier on 9/15/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics implements Parcelable
{

    @SerializedName("viewCount")
    @Expose
    private String viewCount;
    @SerializedName("likeCount")
    @Expose
    private String likeCount;
    @SerializedName("dislikeCount")
    @Expose
    private String dislikeCount;
    @SerializedName("favoriteCount")
    @Expose
    private String favoriteCount;
    @SerializedName("commentCount")
    @Expose
    private String commentCount;
    public final static Parcelable.Creator<Statistics> CREATOR = new Creator<Statistics>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Statistics createFromParcel(Parcel in) {
            Statistics instance = new Statistics();
            instance.viewCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.likeCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.dislikeCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.favoriteCount = ((String) in.readValue((String.class.getClassLoader())));
            instance.commentCount = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Statistics[] newArray(int size) {
            return (new Statistics[size]);
        }

    }
            ;

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(String dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(viewCount);
        dest.writeValue(likeCount);
        dest.writeValue(dislikeCount);
        dest.writeValue(favoriteCount);
        dest.writeValue(commentCount);
    }

    public int describeContents() {
        return 0;
    }

}

