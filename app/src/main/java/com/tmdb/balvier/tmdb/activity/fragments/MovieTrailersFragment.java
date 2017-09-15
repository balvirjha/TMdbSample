package com.tmdb.balvier.tmdb.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.adapter.MovieTrailerAdapter;
import com.tmdb.balvier.tmdb.activity.modal.MovieTrailersResponse;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.YoutubeResponse;

import java.util.List;

/**
 * Created by Balvier on 9/14/2017.
 */

public class MovieTrailersFragment extends Fragment {

    private View mRoot;
    private RecyclerView trailerRecyclerView;
    private MovieTrailerAdapter mAdapter;
    List<YoutubeResponse> youtubeResponseList;

    public void setYoutubeResponseList(List<YoutubeResponse> youtubeResponseList) {
        this.youtubeResponseList = youtubeResponseList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRoot = inflater.inflate(R.layout.movie_trailers_fragment, container, false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showMovies();
    }

    private void showMovies() {
        trailerRecyclerView = (RecyclerView) mRoot.findViewById(R.id.recycler_view);
        trailerRecyclerView.setHasFixedSize(false);
        trailerRecyclerView.setItemViewCacheSize(20);
        trailerRecyclerView.setDrawingCacheEnabled(true);
        trailerRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAdapter = new MovieTrailerAdapter(youtubeResponseList, getActivity(), trailerRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        trailerRecyclerView.setLayoutManager(mLayoutManager);
        trailerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trailerRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        trailerRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
