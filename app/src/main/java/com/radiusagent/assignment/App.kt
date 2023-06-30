package com.radiusagent.assignment

import android.app.Application
import com.radiusagent.assignment.realm.RealmDb
import io.realm.Realm
import io.realm.RealmConfiguration


class App :Application(){
    private  var config:RealmConfiguration?=null
    override fun onCreate() {
        super.onCreate()
        instance = this
        RealmDb.init(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }

}