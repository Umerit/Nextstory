package com.nextory.testapp.navigation

import java.io.Serializable

sealed class Screens(val route: String) {
    object BookList: Screens("BookList")
    object Detail: Screens("DetailScreen?author={author}&description={description}")
}
