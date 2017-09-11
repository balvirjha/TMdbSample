package com.tmdb.balvier.tmdb.activity.restservices;

import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Balvier on 9/6/2017.
 */

public interface MovieAPI {

    @GET("movie/upcoming")
    Call<MovieListResponse> getMovieList(@Query("api_key") String api_key);

    /**
     * @param movie_id
     * @param api_key
     * @return
     */
    @GET("movie/{movie-id}")
    Call<MovieDetailResponse.MovieDetailClass> getMovieDetails(@Path("movie-id") String movie_id, @Query("api_key") String api_key);
}
