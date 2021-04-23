package com.marketkhoone.imdb.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.NewMovieItems
import com.marketkhoone.imdb.model.NewMovie
import com.marketkhoone.imdb.viewmodel.InTheatersViewModel
import kotlinx.android.synthetic.main.fragment_in_theaters.*


class InTheatersFragment : Fragment(), PagerCardsListAdapter.ClickListener {

    private lateinit var viewModel: InTheatersViewModel
    private val listAdapter = PagerCardsListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_in_theaters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerInTheaters.adapter = listAdapter

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        viewPagerInTheaters.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }

        viewModel = ViewModelProviders.of(this).get(InTheatersViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner,movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObsever)
        viewModel.refresh()

        retryInTheaters.setOnClickListener{
            viewModel.refresh()
        }

    }

    private val movieDataListDataObserver = Observer<NewMovie> { list ->
        list?.let {
            viewPagerInTheaters.visibility = View.VISIBLE
            it.items?.let{ items ->
                listAdapter.updatePagerCardsList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewInTheaters.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorInTheaters.visibility = View.GONE
            retryInTheaters.visibility = View.GONE
            viewPagerInTheaters.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorInTheaters.visibility = if (isError) View.VISIBLE else View.GONE
        retryInTheaters.visibility = if (isError) View.VISIBLE else View.GONE
    }

    override fun onClickListener(newMovieItem: NewMovieItems) {
        val activity = context as Activity
        (activity as MainActivity).navigateToMovieBookingFragment(newMovieItem)
    }

}