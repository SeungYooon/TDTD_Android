package com.tdtd.voicepaper

import android.app.Application
import com.tdtd.presentation.util.DeviceInfo
import com.tdtd.presentation.util.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class TDTDApplication : Application() {

    companion object {
        lateinit var preferences: PreferenceManager
    }

    override fun onCreate() {
        preferences = PreferenceManager(applicationContext)
        preferences.saveToken(DeviceInfo(applicationContext).getDeviceId())
        super.onCreate()

        //Initialize Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}