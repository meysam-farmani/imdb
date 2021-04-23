package com.marketkhoone.imdb.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.model.NewMovieItems
import com.marketkhoone.imdb.view.menu.MenuAdapter
import com.marketkhoone.imdb.view.menu.views.DuoDrawerLayout
import com.marketkhoone.imdb.view.menu.views.DuoMenuView
import com.marketkhoone.imdb.view.menu.widgets.DuoDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() , DuoMenuView.OnMenuClickListener {

    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null
    private var lastTitle: String = ""

    private var mTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))

        mViewHolder = ViewHolder(drawer)

        handleMenu()

        handleDrawer()

        goToFragment(TheatersFragment(), false)
        mMenuAdapter?.setViewSelected(0, true)
        val firstTitle: String = mTitles.get(0).split(",".toRegex()).toTypedArray().get(0)
        mainTitle.text = firstTitle

        menuButton.setOnClickListener {
            if (mViewHolder!!.mDuoDrawerLayout.isDrawerVisible) {
                mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
            } else {
                mViewHolder!!.mDuoDrawerLayout.openDrawer(GravityCompat.START)
            }
        }

        backButton.setOnClickListener {
            menuButton.visibility = View.VISIBLE
            backButton.visibility = View.GONE
            searchButton.visibility = View.VISIBLE
            mainTitle.text = lastTitle

            supportFragmentManager.popBackStack()
        }
    }

    private fun handleDrawer() {
        val duoDrawerToggle = mViewHolder?.mDuoDrawerLayout?.let {
            DuoDrawerToggle(
                this,
                it,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
        }
        mViewHolder?.mDuoDrawerLayout?.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle?.syncState()
    }

    private fun handleMenu() {
        mMenuAdapter = MenuAdapter(mTitles)
        mViewHolder?.mDuoMenuView?.setOnMenuClickListener(this)
        mViewHolder?.mDuoMenuView?.adapter = mMenuAdapter
    }

    private fun goToFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.add(R.id.container, fragment).commit()
    }

    private fun goToMovieBookingFragment(fragment: Fragment, newMovieItem: NewMovieItems) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack(null)

        val gson = Gson()
        val parameters = Bundle()
        val json: String = gson.toJson(newMovieItem)
        parameters.putString("NewMovieItem", json)
        fragment.setArguments(parameters)

        transaction.add(R.id.container, fragment).commit()
    }

    override fun onFooterClicked() {

    }

    override fun onHeaderClicked() {

    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {
        val title: String = mTitles.get(position).split(",".toRegex()).toTypedArray().get(0)
        mainTitle.text = title

        // Set the right options selected
        mMenuAdapter?.setViewSelected(position, true)
        when (position) {
//            else -> goToFragment(MainFragment(), false)
        }

        // Close the drawer
        mViewHolder?.mDuoDrawerLayout?.closeDrawer()
    }

    private class ViewHolder internal constructor(drawer: DuoDrawerLayout) {
        val mDuoDrawerLayout: DuoDrawerLayout
        val mDuoMenuView: DuoMenuView

        init {
            mDuoDrawerLayout = drawer
            mDuoMenuView = mDuoDrawerLayout.menuView as DuoMenuView
        }
    }

    fun navigateToMovieBookingFragment(newMovieItem: NewMovieItems) {
        menuButton.visibility = View.GONE
        backButton.visibility = View.VISIBLE
        searchButton.visibility = View.GONE
        lastTitle = mainTitle.text.toString()
        mainTitle.text = "Movie Booking"
        goToMovieBookingFragment(MovieBookingFragment(), newMovieItem)
    }

    fun showVideo(videoId: String?){
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("VideoId", videoId)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}