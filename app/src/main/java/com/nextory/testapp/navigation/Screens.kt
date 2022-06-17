package com.nextory.testapp.navigation

sealed class Screens(val route: String) {
    object BookList: Screens("BookList")
    object Detail: Screens("BookDetailScreen")
}
