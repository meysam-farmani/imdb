package com.marketkhoone.imdb.di

import com.marketkhoone.imdb.view.VideoActivity
import com.marketkhoone.imdb.view.WelcomeActivity
import com.marketkhoone.imdb.view.welcome.onboarding.customView.OnBoardingView
import com.marketkhoone.imdb.viewmodel.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, PrefsModule::class, AppModule::class])
interface ViewModelComponent {

    fun inject(viewModel: OnBoardingView)
    fun inject(viewModel: WelcomeActivity)
    fun inject(viewModel: VideoActivity)
    fun inject(viewModel: InTheatersViewModel)
    fun inject(viewModel: ComingSoonViewModel)
    fun inject(viewModel: YouTubeTrailerViewModel)
    fun inject(viewModel: TitleMovieViewModel)
    fun inject(viewModel: Top250MoviesViewModel)
    fun inject(viewModel: Top250TVsViewModel)
    fun inject(viewModel: MostPopularMoviesViewModel)
    fun inject(viewModel: MostPopularTVsViewModel)
    fun inject(viewModel: BoxOfficeViewModel)
    fun inject(viewModel: SearchTitleViewModel)
}