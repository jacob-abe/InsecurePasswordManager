package com.example.passwordmanager.composable

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.passwordmanager.viewmodel.PrimaryReadsViewModel
import dagger.hilt.android.AndroidEntryPoint

class HomePage(viewModel: PrimaryReadsViewModel) {

    @Composable
    public fun getTabbedPage(){
        Text(text = "Home page")
    }

}