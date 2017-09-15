package com.tmdb.balvier.tmdb.activity.presenter;

import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieTrailersResponse;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.YoutubeResponse;

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

    public interface MovieTrailerRequestCallback {
        public void getMovieTrailer(MoviePresenter.MovieTrailerResponseCallback movieTrailerResponseCallback, String movie_id);

        public void errorRequestingMovieTrailer(String errormessage);
    }

    public interface MovieTrailerResponseCallback {
        public void getMovieTrailerSuccess(Response<MovieTrailersResponse> response, int responseCode);

        public void errorGettingMovieTrailer(String errormessage);
    }


    public interface MovieTrailerGoogleRequestCallback {
        public void getMovieGoogleTrailer(MoviePresenter.MovieTrailerGoogleResponseCallback movieTrailerGoogleResponseCallback, String movie_id);

        public void errorRequestingMovieGoogleTrailer(String errormessage);
    }

    public interface MovieTrailerGoogleResponseCallback {
        public void getMovieGoogleTrailerSuccess(Response<YoutubeResponse> response, int responseCode);

        public void errorGettingMovieGoogleTrailer(String errormessage);
    }


}
