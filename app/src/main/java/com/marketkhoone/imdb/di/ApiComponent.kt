package com.marketkhoone.imdb.di

import com.marketkhoone.imdb.model.ImdbApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: ImdbApiService)
}