package com.marketkhoone.imdb.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.entity.NewMovie
import com.marketkhoone.imdb.model.entity.NewMovieItem
import com.marketkhoone.imdb.model.entity.TopMovie
import com.marketkhoone.imdb.model.entity.TopMovieItem
import com.marketkhoone.imdb.viewmodel.Top250MoviesViewModel
import kotlinx.android.synthetic.main.fragment_in_theaters.*
import kotlinx.android.synthetic.main.fragment_top250_movies.*

class Top250MoviesFragment : Fragment(), TopMovieListAdapter.ClickListener {

    private lateinit var viewModel: Top250MoviesViewModel
    private val listAdapter = TopMovieListAdapter(arrayListOf(), this)
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top250_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(Top250MoviesViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner,movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObsever)
        viewModel.getData()

        movieListTop250Movies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        retryTop250Movies.setOnClickListener{
            viewModel.getData()
        }

    }

    private val movieDataListDataObserver = Observer<TopMovie> { list ->
        list?.let {
            movieListTop250Movies.visibility = View.VISIBLE
            it.items?.let{ items ->
                listAdapter.updateTopList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewTop250Movies.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorTop250Movies.visibility = View.GONE
            retryTop250Movies.visibility = View.GONE
            movieListTop250Movies.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorTop250Movies.visibility = if (isError) View.VISIBLE else View.GONE
        retryTop250Movies.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onClickListener(id: String?) {
        val activity = context as Activity
        (activity as MainActivity).navigateToMovieBookingFragment(id)
    }

}
