package com.marketkhoone.imdb.di

import com.marketkhoone.imdb.view.WelcomeActivity
import com.marketkhoone.imdb.view.welcome.onboarding.customView.OnBoardingView
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: OnBoardingView)
    fun inject(viewModel: WelcomeActivity)
}