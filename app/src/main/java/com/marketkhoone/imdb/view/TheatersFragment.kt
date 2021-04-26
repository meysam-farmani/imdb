package com.marketkhoone.imdb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marketkhoone.imdb.R
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter
import kotlinx.android.synthetic.main.fragment_theaters.*


class TheatersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theaters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter1 = FragmentStatePagerItemAdapter(
            fragmentManager, FragmentPagerItems.with(context)
                .add(R.string.in_theaters, InTheatersFragment::class.java)
                .add(R.string.box_office, BoxOfficeFragment::class.java)
                .add(R.string.coming_soon, ComingSoonFragment::class.java)
                .create()
        )

        viewPagerTheaters.adapter = adapter1
        viewPagerTheaters.setEnableSwipe(false)

        viewPagerTabTheaters.setViewPager(viewPagerTheaters)

    }
}