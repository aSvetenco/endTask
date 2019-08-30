package com.sa.endtask.di.module

import android.content.res.Resources
import com.google.gson.Gson
import com.sa.endtask.R
import com.sa.endtask.api.Api
import com.sa.endtask.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun gson(): Gson = Gson()

    @Provides
    @Singleton
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    @BaseUrl
    fun baseUrl(res: Resources): String = res.getString(R.string.base_url)

    @Provides
    @Singleton
    fun retrofit(@BaseUrl baseUrl: String, client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun endApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    private companion object {
        const val REQUEST_TIMEOUT = 90L
    }
}