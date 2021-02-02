# Flicks
Flicks is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS

- [ ] Implement a shared element transition when user clicks into the details of a movie (1 point).
- [ ] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [ ] When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
  - [ ] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [ ] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
- [ ] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF

<img src="/walkthroughFlicks2.gif"><br>

### Notes

This section of development was straightforward. I implemented the basic requirements, as well as some extras. I added the year of release under the movie title on the details page. I also added a vote counter next to the ratings bar. Lastly, I added a back button in the bottom right, in case the user doesn't want to use the toolbar controls.

### Development Summary
-Used Parceler library to parcel Movie objects
-Sent Movie objects to separate details activity using Intents
-Unwrapped Movie object and initialized details fields
-Fetched JSON data on movie's trailer URL on YouTube
-Used the YouTubePlayerView library to load embedded video of trailer
-Added back button as mechanism to switch back to main activity

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
- [Parceler](https://github.com/johncarl81/parceler) - Library to help Parcel up object and send through Intents
- [YouTube Player API] (https://developers.google.com/youtube/android/player) - Library to allow for embedded YouTube player in activities

## Flicks Part 1

### User Stories

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [ ] (2pts) Views should be responsive for both landscape/portrait mode.
   - [ ] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [ ] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [ ] (2pts) Display a nice default [placeholder graphic] for each image during loading
- [ ] (2pts) Improved the user interface by experimenting with styling and coloring.
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF

<img src="/walkthroughFlicks.gif"><br>

### Notes
Understanding all of the interaction taking place between XML files, the RecyclerView and its adapter is still difficult for me to understand. In hindsight, I understand the process going on behind the scenes a little bit better, but I would struggle to reproduce the app from scratch.

### Development Summary
- Used 3rd-party Asynchronous HTTP client to retrieve JSON data on currently-playing movies through the MovieDB API
- Created Movie model class to encapsulate retrieved JSON data in a Movie object
- Loaded data into List of Movie objects to be displayed in a View
- Utilized RecyclerView to manage individual Movie items on the MainActivity
- Described Movie item layout through XML
- Used MovieAdapter to inflate XML into a View; defined how to bind data to View


### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
