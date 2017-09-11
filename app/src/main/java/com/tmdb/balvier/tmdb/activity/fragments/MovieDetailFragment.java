package com.tmdb.balvier.tmdb.activity.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.tmdb.balvier.tmdb.activity.modal.GlideApp;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

public class MovieDetailFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageView mViewFlipper;
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
}
