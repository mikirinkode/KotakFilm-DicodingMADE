package com.mikirinkode.kotakmovie.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mikirinkode.kotakmovie.R
import com.mikirinkode.kotakmovie.core.domain.model.Catalogue
import com.mikirinkode.kotakmovie.core.utils.Constants
import com.mikirinkode.kotakmovie.core.utils.MovieStatus

@Composable
fun MovieListComponent(
    list: List<Catalogue>,
    navigateToDetail: (String, Boolean, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(list) { movie ->
            MovieItem(
                imageUrl = "${Constants.IMAGE_BASE_URL}${movie.posterPath}",
                title = movie.title ?: stringResource(id = R.string.no_data),
                releaseDate = movie.releaseDate ?: stringResource(id = R.string.no_data),
                rating = movie.voteAverage,
                onClick = {
                    navigateToDetail(MovieStatus.NORMAL, movie.isTvShow ,movie.id)
                }
            )
        }
    }
}