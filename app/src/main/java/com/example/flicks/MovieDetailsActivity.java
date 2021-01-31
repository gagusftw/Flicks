package com.example.flicks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flicks.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends YouTubeBaseActivity {
    Movie movie;
    TextView tvDetailsTitle;
    TextView tvDetailsOverview;
    TextView tvDetailsRelease;
    RatingBar rbDetailsRating;
    TextView tvDetailsVotes;
    YouTubePlayerView ytDetailsPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        //Unwrap movie from the intent and assign reference
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        final String VIDEO_API_URL = String.format("https://api.themoviedb.org/3/movie/%s/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                movie.getId());

        tvDetailsTitle = findViewById(R.id.tvDetailsTitle);
        tvDetailsOverview = findViewById(R.id.tvDetailsOverview);
        tvDetailsRelease = findViewById(R.id.tvDetailsRelease);
        rbDetailsRating = findViewById(R.id.rbDetailsRating);
        tvDetailsVotes = findViewById(R.id.tvDetailsVotes);
        ytDetailsPlayer = findViewById(R.id.ytDetailsPlayer);

        Log.d("MovieDetailsActivity", String.format("Received and unwrapped '%s'", movie.getTitle()));

        tvDetailsTitle.setText(movie.getTitle());
        tvDetailsOverview.setText(movie.getOverview());
        tvDetailsRelease.setText(String.format("(%s)", movie.getRelease_year()));
        rbDetailsRating.setRating(movie.getVote_average().floatValue());
        tvDetailsVotes.setText(String.format("%d ratings", movie.getVote_count()));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(VIDEO_API_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                try {
                    JSONArray jsonArray = json.jsonObject.getJSONArray("results");
                    final String VIDEO_ID = jsonArray.getJSONObject(0).getString("key");
                    ytDetailsPlayer.initialize("AIzaSyBmCC4WmsV6vEAmkWCmL4jIyedn5-vfsz8", new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            youTubePlayer.cueVideo(VIDEO_ID);
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            Log.e("MovieDetailsActivity", "YouTubePlayer initialization error.");
                        }
                    });
                } catch (JSONException e) {
                    Log.e("MovieDetailsActivity", "JSON Exception caught while retrieving video data");
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.e("MovieDetailsActivity", "Async HTTP request error");
            }
        });
    }
}