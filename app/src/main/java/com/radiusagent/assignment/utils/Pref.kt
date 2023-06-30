package com.radiusagent.assignment.utils

import android.content.Context
import android.content.SharedPreferences
import com.radiusagent.assignment.App
import com.radiusagent.assignment.BuildConfig



class Pref() {
    private var preferences: SharedPreferences
    private val TAG = "Pref"

    init {
        preferences = App.instance.getSharedPreferences(PREFERENCENAME, MODE)
    }

    fun getValue(key: String, defaultValue: String = ""): String {
        return preferences.getString(key, defaultValue)!!
    }


    fun getLastUpdate(): Long {
        return preferences.getLong(LastUpdate, 0L)
    }

    fun setLastUpdate(value: Long) {
        val prefsPrivateEditor = preferences.edit()
        prefsPrivateEditor.putLong(LastUpdate, value)
        prefsPrivateEditor.apply()
    }




    companion object {
        const val PREFERENCENAME = BuildConfig.APPLICATION_ID
        const val LastUpdate = "last_update"
        private const val MODE = Context.MODE_PRIVATE
    }

}
