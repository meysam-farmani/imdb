package com.marketkhoone.imdb.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.FragmentMovieBookingBinding
import com.marketkhoone.imdb.model.entity.MovieTitle
import com.marketkhoone.imdb.model.entity.NewMovie
import com.marketkhoone.imdb.model.entity.NewMovieItem
import com.marketkhoone.imdb.model.entity.YouTubeTrailer
import com.marketkhoone.imdb.viewmodel.TitleMovieViewModel
import com.marketkhoone.imdb.viewmodel.YouTubeTrailerViewModel
import kotlinx.android.synthetic.main.fragment_coming_soon.*
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class MovieBookingFragment : Fragment() {

    var imdbId: String? = null
    var isFromMainActivity: Boolean = true
    var videoId: String? = null
    private lateinit var dataBinding: FragmentMovieBookingBinding
    private val actorListAdapter = ActorListAdapter(arrayListOf())

    private lateinit var youTubeTrailerViewModel: YouTubeTrailerViewModel
    private lateinit var titleMovieViewModel: TitleMovieViewModel

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
            imdbId = it.getString("ImdbId")
            isFromMainActivity = it.getBoolean("isFromMainActivity", false)
        }

        actorListMovieBooking.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = actorListAdapter
        }

        titleMovieViewModel = ViewModelProviders.of(this).get(TitleMovieViewModel::class.java)
        titleMovieViewModel.movieData.observe(viewLifecycleOwner,trailerDataObserver)
        titleMovieViewModel.loading.observe(viewLifecycleOwner,movieTitleLoadingLiveDataObserver)
        titleMovieViewModel.loadError.observe(viewLifecycleOwner,movieTitleErrorLiveDataObsever)
        titleMovieViewModel.getData(imdbId)

        youTubeTrailerViewModel = ViewModelProviders.of(this).get(YouTubeTrailerViewModel::class.java)
        youTubeTrailerViewModel.youTubeTrailerData.observe(viewLifecycleOwner,youTubeTrailerDataObserver)
        youTubeTrailerViewModel.loading.observe(viewLifecycleOwner,youTubeTrailerLoadingLiveDataObserver)
        youTubeTrailerViewModel.loadError.observe(viewLifecycleOwner,youTubeTrailerErrorLiveDataObsever)
        youTubeTrailerViewModel.getData(imdbId)

        trailerMovieBooking.setOnClickListener {
            val activity = context as Activity

            if(isFromMainActivity){
                (activity as MainActivity).showVideo(videoId)
            }else{
                (activity as SearchActivity).showVideo(videoId)
            }
        }

        retryMovieBooking.setOnClickListener{
            titleMovieViewModel.getData(imdbId)
            youTubeTrailerViewModel.getData(imdbId)
        }
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

    private val trailerDataObserver = Observer<MovieTitle> { movieTitle ->
        movieTitle?.let {
            movieBookongLayout.visibility = View.VISIBLE

            it.imDbRating?.let { rate ->
                if(rate == ""){
                    simpleRatingBar.rating = 0f
                }else{
                    simpleRatingBar.rating = rate.toFloat() / 2
                }
            }

            dataBinding.movieTitle = it

            it.actorList?.let{ actros ->
                actorListAdapter.updateActorList(actros)
            }
        }
    }

    private val movieTitleLoadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewMovieBooking.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorMovieBooking.visibility = View.GONE
            retryMovieBooking.visibility = View.GONE
            movieBookongLayout.visibility = View.GONE
        }
    }

    private val movieTitleErrorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorMovieBooking.visibility = if (isError) View.VISIBLE else View.GONE
        retryMovieBooking.visibility = if (isError) View.VISIBLE else View.GONE
    }
}