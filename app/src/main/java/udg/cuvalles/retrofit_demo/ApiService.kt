package udg.cuvalles.retrofit_demo

import retrofit2.http.GET

interface ApiService {

    @GET("movies/classic")
    suspend fun getMovies(): List<Movie>
}