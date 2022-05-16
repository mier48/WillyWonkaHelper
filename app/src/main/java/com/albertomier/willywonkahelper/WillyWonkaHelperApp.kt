package com.albertomier.willywonkahelper

import android.app.Application
import android.content.Context
import com.albertomier.willywonkahelper.core.Preference
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WillyWonkaHelperApp : Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var preference: Preference
        var instance: WillyWonkaHelperApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        preference = Preference(applicationContext)
        val context: Context = WillyWonkaHelperApp.applicationContext()
    }
}