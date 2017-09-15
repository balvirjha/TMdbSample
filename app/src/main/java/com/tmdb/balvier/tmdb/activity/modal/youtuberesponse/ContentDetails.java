package com.tmdb.balvier.tmdb.activity.modal.youtuberesponse;

/**
 * Created by Balvier on 9/15/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDetails implements Parcelable
{

    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("definition")
    @Expose
    private String definition;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("licensedContent")
    @Expose
    private Boolean licensedContent;
    @SerializedName("projection")
    @Expose
    private String projection;
    public final static Parcelable.Creator<ContentDetails> CREATOR = new Creator<ContentDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ContentDetails createFromParcel(Parcel in) {
            ContentDetails instance = new ContentDetails();
            instance.duration = ((String) in.readValue((String.class.getClassLoader())));
            instance.dimension = ((String) in.readValue((String.class.getClassLoader())));
            instance.definition = ((String) in.readValue((String.class.getClassLoader())));
            instance.caption = ((String) in.readValue((String.class.getClassLoader())));
            instance.licensedContent = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.projection = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ContentDetails[] newArray(int size) {
            return (new ContentDetails[size]);
        }

    }
            ;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Boolean getLicensedContent() {
        return licensedContent;
    }

    public void setLicensedContent(Boolean licensedContent) {
        this.licensedContent = licensedContent;
    }

    public String getProjection() {
        return projection;
    }

    public void setProjection(String projection) {
        this.projection = projection;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(duration);
        dest.writeValue(dimension);
        dest.writeValue(definition);
        dest.writeValue(caption);
        dest.writeValue(licensedContent);
        dest.writeValue(projection);
    }

    public int describeContents() {
        return 0;
    }

}