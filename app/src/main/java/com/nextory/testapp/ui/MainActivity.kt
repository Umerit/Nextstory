package com.nextory.testapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(
    ExperimentalMaterialNavigationApi::class,
    ExperimentalAnimationApi::class
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            TestApp()
        }
    }
}
