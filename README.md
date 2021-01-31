# Flicks
Flicks is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

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
