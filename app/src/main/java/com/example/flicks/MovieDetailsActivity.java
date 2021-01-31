package com.example.flicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.flicks.models.Movie;

import org.parceler.Parcels;

public class MovieDetailsActivity extends AppCompatActivity {
    Movie movie;
    TextView tvDetailsTitle;
    TextView tvDetailsOverview;
    RatingBar rbDetailsRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        tvDetailsOverview = findViewById(R.id.tvDetailsOverview);
        rbDetailsRating = findViewById(R.id.rbDetailsRating);

        //Unwrap movie from the intent and assign reference
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Received and unwrapped '%s'", movie.getTitle()));

        tvDetailsTitle.setText(movie.getTitle());
        tvDetailsOverview.setText(movie.getOverview());
        rbDetailsRating.setRating(movie.getVote_average().floatValue());
    }
}