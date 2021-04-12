package com.marketkhoone.imdb.view.welcome.onboarding.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.marketkhoone.imdb.R

enum class OnBoardingPage(
    @StringRes val titleResource: Int,
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @DrawableRes val logoResource: Int
) {

    ONE(R.string.onboarding_slide1_title, R.string.onboarding_slide1_subtitle,R.string.onboarding_slide1_desc, R.drawable.ic_undraw_welcome_cats_imdb),
    TWO(R.string.onboarding_slide2_title, R.string.onboarding_slide2_subtitle,R.string.onboarding_slide2_desc, R.drawable.ic_undraw_home_cinema),
    THREE(R.string.onboarding_slide2_title, R.string.onboarding_slide3_subtitle,R.string.onboarding_slide3_desc, R.drawable.ic_undraw_horror_movie)
}