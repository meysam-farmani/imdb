package com.marketkhoone.imdb.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.entity.SearchTitle
import com.marketkhoone.imdb.viewmodel.SearchTitleViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchTitleListAdapter.ClickListener {

    private lateinit var viewModel: SearchTitleViewModel
    private val listAdapter = SearchTitleListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SearchTitleViewModel::class.java)
        viewModel.movieDataList.observe(viewLifecycleOwner, movieDataListDataObserver)
        viewModel.loading.observe(viewLifecycleOwner, loadingLiveDataObserver)
        viewModel.loadError.observe(viewLifecycleOwner, errorLiveDataObsever)

        movieListSearch.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listAdapter
        }

        searchButtonSearchTitle.setOnClickListener {
            val expression = expressionSearchTitle.text.toString()
            if(!expression.isNullOrEmpty()){
                hideKeyboard(searchFragmentLayout)
                viewModel.search(expression)
            }
        }

        searchFragmentLayout.setOnClickListener {
            hideKeyboard(searchFragmentLayout)
        }
    }

    private val movieDataListDataObserver = Observer<SearchTitle> { list ->
        list?.let {
            it.results?.let{ results ->
                if(results.size > 0){
                    movieListSearch.visibility = View.VISIBLE
                    listAdapter.updateSearchList(results)
                }else{
                    listEmptySearch.visibility = View.VISIBLE
                }
            }
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { isLoading ->
        loadingViewSearch.visibility = if (isLoading) View.VISIBLE else View.GONE

        if(isLoading){
            listErrorSearch.visibility = View.GONE
            movieListSearch.visibility = View.GONE
            listEmptySearch.visibility = View.GONE
        }
    }

    private val errorLiveDataObsever = Observer<Boolean> { isError ->
        listErrorSearch.visibility = if (isError) View.VISIBLE else View.GONE
    }

    private fun hideKeyboard(view: View){
        val imm: InputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onClickListener(id: String?) {
        val activity = context as Activity
        (activity as SearchActivity).navigateToMovieBookingFragment(id)
    }

}