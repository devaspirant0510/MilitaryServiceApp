package dev.seh.militaryserviceapp.app;

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
@HiltAndroidApp
class MyApplication :Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}