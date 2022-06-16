package com.nextory.testapp.ui.bookdetails

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextory.testapp.R


@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun BookDetails(
    author: String?,
    description: String?,
    imageURL: String?,
    navigateToBookList: () -> Unit
) {
    Scaffold(topBar = { BookDetailsTopBar(navigateToBookList) }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            ImageCard()
            Text(author.toString() ,
                fontSize = 40.sp,
                modifier = Modifier.padding(start = 16.dp))
            Text("Book Description" ,
                fontSize = 40.sp,
                        modifier = Modifier.padding(start = 16.dp, top = 15.dp))

}
    }
}

@ExperimentalMaterial3Api
@Composable
fun ImageCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(15.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "image.text"
        )
    }
}
//Image(
//painter = painterResource(id = R.drawable.ic_launcher_background),
//contentDescription = "image.text"
//)

@Composable
private fun BookDetailsTopBar(navigateToBookList: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(id = R.string.booklist_title)) },
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        ),
        navigationIcon = {
            IconButton(onClick = { navigateToBookList()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Button"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Add to book to Favorite list"
                )
            }
        }
    )
}

