package com.example.flicks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flicks.adapters.MovieAdapter;
import com.example.flicks.models.Movie;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String MOVIES_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";
    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Setting up the RecyclerView reference
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        //Setting adapter and LayoutManager for RecyclerView
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        //Creating asynchronous client to fetch movie data as JSON
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(MOVIES_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d(TAG, "Successful JSON Request");  //Log success

                try {
                    //Retrieving JSONArray of movies currently in theatres
                    JSONArray moviesJSONArray = json.jsonObject.getJSONArray("results");
                    Log.i(TAG, "MoviesJSONArray: " + moviesJSONArray.toString());

                    //Adding data from JSONArray into List<Movie>
                    movies.addAll(Movie.getMoviesList(moviesJSONArray));
                    movieAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Movies List: " + movies.size());
                } catch (JSONException e) {
                    Log.e(TAG, "JSONException");
                }
            }

            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d(TAG, "JSON Request FAIL");
            }
        });
    }
}