package com.marketkhoone.imdb.view

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.entity.NewMovieItem
import com.marketkhoone.imdb.model.entity.NewMovie
import com.marketkhoone.imdb.viewmodel.ComingSoonViewModel
import kotlinx.android.synthetic.main.fragment_coming_soon.*

class ComingSoonFragment : Fragment(), PagerCardsListAdapter.ClickListener {

    private lateinit var viewModel: ComingSoonViewModel
    private val listAdapter = PagerCardsListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coming_soon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerComingSoon.adapter = listAdapter

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        viewPagerComingSoon.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }

        viewModel = ViewModelProviders.of(this).get(ComingSoonViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner,movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner,loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner,errorLiveDataObsever)
        viewModel.getData()

        retryComingSoon.setOnClickListener{
            viewModel.getData()
        }

    }

    private val movieDataListDataObserver = Observer<NewMovie> { list ->
        list?.let {
            viewPagerComingSoon.visibility = View.VISIBLE
            it.items?.let{ items ->
                listAdapter.updatePagerCardsList(items)
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewComingSoon.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorComingSoon.visibility = View.GONE
            retryComingSoon.visibility = View.GONE
            viewPagerComingSoon.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorComingSoon.visibility = if (isError) View.VISIBLE else View.GONE
        retryComingSoon.visibility = if (isError) View.VISIBLE else View.GONE
    }


    override fun onClickListener(id: String?) {
        val activity = context as Activity
        (activity as MainActivity).navigateToMovieBookingFragment(id)
    }
}