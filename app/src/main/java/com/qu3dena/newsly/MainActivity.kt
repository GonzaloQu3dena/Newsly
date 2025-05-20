package com.qu3dena.newsly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.qu3dena.newsly.presentation.navigation.SetUpNavigation
import com.qu3dena.newsly.presentation.ui.theme.NewslyTheme

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewslyTheme {
                navController = rememberNavController()


                SetUpNavigation(navController)
            }
        }
    }
}

