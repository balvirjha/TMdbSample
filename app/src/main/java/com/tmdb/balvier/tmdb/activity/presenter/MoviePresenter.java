package com.tmdb.balvier.tmdb.activity.presenter;

import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;

import retrofit2.Response;

/**
 * Created by Balvier on 9/6/2017.
 */

public class MoviePresenter {

    public interface MovieListResponseCallback {
        public void getMovieListSuccess(Response<MovieListResponse> response, int responseCode);

        public void errorGettingMovieList(String errormessage);
    }

    public interface MovieListRequestCallback {
        public void getMovieList(MoviePresenter.MovieListResponseCallback movieListResponseCallback);

        public void errorRequestingMovieList(String errormessage);
    }

    public interface MovieDetailResponseCallback {
        public void getMovieDetailSuccess(Response<MovieDetailResponse.MovieDetailClass> response, int responseCode);

        public void errorGettingMovieDetail(String errormessage);
    }

    public interface MovieDetailRequestCallback {
        public void getMovieDetail(MoviePresenter.MovieDetailResponseCallback movieDetailResponseCallback, String movie_id);

        public void errorRequestingMovieDetail(String errormessage);
    }

}
