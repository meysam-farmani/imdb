package com.marketkhoone.imdb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.marketkhoone.imdb.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private var lastTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        backButtonSearch.setOnClickListener {
            finish()
        }

        backButtonSearchMovieBooking.setOnClickListener {
            backButtonSearchMovieBooking.visibility = View.GONE
            backButtonSearch.visibility = View.VISIBLE
            searchTitle.text = lastTitle

            supportFragmentManager.popBackStack()
        }

        goToFragment(SearchFragment(), true)
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.containerSearch, fragment).commit()
    }

    private fun goToMovieBookingFragment(fragment: Fragment, id: String?) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack(null)

        val parameters = Bundle()
        parameters.putString("ImdbId", id)
        parameters.putBoolean("IsFromMainActivity", false)
        fragment.setArguments(parameters)

        transaction.add(R.id.containerSearch, fragment).commit()
    }

    fun showVideo(videoId: String?){
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("VideoId", videoId)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun navigateToMovieBookingFragment(id: String?) {
        backButtonSearchMovieBooking.visibility = View.VISIBLE
        backButtonSearch.visibility = View.GONE
        lastTitle = searchTitle.text.toString()
        searchTitle.text = "Movie Booking"
        goToMovieBookingFragment(MovieBookingFragment(), id)
    }
}