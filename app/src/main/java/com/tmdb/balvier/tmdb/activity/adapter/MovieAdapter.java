package com.tmdb.balvier.tmdb.activity.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.MovieActivity;
import com.tmdb.balvier.tmdb.activity.modal.GlideApp;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.presenter.MovieDetail;
import com.tmdb.balvier.tmdb.activity.presenter.MoviePresenter;
import com.tmdb.balvier.tmdb.activity.restservices.RetrofitClient;
import com.vstechlab.easyfonts.EasyFonts;

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
        TextView movieName, releaseDate, movieRating, movieOverview;
        ImageView moviePoster;
        ProgressBar moviePosterProgress;

        public MyViewHolder(View view) {
            super(view);
            moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
            movieName = (TextView) view.findViewById(R.id.movieName);
            movieOverview = (TextView) view.findViewById(R.id.movieOverview);
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
        holder.movieOverview.setText(movie.getOverview());
        holder.movieName.setTypeface(EasyFonts.robotoLight(context));
        holder.movieOverview.setTypeface(EasyFonts.robotoRegular(context));
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.movieOverview.setTypeface(EasyFonts.robotoThin(context));
        holder.movieRating.setText(movie.getAdult() ? "U/A" : "U");
        holder.movieOverview.setTypeface(EasyFonts.robotoThin(context));
       // holder.moviePosterProgress.setVisibility(View.VISIBLE);
        String url = RetrofitClient.IMAGE_BASE_URL + movie.getPosterPath();

        GlideApp.with(context)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                       // holder.moviePosterProgress.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                       // holder.moviePosterProgress.setVisibility(View.GONE);
                        return false;
                    }
                })
                .centerCrop()
                .into(holder.moviePoster);
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
        new MovieDetail().getMovieDetail(this, item.getId().toString());
    }

    @Override
    public void getMovieDetailSuccess(Response<MovieDetailResponse.MovieDetailClass> response, int responseCode) {
        Log.e("bvc", "getMovieDetailSuccess");
        ((MovieActivity) context).hideProgressBar();
        ((MovieActivity) context).onLoadDetailPage(response.body());
    }

    @Override
    public void errorGettingMovieDetail(String errormessage) {
        Log.e("bvc", "errorGettingMovieDetail");
        ((MovieActivity) context).hideProgressBar();
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
