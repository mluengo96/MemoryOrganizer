package com.mluengo

import android.app.Application
import com.mluengo.memoryorganizer.di.appModule
import com.mluengo.memoryorganizer.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class OrganizerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OrganizerApp)
            androidLogger()

            modules(appModule)
            modules(dataModule)
        }
    }
}