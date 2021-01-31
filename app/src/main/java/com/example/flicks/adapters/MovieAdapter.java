package com.example.flicks.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flicks.MovieDetailsActivity;
import com.example.flicks.R;
import com.example.flicks.models.Movie;

import org.parceler.Parcels;

import java.util.List;

//Used as an adapter to the RecyclerView we will implement
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.entry_movie, parent, false);
        return new ViewHolder(view);
    }

    //Used to bind the data to the ViewHolder; each time the view comes into screen
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //Setup the listener to switch activities

            //Linking member data with XML elements
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        //Helper function to initialize the member data for each ViewHolder
        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            //Using Glide library to render the images
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                //Get movie at position corresponding to View
                Movie movie = movies.get(position);
                //Create intent to switch control to MovieDetailsActivity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                //Wrap up movie object to send to MovieDetailsActivity
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                //Switch control to MovieDetailsActivity
                context.startActivity(intent);
            }
        }
    }
}
