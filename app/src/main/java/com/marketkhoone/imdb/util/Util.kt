package com.marketkhoone.imdb.util

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.DirectorList
import com.marketkhoone.imdb.model.GenreList
import com.marketkhoone.imdb.model.JobItems
import com.marketkhoone.imdb.view.menu.widgets.T
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

fun getProgressDrawable(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        setColorSchemeColors(context.resources.getColor(R.color.main))
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.color.mainTransparent)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?){
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("android:runtimeMins", "android:releaseState", "android:year")
fun setTextRuntimeAndRelease(view: TextView, runtimeMins: Int?, releaseState : String?, year: Int?){
    runtimeMins?.let {

        val string = "${runtimeMins} Minutes - ${releaseState} ${year}"
        view.setText(string)
    }
}

@BindingAdapter("android:directorListToString")
fun directorListToString(view: TextView, list: List<DirectorList>?){
    list?.let {
        val stringList = list.map { it.name }
        var string = stringList.joinToString(separator = "  |  ")
        view.setText(string)
    }
}

@BindingAdapter("android:genreListToString")
fun genreListToString(view: TextView, list: List<GenreList>?){
    list?.let {
        val stringList = list.map { it.value }
        var string = stringList.joinToString(separator = "  |  ")
        view.setText(string)
    }
}