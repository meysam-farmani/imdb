package com.marketkhoone.imdb.view.welcome.onboarding.customView

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.viewpager2.widget.ViewPager2
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.di.AppModule
import com.marketkhoone.imdb.di.CONTEXT_APP
import com.marketkhoone.imdb.di.DaggerViewModelComponent
import com.marketkhoone.imdb.di.TypeOfContext
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import com.marketkhoone.imdb.view.MainActivity
import com.marketkhoone.imdb.view.WelcomeActivity
import com.marketkhoone.imdb.view.welcome.OnBoardingPagerAdapter
import com.marketkhoone.imdb.view.welcome.core.setParallaxTransformation
import com.marketkhoone.imdb.view.welcome.onboarding.entity.OnBoardingPage
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.onboarding_view.view.*
import javax.inject.Inject


class OnBoardingView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }

    @Inject
    @field:TypeOfContext(CONTEXT_APP)
    lateinit var prefs: SharedPreferencesHelper

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
        setUpSlider(view)
        addingButtonsClickListeners()
        DaggerViewModelComponent.builder()
            .appModule(AppModule(context.applicationContext as Application))
            .build()
            .inject(this)
    }

    private fun setUpSlider(view: View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSlideChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }


    private fun addSlideChangeListener() {

        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }

    private fun addingButtonsClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
        }
        startBtn.setOnClickListener {
            setFirstTimeLaunchToFalse()
        }
    }

    private fun setFirstTimeLaunchToFalse() {
        prefs.isFirstTimeLaunch = false
        navigateToMainActivity()
    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }

    private fun navigateToMainActivity() {
        val activity = context as Activity
        (activity as WelcomeActivity).navigateToMainActivity()
    }
}