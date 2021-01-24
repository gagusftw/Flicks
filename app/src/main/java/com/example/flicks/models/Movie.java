package com.example.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//Defines one movie object that will be displayed in the RecyclerView in MainActivity
public class Movie {
    public String posterPath;   //Relative path to the image of movie poster
    public String title;    //Movie title
    public String overview; //Summary of movie

    //Movie constructor; takes in JSONObject from moviesJSONArray
    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
    }

    //Static method to facilitate loading List of Movies with JSON data
    public static List<Movie> getMoviesList(JSONArray moviesJSONArray) throws JSONException{
        List<Movie> movies = new ArrayList<>();
        for(int i = 0; i < moviesJSONArray.length(); i++){
            movies.add(new Movie(moviesJSONArray.getJSONObject(i)));
        }

        return movies;
    }

    //Returns absolute path of the poster
    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w342" + posterPath;
    }

    //Returns movie title
    public String getTitle() {
        return title;
    }

    //Returns movie summary
    public String getOverview() {
        return overview;
    }
}
