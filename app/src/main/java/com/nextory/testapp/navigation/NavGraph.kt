package com.nextory.testapp.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.map
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.nextory.testapp.data.Book
import com.nextory.testapp.ui.bookdetails.BookDetailedListViewModel
import com.nextory.testapp.ui.bookdetails.BookDetails
import com.nextory.testapp.ui.booklist.BookList
import com.nextory.testapp.ui.booklist.BookListViewModel
import com.nextory.testapp.ui.utils.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map


@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.BookList.route
    )
    {
        composable(route = Screens.BookList.route) {
            BookListDestination(navController = navController)
        }
        composable(route = Screens.Detail.route) {
            val book = navController.previousBackStackEntry?.arguments?.getSerializable("book") as Book?
            BookDetailDestination(navController = navController, book = book)
        }

    }
}

@Composable
private fun BookListDestination(
    navController: NavHostController,
) {
    val bookListViewModel: BookListViewModel = hiltViewModel()

    BookList(
        viewModelBookList= bookListViewModel,
        navigateToBookDetails = {
            navController.currentBackStackEntry?.arguments?.putSerializable("book", it)
            navController.navigate(Screens.Detail.route)
        }
    )
}

@Composable
private fun BookDetailDestination(
    navController: NavHostController,
    book: Book?
) {
    val bookListViewModel: BookDetailedListViewModel = hiltViewModel()
    BookDetails(
        navigateToBookList = {
            navController.navigate(Screens.BookList.route)
        },
        onFavorite = {
            book?.let {
                bookListViewModel.updateBook(it.copy(favorite = !it.favorite))
            }
        },
        book = book
    )
}
