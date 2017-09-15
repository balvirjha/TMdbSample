package com.tmdb.balvier.tmdb.activity.modal.youtuberesponse;

/**
 * Created by Balvier on 9/15/2017.
 */
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet implements Parcelable
{

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("channelId")
    @Expose
    private String channelId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("channelTitle")
    @Expose
    private String channelTitle;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("categoryId")
    @Expose
    private String categoryId;
    @SerializedName("liveBroadcastContent")
    @Expose
    private String liveBroadcastContent;
    @SerializedName("localized")
    @Expose
    private Localized localized;
    public final static Parcelable.Creator<Snippet> CREATOR = new Creator<Snippet>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Snippet createFromParcel(Parcel in) {
            Snippet instance = new Snippet();
            instance.publishedAt = ((String) in.readValue((String.class.getClassLoader())));
            instance.channelId = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.thumbnails = ((Thumbnails) in.readValue((Thumbnails.class.getClassLoader())));
            instance.channelTitle = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.tags, (java.lang.String.class.getClassLoader()));
            instance.categoryId = ((String) in.readValue((String.class.getClassLoader())));
            instance.liveBroadcastContent = ((String) in.readValue((String.class.getClassLoader())));
            instance.localized = ((Localized) in.readValue((Localized.class.getClassLoader())));
            return instance;
        }

        public Snippet[] newArray(int size) {
            return (new Snippet[size]);
        }

    }
            ;

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLiveBroadcastContent() {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent(String liveBroadcastContent) {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    public Localized getLocalized() {
        return localized;
    }

    public void setLocalized(Localized localized) {
        this.localized = localized;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(publishedAt);
        dest.writeValue(channelId);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(thumbnails);
        dest.writeValue(channelTitle);
        dest.writeList(tags);
        dest.writeValue(categoryId);
        dest.writeValue(liveBroadcastContent);
        dest.writeValue(localized);
    }

    public int describeContents() {
        return 0;
    }

}