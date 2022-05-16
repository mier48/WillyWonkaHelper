package com.albertomier.willywonkahelper.core

import android.content.Context

class Preference(context: Context) {

    private val SHARED_NAME = "database"
    val SHARED_FILTER = "filter"
    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveFilters(str: String) {
        storage.edit().putString(SHARED_FILTER, str).apply()
    }

    fun getFilters(): String {
        return storage.getString(SHARED_FILTER, "")!!
    }
}