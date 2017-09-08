package com.tmdb.balvier.tmdb.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.tmdb.balvier.tmdb.R;
import com.tmdb.balvier.tmdb.activity.fragments.MovieDetailFragment;
import com.tmdb.balvier.tmdb.activity.fragments.MovielistFragment;
import com.tmdb.balvier.tmdb.activity.modal.MovieDetailResponse;
import com.tmdb.balvier.tmdb.activity.modal.MovieListResponse;
import com.tmdb.balvier.tmdb.activity.presenter.MovieList;
import com.tmdb.balvier.tmdb.activity.presenter.MoviePresenter;

import retrofit2.Response;

public class MovieActivity extends AppCompatActivity implements MovielistFragment.OnFragmentInteractionListener,
        MoviePresenter.MovieListResponseCallback, MovieDetailFragment.OnFragmentInteractionListener {

    private ProgressBar progressbar;
    private MovieListResponse resultMovies;
    private MovieDetailResponse.MovieDetailClass movieDetailResponse;
    public static Activity ACTIVITY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (ApplicationClass.isTablet())
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_movie);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        showProgressBar();

        new MovieList().getMovieList(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LauncherActivity.killMe(ACTIVITY);
        ACTIVITY = null;
    }

    @Override
    public void onLoadDetailPage(MovieDetailResponse.MovieDetailClass movieDetailResponse) {
        this.movieDetailResponse = movieDetailResponse;
        loadMovieDetailFragment();
    }

    public static int rowPositipnClicked;

    void loadMovieDetailFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("movieDetail", movieDetailResponse);
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.setArguments(bundle);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction().
                setCustomAnimations(R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right,
                        R.anim.enter_from_left).add(getFrameContainerID(), movieDetailFragment,
                MovieDetailFragment.class.getSimpleName())
                .addToBackStack(MovieDetailFragment.class.getSimpleName()).commit();
        getSupportFragmentManager().executePendingTransactions();
    }

    int getFrameContainerID() {
        return !ApplicationClass.isTablet() ? R.id.movieListFrame : R.id.movieDetailFrame;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();

    }

    @Override
    public void onListItemClicked() {

    }

    @Override
    public void getMovieListSuccess(Response<MovieListResponse> response, int responseCode) {
        Log.e("bvc", "responseCode : " + responseCode);
        hideProgressBar();
        resultMovies = response.body();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movieList", resultMovies);
        MovielistFragment movielistFragment = new MovielistFragment();
        movielistFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.movieListFrame, movielistFragment, MovielistFragment.class.getSimpleName())
                .commit();

    }

    @Override
    public void errorGettingMovieList(String errormessage) {
        Log.e("bvc", "errormessage : " + errormessage);
        MovielistFragment movielistFragment = new MovielistFragment();
        hideProgressBar();
        getSupportFragmentManager().beginTransaction().add(R.id.movieListFrame, movielistFragment, MovielistFragment.class.getSimpleName())
                .commit();
    }

    public void showProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    public void hideProgressBar() {
        progressbar.setVisibility(View.GONE);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        rowPositipnClicked = -1;
        getSupportFragmentManager().popBackStack(MovieDetailFragment.class.getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
}
