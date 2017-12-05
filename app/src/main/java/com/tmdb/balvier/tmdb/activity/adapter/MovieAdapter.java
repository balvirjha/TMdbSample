package com.tmdb.balvier.tmdb.activity.adapter;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.MovieActivity;
import com.tmdb.balvier.tmdb.activity.fragments.ProgressLoaderFragment;
import com.tmdb.balvier.tmdb.activity.modal.GlideApp;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.presenter.MovieDetail;
import com.tmdb.balvier.tmdb.activity.presenter.MoviePresenter;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;

import java.util.List;

import retrofit2.Response;

/**
 * Created by Balvier on 9/5/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> implements View.OnClickListener, MoviePresenter.MovieDetailResponseCallback {

    private List<MovieListResponse.Result> moviesList;
    private RecyclerView recyclerView;
    private Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieName, releaseDate, movieRating;
        ImageView moviePoster;
        ProgressBar moviePosterProgress;

        public MyViewHolder(View view) {
            super(view);
            moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
            movieName = (TextView) view.findViewById(R.id.movieName);
           // movieOverview = (TextView) view.findViewById(R.id.movieOverview);
            releaseDate = (TextView) view.findViewById(R.id.releaseDate);
            movieRating = (TextView) view.findViewById(R.id.movieRating);
            //moviePosterProgress = (ProgressBar) view.findViewById(R.id.moviePosterProgress);
        }

    }

    public MovieAdapter(List<MovieListResponse.Result> moviesList, Activity context, RecyclerView recyclerView) {
        this.moviesList = moviesList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movielistrow, parent, false);
        itemView.setOnClickListener(this);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        MovieListResponse.Result movie = moviesList.get(position);
        holder.movieName.setText(movie.getTitle());
        //holder.movieOverview.setText(movie.getOverview());
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.movieRating.setText(movie.getAdult() ? "U/A" : "U");
        final String url = RetrofitClient.IMAGE_BASE_URL + movie.getPosterPath();
        if (ApplicationClass.isTablet()) {
            GlideApp.with(context)
                    .load(url)
                    .error(R.drawable.error)
                    .fitCenter()
                    .into(holder.moviePoster);
        }else{
            GlideApp.with(context)
                    .load(url)
                    .error(R.drawable.error)
                    .centerCrop()
                    .into(holder.moviePoster);
        }

    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
        if (MovieActivity.rowPositipnClicked == itemPosition) {
            return;
        }
        ((MovieActivity) context).showProgressBar();
        MovieActivity.rowPositipnClicked = itemPosition;
        MovieListResponse.Result item = moviesList.get(itemPosition);
        Toast.makeText(context, item.getTitle(), Toast.LENGTH_LONG).show();
        showLoaderFragement();
        new MovieDetail().getMovieDetail(this, item.getId().toString());
    }

    ProgressLoaderFragment progressLoaderFragment;

    private void showLoaderFragement() {

        progressLoaderFragment = new ProgressLoaderFragment();
        progressLoaderFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), ProgressLoaderFragment.class.getSimpleName());
    }


    @Override
    public void getMovieDetailSuccess(Response<MovieDetailResponse.MovieDetailClass> response, int responseCode) {
        Log.e("bvc", "getMovieDetailSuccess");
        if (progressLoaderFragment != null && progressLoaderFragment.isAdded()) {
            progressLoaderFragment.dismiss();
        }
        // ((MovieActivity) context).hideProgressBar();
        ((MovieActivity) context).onLoadDetailPage(response.body());
    }

    @Override
    public void errorGettingMovieDetail(String errormessage) {
        Log.e("bvc", "errorGettingMovieDetail");
        if (progressLoaderFragment != null && progressLoaderFragment.isAdded()) {
            progressLoaderFragment.dismiss();
        }
        // ((MovieActivity) context).hideProgressBar();
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
