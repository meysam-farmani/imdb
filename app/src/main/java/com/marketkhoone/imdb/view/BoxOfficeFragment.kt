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
import com.marketkhoone.imdb.model.entity.BoxOfficeMovie
import com.marketkhoone.imdb.model.entity.PopularMovie
import com.marketkhoone.imdb.viewmodel.BoxOfficeViewModel
import kotlinx.android.synthetic.main.fragment_box_office.*
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*

class BoxOfficeFragment : Fragment(), BoxOfficeMovieListAdapter.ClickListener {

    private lateinit var viewModel: BoxOfficeViewModel
    private val listAdapter = BoxOfficeMovieListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box_office, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(BoxOfficeViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner,movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObsever)
        viewModel.getData()

        movieListBoxOfficeMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        retryBoxOfficeMovies.setOnClickListener{
            viewModel.getData()
        }

    }

    private val movieDataListDataObserver = Observer<BoxOfficeMovie> { list ->
        list?.let {
            movieListBoxOfficeMovies.visibility = View.VISIBLE
            it.items?.let{ items ->
                listAdapter.updateBoxOfficeList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewBoxOfficeMovies.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorBoxOfficeMovies.visibility = View.GONE
            retryBoxOfficeMovies.visibility = View.GONE
            movieListBoxOfficeMovies.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorBoxOfficeMovies.visibility = if (isError) View.VISIBLE else View.GONE
        retryBoxOfficeMovies.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onClickListener(id: String?) {
        val activity = context as Activity
        (activity as MainActivity).navigateToMovieBookingFragment(id)
    }

}