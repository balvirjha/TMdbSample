package com.tmdb.balvier.tmdb.activity.modal.youtuberesponse;

/**
 * Created by Balvier on 9/15/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails implements Parcelable
{

    @SerializedName("default")
    @Expose
    private Default _default;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("high")
    @Expose
    private High high;
    @SerializedName("standard")
    @Expose
    private Standard standard;
    @SerializedName("maxres")
    @Expose
    private Maxres maxres;
    public final static Parcelable.Creator<Thumbnails> CREATOR = new Creator<Thumbnails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Thumbnails createFromParcel(Parcel in) {
            Thumbnails instance = new Thumbnails();
            instance._default = ((Default) in.readValue((Default.class.getClassLoader())));
            instance.medium = ((Medium) in.readValue((Medium.class.getClassLoader())));
            instance.high = ((High) in.readValue((High.class.getClassLoader())));
            instance.standard = ((Standard) in.readValue((Standard.class.getClassLoader())));
            instance.maxres = ((Maxres) in.readValue((Maxres.class.getClassLoader())));
            return instance;
        }

        public Thumbnails[] newArray(int size) {
            return (new Thumbnails[size]);
        }

    }
            ;

    public Default getDefault() {
        return _default;
    }

    public void setDefault(Default _default) {
        this._default = _default;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public High getHigh() {
        return high;
    }

    public void setHigh(High high) {
        this.high = high;
    }

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    public Maxres getMaxres() {
        return maxres;
    }

    public void setMaxres(Maxres maxres) {
        this.maxres = maxres;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(_default);
        dest.writeValue(medium);
        dest.writeValue(high);
        dest.writeValue(standard);
        dest.writeValue(maxres);
    }

    public int describeContents() {
        return 0;
    }

}