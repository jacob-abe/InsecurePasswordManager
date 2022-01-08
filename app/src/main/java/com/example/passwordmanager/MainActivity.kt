package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.example.passwordmanager.composable.HomePage
import com.example.passwordmanager.ui.theme.FirstComposeAppTheme
import com.example.passwordmanager.viewmodel.PrimaryReadsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val readViewModel by viewModels<PrimaryReadsViewModel>()

    private lateinit var homePage: HomePage

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePage = HomePage(readViewModel)
        setContent {
            FirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    homePage.getTabbedPage()
                }
            }
        }
    }
}