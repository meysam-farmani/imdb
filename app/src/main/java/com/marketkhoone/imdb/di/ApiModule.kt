package com.marketkhoone.imdb.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.marketkhoone.imdb.model.ImdbApi
import com.marketkhoone.imdb.model.ImdbApiService
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


@Module
open class ApiModule(val app: Application) {
    private val BASE_URL = "https://imdb-api.com/en/API/"

    var onlineInterceptor: Interceptor = object : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response? {
            val response: Response = chain.proceed(chain.request())
            val maxAge = 60 * 60 * 24 // read from cache for 1 day even if there is internet connection
            return response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma")
                .build()
        }
    }

    var offlineInterceptor = Interceptor { chain ->
        var request: Request = chain.request()
        if (!isInternetAvailable(app)) {
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        chain.proceed(request)
    }

    var cacheSize = 10 * 1024 * 1024 // 10 MB

    var cache: Cache = Cache(app.getCacheDir(), cacheSize.toLong())

    var okHttpClient =
        OkHttpClient.Builder() // .addInterceptor(provideHttpLoggingInterceptor()) // For HTTP request & Response data logging
            .addInterceptor(offlineInterceptor)
            .addNetworkInterceptor(onlineInterceptor)
            .cache(cache)
            .build()

    open fun isInternetAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting
    }

    @Provides
    fun provideImdbApi(): ImdbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ImdbApi::class.java)
    }

    @Provides
    open fun provideImdbApiService() : ImdbApiService {
        return ImdbApiService(app)
    }
}