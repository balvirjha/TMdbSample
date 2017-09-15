package com.tmdb.balvier.tmdb.activity.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.MovieActivity;
import com.tmdb.balvier.tmdb.activity.modal.GlideApp;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieTrailersResponse;
import com.tmdb.balvier.tmdb.activity.modal.Result;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.YoutubeResponse;
import com.tmdb.balvier.tmdb.activity.presenter.MoviePresenter;
import com.tmdb.balvier.tmdb.activity.presenter.MovieTrailers;
import com.tmdb.balvier.tmdb.activity.presenter.MovieYoutubeTrailer;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MovieDetailFragment extends Fragment implements View.OnClickListener,
        MoviePresenter.MovieTrailerResponseCallback, MoviePresenter.MovieTrailerGoogleResponseCallback {

    private OnFragmentInteractionListener mListener;
    private ImageView mViewFlipper, movieTrailers;
    private View mRoot;
    private MovieDetailResponse.MovieDetailClass movieDetailResponse;
    private ProgressBar progressbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            movieDetailResponse = (MovieDetailResponse.MovieDetailClass) getArguments().getSerializable("movieDetail");
        }
        mRoot = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewFlipper = (ImageView) mRoot.findViewById(R.id.moviePosterFlipper);
        movieTrailers = (ImageView) mRoot.findViewById(R.id.movieTrailers);
        movieTrailers.setOnClickListener(this);
        mViewFlipper.setOnClickListener(this);
        if (movieDetailResponse != null) {
            mRoot.findViewById(R.id.detailLayout).setVisibility(View.VISIBLE);
            progressbar = (ProgressBar) mRoot.findViewById(R.id.progressbar);
            progressbar.setVisibility(View.VISIBLE);
            addMoviePostersToViewFlipper();
            ((TextView) mRoot.findViewById(R.id.title)).setText(movieDetailResponse.getTitle());
            ((TextView) mRoot.findViewById(R.id.overview)).setText(movieDetailResponse.getOverview());
            float d = (float) ((movieDetailResponse.getPopularity() * 5) / 100);
            ((RatingBar) mRoot.findViewById(R.id.ratings)).setRating(d);
        } else {
            mRoot.findViewById(R.id.detailLayout).setVisibility(View.GONE);
        }

    }

    void addMoviePostersToViewFlipper() {
        GlideApp.with(getActivity())
                .load(RetrofitClient.IMAGE_BASE_URL_BACKDROP + movieDetailResponse.getBackdropPath())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressbar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressbar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerCrop()
                .into(mViewFlipper);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onLoadDetailPage(MovieDetailResponse.MovieDetailClass movieDetailResponse);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == mViewFlipper.getId() || view.getId() == movieTrailers.getId()) {
            showLoaderFragement();
            new MovieTrailers().getMovieTrailer(this, movieDetailResponse.getId().toString());
        }
    }


    ProgressLoaderFragment progressLoaderFragment;

    private void showLoaderFragement() {

        progressLoaderFragment = new ProgressLoaderFragment();
        progressLoaderFragment.show(((AppCompatActivity) getActivity()).getSupportFragmentManager(), ProgressLoaderFragment.class.getSimpleName());
    }

    int callGoogleAPiStack = 0;

    List<YoutubeResponse> youtubeResponseList = new ArrayList<>();

    @Override
    public void getMovieTrailerSuccess(Response<MovieTrailersResponse> response, int responseCode) {
        for (Result result : ((List<Result>) response.body().getResults())) {
            callGoogleAPiStack++;
            new MovieYoutubeTrailer().getMovieGoogleTrailer(this, result.getKey());

            Log.e("bvc", "result.getKey() = " + result.getKey());
        }

    }


    @Override
    public void errorGettingMovieTrailer(String errormessage) {
        Log.e("bvc", "errorGettingMovieTrailer");
        if (progressLoaderFragment != null && progressLoaderFragment.isAdded()) {
            progressLoaderFragment.dismiss();
        }
    }

    @Override
    public void getMovieGoogleTrailerSuccess(Response<YoutubeResponse> response, int responseCode) {
        youtubeResponseList.add(response.body());

        callGoogleAPiStack--;
        if (callGoogleAPiStack == 0) {
            if (progressLoaderFragment != null && progressLoaderFragment.isAdded()) {
                progressLoaderFragment.dismiss();
            }
            Log.e("bvc", "total size of youtube trailers: " + youtubeResponseList.size());
            //showMovies();
            ((MovieActivity) getActivity()).loadMovieTrailerListFragment(youtubeResponseList);
        }
    }

    @Override
    public void errorGettingMovieGoogleTrailer(String errormessage) {
        if (progressLoaderFragment != null && progressLoaderFragment.isAdded()) {
            progressLoaderFragment.dismiss();
        }
        Log.e("bvc", "errorGettingMovieGoogleTrailer");
        callGoogleAPiStack--;
    }

}
