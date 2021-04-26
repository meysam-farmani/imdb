package com.marketkhoone.imdb.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.entity.PopularMovie
import com.marketkhoone.imdb.viewmodel.MostPopularTVsViewModel
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*
import kotlinx.android.synthetic.main.fragment_most_popular_tvs.*

class MostPopularTVsFragment : Fragment(), PopularMovieListAdapter.ClickListener {

    private lateinit var viewModel: MostPopularTVsViewModel
    private val listAdapter = PopularMovieListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_tvs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MostPopularTVsViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner,movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObsever)
        viewModel.getData()

        movieListMostPopularTVs.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        retryMostPopularTVs.setOnClickListener{
            viewModel.getData()
        }

    }

    private val movieDataListDataObserver = Observer<PopularMovie> { list ->
        list?.let {
            movieListMostPopularTVs.visibility = View.VISIBLE
            it.items?.let{ items ->
                listAdapter.updatePopularList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewMostPopularTVs.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorMostPopularTVs.visibility = View.GONE
            retryMostPopularTVs.visibility = View.GONE
            movieListMostPopularTVs.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorMostPopularTVs.visibility = if (isError) View.VISIBLE else View.GONE
        retryMostPopularTVs.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onClickListener(id: String?) {
        val activity = context as Activity
        (activity as MainActivity).navigateToMovieBookingFragment(id)
    }
}