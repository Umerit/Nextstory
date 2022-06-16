package com.nextory.testapp.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nextory.testapp.navigation.NavGraph
import com.nextory.testapp.ui.theme.TestAppTheme


@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun TestApp() {
    TestAppTheme {
        val systemUiController = rememberSystemUiController()
        val useDarkIcons = !isSystemInDarkTheme()

        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = useDarkIcons
            )
        }

        val bottomSheetNavigator = rememberBottomSheetNavigator()
        val navController = rememberAnimatedNavController(bottomSheetNavigator)
        NavGraph(navController = navController)
    }
}
