package com.example.trainingschedule.ui.app

import android.app.Application
import com.example.trainingschedule.ui.di.appModule
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}