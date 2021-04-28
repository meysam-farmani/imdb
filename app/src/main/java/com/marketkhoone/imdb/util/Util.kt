package com.marketkhoone.imdb.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.entity.Director
import com.marketkhoone.imdb.model.entity.Genre

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
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?){
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("android:runtimeMins", "android:releaseState", "android:year")
fun setTextRuntimeAndRelease(view: TextView, runtimeMins: String?, releaseState : String?, year: String?){
    runtimeMins?.let {

        val string = "${runtimeMins} Minutes - ${releaseState} ${year}"
        view.setText(string)
    }
}

@BindingAdapter("android:directorListToString")
fun directorListToString(view: TextView, list: List<Director>?){
    list?.let {
        val stringList = list.map { it.name }
        var string = stringList.joinToString(separator = "  |  ")
        view.setText(string)
    }
}

@BindingAdapter("android:genreListToString")
fun genreListToString(view: TextView, list: List<Genre>?){
    list?.let {
        val stringList = list.map { it.value }
        var string = stringList.joinToString(separator = "  |  ")
        view.setText(string)
    }
}

@BindingAdapter("android:rateText")
fun rateText(view: TextView, rate: String?){
    rate?.let {
        if(it == ""){
            view.setText("0")
        }else{
            view.setText(rate)
        }
    }
}

@BindingAdapter("android:voteText")
fun voteText(view: TextView, vote: String?){
    vote?.let {
        if(it == ""){
            view.setText("(0)")
        }else{
            view.setText("($vote)")
        }
    }
}

@BindingAdapter("android:getYearFromDescription")
fun getYear(view: TextView, description: String?){
    description?.let {
        view.setText(it.substring(1,5))
    }
}