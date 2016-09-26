package com.angelgomezsalazar.kotlinexample.modules

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by angelgomez on 9/13/16.
 */
@Module
class NetworkModule(val baseUrl: String) {

    // Generates HTTP Cache
    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    // Add options to GSON here if you need them
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    // Provides us with cached responses if there is no network available, remove if you don' want
    // it
    @Provides
    @Singleton
    fun provideInterceptor(application: Application): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()
            if (isNetworkAvailable(application)) {
                request = request.newBuilder().header("Cache-Control", "public, max-age=" + 60).build()
            } else {
                request = request.newBuilder().header("Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
            }
            chain.proceed(request)
        }
    }

    // Creates OkHttpClient we will be using with retrofit, customize here if you neeed to
    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder().cache(cache).addInterceptor(interceptor).build()
        return client
    }

    // Retrofit provider, you can change the converter factory (don't forget to add a provider for
    // that factory)
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder().client(okHttpClient).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit
    }

    private fun isNetworkAvailable(application: Application): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}
