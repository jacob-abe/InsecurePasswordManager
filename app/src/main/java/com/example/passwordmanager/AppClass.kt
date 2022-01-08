package com.example.passwordmanager

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}