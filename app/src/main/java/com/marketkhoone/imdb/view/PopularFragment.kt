package com.marketkhoone.imdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marketkhoone.imdb.R
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FragmentStatePagerItemAdapter(
            fragmentManager, FragmentPagerItems.with(context)
                .add(R.string.most_popular_movies, MostPopularMoviesFragment::class.java)
                .add(R.string.most_popular_tvs, MostPopularTVsFragment::class.java)
                .create()
        )

        viewPagerPopular.adapter = adapter
        viewPagerPopular.setEnableSwipe(false)

        viewPagerTabPopular.setViewPager(viewPagerPopular)
    }
}