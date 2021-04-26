package com.marketkhoone.imdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marketkhoone.imdb.R
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter
import kotlinx.android.synthetic.main.fragment_theaters.*
import kotlinx.android.synthetic.main.fragment_top.*

class TopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FragmentStatePagerItemAdapter(
            fragmentManager, FragmentPagerItems.with(context)
                .add(R.string.top_250_movies, Top250MoviesFragment::class.java)
                .add(R.string.top_250_tvs, Top250TVsFragment::class.java)
                .create()
        )

        viewPagerTop.adapter = adapter
        viewPagerTop.setEnableSwipe(false)

        viewPagerTabTop.setViewPager(viewPagerTop)
    }
}