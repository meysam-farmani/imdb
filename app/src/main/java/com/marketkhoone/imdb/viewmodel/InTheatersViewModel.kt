package com.marketkhoone.imdb.viewmodel

import com.marketkhoone.imdb.model.entity.NewMovie
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.marketkhoone.imdb.di.*
import com.marketkhoone.imdb.model.ImdbApiService
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InTheatersViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean = true): this(application){
        iniected = true
    }

    val movieDataList by lazy { MutableLiveData<NewMovie>() }
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
                .apiModule(ApiModule(getApplication()))
                .build()
                .inject(this)
        }
    }

    private var iniected = false

    fun getData(){
        inject()
        loading.value = true

        val apiKey = prefs.getApiKey()
        getInTheaters(apiKey)
    }

    private fun getInTheaters(apiKey: String){
        disposable.add(
            apiService.getInTheaters(apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<NewMovie>(){
                    override fun onSuccess(newMovie: NewMovie) {
                        loadError.value = false
                        movieDataList.value = newMovie
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        movieDataList.value = null
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