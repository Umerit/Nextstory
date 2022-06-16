package com.nextory.testapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.nextory.testapp.ui.bookdetails.BookDetailedListViewModel
import com.nextory.testapp.ui.bookdetails.BookDetails
import com.nextory.testapp.ui.booklist.BookList
import com.nextory.testapp.ui.booklist.BookListViewModel
import com.nextory.testapp.ui.utils.rememberFlowWithLifecycle


@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun NavGraph (navController: NavHostController){
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.BookList.route)
    {
        composable(route = Screens.BookList.route){
            BookListDestination(navController = navController)
        }
        composable(route = Screens.Detail.route){
            BookDetailDestination(navController = navController)
        }
    }
}

@Composable
private fun BookListDestination(navController: NavHostController) {
    val bookListViewModel : BookListViewModel = hiltViewModel()
    val pagedBooks = rememberFlowWithLifecycle(bookListViewModel.pagedBooks)
        .collectAsLazyPagingItems()
        BookList(
        pagedBooks = pagedBooks,
        onSearchTextChanged = {
        },
            navigateToBookDetails = {
                navController.navigate(Screens.Detail.route)
            }
        )
}
@Composable
private fun BookDetailDestination(navController: NavHostController) {
    val bookListViewModel : BookDetailedListViewModel = hiltViewModel()
    BookDetails(
        navigateToBookList = {
            navController.navigate(Screens.BookList.route)
        }
    )
}

