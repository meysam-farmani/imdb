package com.marketkhoone.imdb.viewmodel

import MovieData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.marketkhoone.imdb.di.AppModule
import com.marketkhoone.imdb.di.CONTEXT_APP
import com.marketkhoone.imdb.di.DaggerViewModelComponent
import com.marketkhoone.imdb.di.TypeOfContext
import com.marketkhoone.imdb.model.ImdbApiService
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Top250MoviesViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean = true): this(application){
        iniected = true
    }

    val top250Movies by lazy { MutableLiveData<MovieData>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable =  CompositeDisposable()

    @Inject
    lateinit var apiService: ImdbApiService

    @Inject
    @field:TypeOfContext(CONTEXT_APP)
    lateinit var prefs: SharedPreferencesHelper

    fun inject() {
        if(!iniected){
            DaggerViewModelComponent.builder()
                .appModule(AppModule(getApplication()))
                .build()
                .inject(this)
        }
    }

    private var iniected = false

    fun refresh(){
        inject()
        loading.value = true

        val apiKey = prefs.getApiKey()
        getTop250Movies(apiKey)
    }


    private fun getTop250Movies(apiKey: String){
        disposable.add(
            apiService.getTop250Movies(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<MovieData>(){
                    override fun onSuccess(movieData: MovieData) {
                        loadError.value = false
                        top250Movies.value = movieData
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                            e.printStackTrace()
                            loading.value = false
                            top250Movies.value = null
                            loadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}