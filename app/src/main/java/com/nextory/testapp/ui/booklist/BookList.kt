package com.nextory.testapp.ui.booklist


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.nextory.testapp.R
import com.nextory.testapp.data.Book
import com.nextory.testapp.ui.components.ListItem
import com.nextory.testapp.ui.utils.rememberFlowWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)

@Composable
fun BookList(
    viewModelBookList: BookListViewModel,
    navigateToBookDetails: (book: Book) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var pagedBooks = rememberFlowWithLifecycle(viewModelBookList.booksLists())
        .collectAsLazyPagingItems()
    var searchBooks = rememberFlowWithLifecycle(viewModelBookList.searchBook(searchText))
        .collectAsLazyPagingItems()
    Scaffold(topBar = { BookListTopBar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            contentPadding = WindowInsets.safeDrawing
                .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
                .asPaddingValues()
        ) {
            stickyHeader {
                val keyboardController = LocalSoftwareKeyboardController.current!!
                val focusRequester = remember { FocusRequester() }
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    value = searchText,
                    onValueChange = {
                       searchText = it

                    },
                    placeholder = {
                        Text(text = stringResource(R.string.search_placeholder))
                    },
                    trailingIcon = {
                        AnimatedVisibility(visible = searchText.isNotEmpty()) {
                            IconButton(onClick = { searchText = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = stringResource(R.string.fav_iconfilled_close_content_description)
                                )
                            }
                        }
                    },
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController.hide()
                    }),
                    colors = TextFieldDefaults.textFieldColors()
                )
            }
            items(if(searchText.isNotEmpty()) searchBooks else pagedBooks) { book ->
                BookItem(book = book!!, navigateToBookDetails)
            }
        }
    }
}

@Composable
private fun BookListTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.booklist_title)) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        )
    )
}



@Composable
private fun BookItem(book: Book, onItemClicked: (book: Book) -> Unit = { }) {
    ListItem(
        modifier = Modifier.clickable {
            onItemClicked(book)
        },
        icon = {
            AsyncImage(
                model = book.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        },
        secondaryText = { Text(book.author) },
        trailing = {
            if (book.favorite) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.fav_icon_content_description)
                )
            }
        }
    ) {
        Text(book.title)
    }
}