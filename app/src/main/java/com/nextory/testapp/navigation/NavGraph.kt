package com.nextory.testapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
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
fun NavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.BookList.route
    )
    {
        composable(route = Screens.BookList.route) {
            BookListDestination(navController = navController)
        }
        composable(route = Screens.Detail.route,
            arguments = listOf(
                navArgument("author") {
                    type = NavType.StringType
                }, navArgument("description") {
                    type = NavType.StringType
                }, navArgument("imageUrl") {
                    type = NavType.StringType
                })
        ) { backStackEntry ->
            BookDetailDestination(
                navController = navController,
                author = backStackEntry.arguments?.getString("author"),
                description = backStackEntry.arguments?.getString("description"),
                imageURL = backStackEntry.arguments?.getString("imageUrl")
            )
        }

    }
}

@Composable
private fun BookListDestination(
    navController: NavHostController,
) {
    val bookListViewModel: BookListViewModel = hiltViewModel()
    val pagedBooks = rememberFlowWithLifecycle(bookListViewModel.pagedBooks)
        .collectAsLazyPagingItems()
    BookList(
        pagedBooks = pagedBooks,
        onSearchTextChanged = {
        },
        navigateToBookDetails = {
            navController.navigate(
                Screens.Detail.route + "?"+"author=${it.author}&"+"description=${it.description}&"+"imageUrl=${it.imageUrl}"
            )
        }
    )
}

@Composable
private fun BookDetailDestination(
    navController: NavHostController,
    author: String?,
    description: String?,
    imageURL: String?
) {
    val bookListViewModel: BookDetailedListViewModel = hiltViewModel()
    BookDetails(
        navigateToBookList = {
            navController.navigate(Screens.BookList.route)
        },
        author = author,
        description=description,
        imageURL = imageURL
    )
}

