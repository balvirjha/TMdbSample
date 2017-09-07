package com.tmdb.balvier.tmdb.activity.modal;

/**
 * Created by Balvier on 9/7/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class MovieDetailResponse {
    class BelongsToCollection implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        private final static long serialVersionUID = 3567253375414438351L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

    }

    public class MovieDetailClass implements Serializable {

        @SerializedName("adult")
        @Expose
        private Boolean adult;
        @SerializedName("backdrop_path")
        @Expose
        private String backdropPath;
        @SerializedName("belongs_to_collection")
        @Expose
        private BelongsToCollection belongsToCollection;
        @SerializedName("budget")
        @Expose
        private Integer budget;
        @SerializedName("genres")
        @Expose
        private List<Genre> genres = null;
        @SerializedName("homepage")
        @Expose
        private String homepage;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("imdb_id")
        @Expose
        private String imdbId;
        @SerializedName("original_language")
        @Expose
        private String originalLanguage;
        @SerializedName("original_title")
        @Expose
        private String originalTitle;
        @SerializedName("overview")
        @Expose
        private String overview;
        @SerializedName("popularity")
        @Expose
        private Float popularity;
        @SerializedName("poster_path")
        @Expose
        private String posterPath;
        @SerializedName("production_companies")
        @Expose
        private List<ProductionCompany> productionCompanies = null;
        @SerializedName("production_countries")
        @Expose
        private List<ProductionCountry> productionCountries = null;
        @SerializedName("release_date")
        @Expose
        private String releaseDate;
        @SerializedName("revenue")
        @Expose
        private Integer revenue;
        @SerializedName("runtime")
        @Expose
        private Integer runtime;
        @SerializedName("spoken_languages")
        @Expose
        private List<SpokenLanguage> spokenLanguages = null;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("tagline")
        @Expose
        private String tagline;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("video")
        @Expose
        private Boolean video;
        @SerializedName("vote_average")
        @Expose
        private Float voteAverage;
        @SerializedName("vote_count")
        @Expose
        private Integer voteCount;
        private final static long serialVersionUID = 7991049449471494566L;

        public Boolean getAdult() {
            return adult;
        }

        public void setAdult(Boolean adult) {
            this.adult = adult;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public BelongsToCollection getBelongsToCollection() {
            return belongsToCollection;
        }

        public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
            this.belongsToCollection = belongsToCollection;
        }

        public Integer getBudget() {
            return budget;
        }

        public void setBudget(Integer budget) {
            this.budget = budget;
        }

        public List<Genre> getGenres() {
            return genres;
        }

        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImdbId() {
            return imdbId;
        }

        public void setImdbId(String imdbId) {
            this.imdbId = imdbId;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public Float getPopularity() {
            return popularity;
        }

        public void setPopularity(Float popularity) {
            this.popularity = popularity;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public List<ProductionCompany> getProductionCompanies() {
            return productionCompanies;
        }

        public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
            this.productionCompanies = productionCompanies;
        }

        public List<ProductionCountry> getProductionCountries() {
            return productionCountries;
        }

        public void setProductionCountries(List<ProductionCountry> productionCountries) {
            this.productionCountries = productionCountries;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public Integer getRevenue() {
            return revenue;
        }

        public void setRevenue(Integer revenue) {
            this.revenue = revenue;
        }

        public Integer getRuntime() {
            return runtime;
        }

        public void setRuntime(Integer runtime) {
            this.runtime = runtime;
        }

        public List<SpokenLanguage> getSpokenLanguages() {
            return spokenLanguages;
        }

        public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
            this.spokenLanguages = spokenLanguages;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTagline() {
            return tagline;
        }

        public void setTagline(String tagline) {
            this.tagline = tagline;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getVideo() {
            return video;
        }

        public void setVideo(Boolean video) {
            this.video = video;
        }

        public Float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(Float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(Integer voteCount) {
            this.voteCount = voteCount;
        }

    }

    class Genre implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        private final static long serialVersionUID = -6090186935458313816L;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    class ProductionCompany implements Serializable {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("id")
        @Expose
        private Integer id;
        private final static long serialVersionUID = 4855527118940297141L;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    class ProductionCountry implements Serializable {

        @SerializedName("iso_3166_1")
        @Expose
        private String iso31661;
        @SerializedName("name")
        @Expose
        private String name;
        private final static long serialVersionUID = -8101588047018536907L;

        public String getIso31661() {
            return iso31661;
        }

        public void setIso31661(String iso31661) {
            this.iso31661 = iso31661;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    class SpokenLanguage implements Serializable {

        @SerializedName("iso_639_1")
        @Expose
        private String iso6391;
        @SerializedName("name")
        @Expose
        private String name;
        private final static long serialVersionUID = 2894018354073871938L;

        public String getIso6391() {
            return iso6391;
        }

        public void setIso6391(String iso6391) {
            this.iso6391 = iso6391;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}