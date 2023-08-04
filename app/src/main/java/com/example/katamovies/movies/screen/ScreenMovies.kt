package com.example.katamovies.movies.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.register.dtos.MovieD
import com.example.katamovies.utils.LoadImageFromUrl

@Composable
fun ScreenMovies(listOfMovies: List<MovieD>) {
    BuildSearchBar(listOfMovies)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuildSearchBar(listOfMovies: List<MovieD>) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
            text = ""
        },
        active = true,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                        text = ""
                    }
                )
            }
        }
    ) {
        Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            val filteredMovies = listOfMovies.filter {
                it.title.contains(text, ignoreCase = true)
            }
            Column {
                BuildBody(filteredMovies = filteredMovies)
            }
        }
    }
}

@Composable
fun BuildBody(filteredMovies: List<MovieD>) {
    LazyColumn {
        items(filteredMovies) { movie ->
            Card(
                modifier =
                Modifier
                    .padding(all = 14.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    movie.posterPath?.let { LoadImageFromUrl(url = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .graphicsLayer(
                                    translationX = 0f,
                                    translationY = 0f,
                                )

                        )
                    }
                    Column {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Movie description
                        Text(
                            text = buildAnnotatedString {
                                append(movie.overview)
                                withStyle(style = SpanStyle(textDecoration = androidx.compose.ui.text.style.TextDecoration.None)) {
                                    append("...") // Add the ellipsis
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            maxLines = 6,
                            overflow = TextOverflow.Ellipsis
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Movie ranking
                        val rankingText = buildAnnotatedString {
                            append("Ranking: ")
                            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                                append(movie.voteAverage.toString())
                            }
                        }

                        Text(
                            text = rankingText,
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

