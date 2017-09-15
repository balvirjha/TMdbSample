package com.tmdb.balvier.tmdb.activity.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.ApplicationClass;
import com.tmdb.balvier.tmdb.activity.MovieActivity;
import com.tmdb.balvier.tmdb.activity.PlayerActivity;
import com.tmdb.balvier.tmdb.activity.modal.GlideApp;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.Item;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.Snippet;
import com.tmdb.balvier.tmdb.activity.modal.youtuberesponse.YoutubeResponse;

import java.util.List;

/**
 * Created by Balvier on 9/15/2017.
 */

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.MovieTrailerHolder> implements View.OnClickListener {

    private List<YoutubeResponse> youtubeResponseList;
    private RecyclerView recyclerView;
    private Activity context;

    public class MovieTrailerHolder extends RecyclerView.ViewHolder {
        TextView trailerTitle, trailerDescription, trailerViews, trailerViewsDislikes, trailerLikes;
        ImageView moviePoster;
        ProgressBar moviePosterProgress;

        public MovieTrailerHolder(View view) {
            super(view);
            moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
            trailerTitle = (TextView) view.findViewById(R.id.trailerTitle);
            trailerDescription = (TextView) view.findViewById(R.id.trailerDescription);
            trailerViews = (TextView) view.findViewById(R.id.trailerViews);
            trailerViewsDislikes = (TextView) view.findViewById(R.id.trailerViewsDislikes);
            trailerLikes = (TextView) view.findViewById(R.id.trailerLikes);
            //moviePosterProgress = (ProgressBar) view.findViewById(R.id.moviePosterProgress);
        }

    }

    public MovieTrailerAdapter(List<YoutubeResponse> youtubeResponseList, Activity context, RecyclerView recyclerView) {
        this.youtubeResponseList = youtubeResponseList;
        this.context = context;
        this.recyclerView = recyclerView;
    }


    @Override
    public MovieTrailerAdapter.MovieTrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_trailer_row, parent, false);
        itemView.setOnClickListener(this);

        return new MovieTrailerAdapter.MovieTrailerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieTrailerAdapter.MovieTrailerHolder holder, int position) {
        YoutubeResponse youtubeResponse = youtubeResponseList.get(position);
        Item item = youtubeResponse.getItems().get(0);
        Snippet snippet = item.getSnippet();
        holder.trailerTitle.setText(snippet.getTitle());
        holder.trailerDescription.setText(snippet.getDescription());
        holder.trailerViewsDislikes.setText(item.getStatistics().getDislikeCount());
        holder.trailerLikes.setText(item.getStatistics().getLikeCount());
        holder.trailerViews.setText(item.getStatistics().getViewCount());
        final String url = snippet.getThumbnails().getHigh().getUrl();
        if (ApplicationClass.isTablet()) {
            GlideApp.with(context)
                    .load(url)
                    .error(R.drawable.error)
                    .centerCrop()
                    .into(holder.moviePoster);
        } else {
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
        context.startActivity(new Intent(context, PlayerActivity.class).putExtra("trialerid", youtubeResponseList.get(itemPosition).getItems().get(0).getId()));
    }


    @Override
    public int getItemCount() {
        return youtubeResponseList.size();
    }
}
