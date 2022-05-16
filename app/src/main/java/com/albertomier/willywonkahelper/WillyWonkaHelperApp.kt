package com.albertomier.willywonkahelper

import android.app.Application
import com.albertomier.willywonkahelper.core.Preference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WillyWonkaHelperApp : Application() {

    companion object {
        lateinit var preference: Preference
    }

    override fun onCreate() {
        super.onCreate()

        preference = Preference(applicationContext)
    }
}