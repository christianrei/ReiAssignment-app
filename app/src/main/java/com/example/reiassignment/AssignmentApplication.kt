package com.example.reiassignment

import android.app.Application
import com.example.reiassignment.main.di.mainModule
import com.example.reiassignment.postviewer.di.postViewerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AssignmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AssignmentApplication)
            modules(listOf(mainModule, postViewerModule))
        }
    }

}