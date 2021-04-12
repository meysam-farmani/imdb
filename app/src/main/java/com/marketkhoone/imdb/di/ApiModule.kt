package com.marketkhoone.imdb.di

import com.marketkhoone.imdb.model.ImdbApi
import com.marketkhoone.imdb.model.ImdbApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiModule {
    private val BASE_URL = "https://imdb-api.com/en/API"

    @Provides
    fun provideImdbApi(): ImdbApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ImdbApi::class.java)
    }

    @Provides
    open fun provideImdbApiService() : ImdbApiService {
        return ImdbApiService()
    }
}