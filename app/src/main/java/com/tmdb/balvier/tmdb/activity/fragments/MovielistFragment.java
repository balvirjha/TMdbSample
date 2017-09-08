package com.tmdb.balvier.tmdb.activity.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.adapter.MovieAdapter;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;

import java.util.List;

public class MovielistFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView movieRecyclerView;
    private MovieAdapter mAdapter;
    private View mRoot;
    private List<MovieListResponse.Result> resultListMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            MovieListResponse resultMovies = (MovieListResponse) getArguments().getSerializable("movieList");
            if (resultMovies != null) {
                resultListMovies = resultMovies.getResults();
            }
        }
        mRoot = inflater.inflate(R.layout.fragment_movielist, container, false);
        return mRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (resultListMovies != null) {
            showMovies();
        } else {
            LocalBroadcastManager.getInstance(ApplicationClass.getApplicationConotext()).registerReceiver(
                    mMessageReceiver, new IntentFilter("com.tmdb.balvier.tmdb.activity.receivingData"));
            ((ViewStub) mRoot.findViewById(R.id.errorLayout)).setVisibility(View.VISIBLE);
        }
    }

    private void showMovies() {
        movieRecyclerView = (RecyclerView) mRoot.findViewById(R.id.recycler_view);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setItemViewCacheSize(20);
        movieRecyclerView.setDrawingCacheEnabled(true);
        movieRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mAdapter = new MovieAdapter(resultListMovies, getActivity(), movieRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        movieRecyclerView.setLayoutManager(mLayoutManager);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        movieRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(ApplicationClass.getApplicationConotext()).unregisterReceiver(
                mMessageReceiver);
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

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("bvc", "gggggggggggggggggggg");
            if (intent.getExtras() != null) {
                MovieListResponse resultMovies = (MovieListResponse) getArguments().getSerializable("movieList");
                if (resultMovies != null) {
                    resultListMovies = resultMovies.getResults();
                }
                Log.e("bvc", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb ");
                String message = intent.getStringExtra("message");
                Log.e("bvc", "Got message: " + message);
                if (context != null) {
                    ((ViewStub) mRoot.findViewById(R.id.errorLayout)).setVisibility(View.GONE);
                }
                showMovies();
            }
        }
    };

    public interface OnFragmentInteractionListener {
        void onListItemClicked();
    }
}
