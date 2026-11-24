package udg.cuvalles.retrofit_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import udg.cuvalles.retrofit_demo.ui.theme.RetroFit_DemoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RetroFit_DemoTheme {
                MovieList()
            }
        }
    }
}

@Composable
fun MovieList() {

    var movies by remember {
        mutableStateOf<List<Movie>>(emptyList())
    }

    LaunchedEffect(Unit) {
        try {
            movies = RetrofitClient.api.getMovies()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Películas clásicas",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(movies.take(10)) { movie ->
                MovieItem(movie)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "ID: ${movie.id}",
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                text = "IMDb: ${movie.imdbId}"
            )
        }
    }
}