package com.radiusagent.assignment.api

import com.onetouch.businesssolution.BASE_URL
import okhttp3.OkHttpClient
import com.radiusagent.assignment.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit{
    companion object Factory {
        fun create(): Api? {
            val levelType: Level
            if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                levelType = Level.BODY else levelType = Level.NONE

            val logging = HttpLoggingInterceptor()
            logging.setLevel(levelType)

            val okhttpClient = OkHttpClient.Builder()
            okhttpClient.addInterceptor(logging)

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}