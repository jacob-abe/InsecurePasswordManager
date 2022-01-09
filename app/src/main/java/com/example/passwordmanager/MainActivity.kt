package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    ActivityWindow()
                }
            }
        }
    }

    @Preview
    @Composable
    private fun ActivityWindow(){
        MaterialTheme() {
            Column(modifier = Modifier.fillMaxWidth()) {

                TopAppBar(title = {
                    Text("Insecure password manager")
                })

                Spacer(modifier = Modifier.size(16.dp))

                Column(modifier = Modifier.padding(16.dp,0.dp)) {
                    homePage.GetPasswordList()
                }
            }
        }
    }
}