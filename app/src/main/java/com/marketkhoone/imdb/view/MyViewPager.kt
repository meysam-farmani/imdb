package com.marketkhoone.imdb.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent

import androidx.viewpager.widget.ViewPager


class MyViewPager : ViewPager {
    private var enableSwipe = false

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    private fun init() {
        enableSwipe = true
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return enableSwipe && super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Never allow swiping to switch between pages
        return enableSwipe && super.onTouchEvent(event)
    }

    fun setEnableSwipe(enableSwipe: Boolean) {
        this.enableSwipe = enableSwipe
    }
}