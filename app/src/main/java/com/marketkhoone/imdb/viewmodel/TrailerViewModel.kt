package com.marketkhoone.imdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.marketkhoone.imdb.di.AppModule
import com.marketkhoone.imdb.di.CONTEXT_APP
import com.marketkhoone.imdb.di.DaggerViewModelComponent
import com.marketkhoone.imdb.di.TypeOfContext
import com.marketkhoone.imdb.model.FullCast
import com.marketkhoone.imdb.model.ImdbApiService
import com.marketkhoone.imdb.model.ImdbTrailer
import com.marketkhoone.imdb.util.SharedPreferencesHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrailerViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean = true): this(application){
        iniected = true
    }

    val trailerData by lazy { MutableLiveData<ImdbTrailer>() }
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

    fun getData(imdbId: String?){
        inject()
        loading.value = true

        val apiKey = prefs.getApiKey()
        getTrailer(apiKey, imdbId)
    }

    private fun getTrailer(apiKey: String, imdbId: String?){
        disposable.add(
            apiService.getTrailer(apiKey, imdbId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<ImdbTrailer>(){
                    override fun onSuccess(trailer: ImdbTrailer) {
                        loadError.value = false
                        trailerData.value = trailer
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        trailerData.value = null
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