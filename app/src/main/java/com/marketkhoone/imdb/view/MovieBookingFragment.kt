package com.marketkhoone.imdb.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.FragmentMovieBookingBinding
import com.marketkhoone.imdb.model.*
import com.marketkhoone.imdb.viewmodel.FullCastViewModel
import com.marketkhoone.imdb.viewmodel.InTheatersViewModel
import com.marketkhoone.imdb.viewmodel.TrailerViewModel
import com.marketkhoone.imdb.viewmodel.YouTubeTrailerViewModel
import kotlinx.android.synthetic.main.fragment_in_theaters.*
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class MovieBookingFragment : Fragment() {

    var newMovieItem: NewMovieItems? = null
    var videoId: String? = null
    private lateinit var dataBinding: FragmentMovieBookingBinding
    private val actorListAdapter = ActorListAdapter(arrayListOf())

    private lateinit var fullCastViewModel: FullCastViewModel
    private lateinit var youTubeTrailerViewModel: YouTubeTrailerViewModel
    private lateinit var trailerViewModel: TrailerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie_booking, container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val gson = Gson()

            val typeMovieItem = object : TypeToken<NewMovieItems>() {}.type
            newMovieItem = gson.fromJson<NewMovieItems>(
                it.getString(
                    "NewMovieItem",
                    ""
                ), typeMovieItem
            )
        }

        dataBinding.newMovieItem = newMovieItem

        actorListMovieBooking.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = actorListAdapter
        }

        fullCastViewModel = ViewModelProviders.of(this).get(FullCastViewModel::class.java)
        fullCastViewModel.fullCastData.observe(viewLifecycleOwner,fullCastDataObserver)
        fullCastViewModel.loading.observe(viewLifecycleOwner,fullCastLoadingLiveDataObserver)
        fullCastViewModel.loadError.observe(viewLifecycleOwner,fullCastErrorLiveDataObsever)
        fullCastViewModel.getData(newMovieItem?.id)

        youTubeTrailerViewModel = ViewModelProviders.of(this).get(YouTubeTrailerViewModel::class.java)
        youTubeTrailerViewModel.youTubeTrailerData.observe(viewLifecycleOwner,youTubeTrailerDataObserver)
        youTubeTrailerViewModel.loading.observe(viewLifecycleOwner,youTubeTrailerLoadingLiveDataObserver)
        youTubeTrailerViewModel.loadError.observe(viewLifecycleOwner,youTubeTrailerErrorLiveDataObsever)
        youTubeTrailerViewModel.getData(newMovieItem?.id)

        trailerViewModel = ViewModelProviders.of(this).get(TrailerViewModel::class.java)
        trailerViewModel.trailerData.observe(viewLifecycleOwner,trailerDataObserver)
        trailerViewModel.loading.observe(viewLifecycleOwner,trailerLoadingLiveDataObserver)
        trailerViewModel.loadError.observe(viewLifecycleOwner,trailerErrorLiveDataObsever)
        trailerViewModel.getData(newMovieItem?.id)

        trailerMovieBooking.setOnClickListener {
            val activity = context as Activity
            (activity as MainActivity).showVideo(videoId)
        }
    }

    private val fullCastDataObserver = Observer<FullCast> { fullCast ->
        fullCast?.let {
            it.actors?.let{ actors ->
                actorListAdapter.updateActorList(actors)
            }
        }
    }

    private val fullCastLoadingLiveDataObserver = Observer<Boolean> { isLoading ->
    }

    private val fullCastErrorLiveDataObsever = Observer<Boolean> { isError ->
    }

    private val youTubeTrailerDataObserver = Observer<YouTubeTrailer> { youTubeTrailer ->
        youTubeTrailer?.let {
            videoId = it.videoId
        }
    }

    private val youTubeTrailerLoadingLiveDataObserver = Observer<Boolean> { isLoading ->
    }

    private val youTubeTrailerErrorLiveDataObsever = Observer<Boolean> { isError ->
    }

    private val trailerDataObserver = Observer<ImdbTrailer> { trailer ->
        trailer?.let {
            dataBinding.trailer = it
        }
    }

    private val trailerLoadingLiveDataObserver = Observer<Boolean> { isLoading ->
    }

    private val trailerErrorLiveDataObsever = Observer<Boolean> { isError ->
    }
}