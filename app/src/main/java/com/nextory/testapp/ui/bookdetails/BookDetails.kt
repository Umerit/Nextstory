package com.nextory.testapp.ui.bookdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nextory.testapp.R
import com.nextory.testapp.data.Book


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun BookDetails(
    book: Book?,
    navigateToBookList: () -> Unit,
    onFavorite: () -> Unit
) {
    Scaffold(topBar = {
        BookDetailsTopBar(book?.title, navigateToBookList, onFavorite, book?.favorite ?: false)
    }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            AsyncImage(
                model = book?.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                book?.author.orEmpty(),
                fontSize = 30.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Text(
                book?.description.orEmpty(),
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp, top = 15.dp)
            )

        }
    }
}

@Composable
private fun BookDetailsTopBar(
    title: String?,
    navigateToBookList: () -> Unit,
    onFavoriteClick: () -> Unit,
    favorite: Boolean
) {
    var isFavorite by rememberSaveable { mutableStateOf(favorite) }
    CenterAlignedTopAppBar(
        title = { Text(title.orEmpty()) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        ),
        navigationIcon = {
            IconButton(onClick = { navigateToBookList() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back_button_content_description)
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onFavoriteClick()
                isFavorite = !isFavorite
            }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.add_book_to_fav_list)
                )
            }
        }
    )
}

