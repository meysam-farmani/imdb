package com.marketkhoone.imdb.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.view.menu.MenuAdapter
import com.marketkhoone.imdb.view.menu.views.DuoDrawerLayout
import com.marketkhoone.imdb.view.menu.views.DuoMenuView
import com.marketkhoone.imdb.view.menu.widgets.DuoDrawerToggle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() , DuoMenuView.OnMenuClickListener {

    private var mMenuAdapter: MenuAdapter? = null
    private var mViewHolder: ViewHolder? = null

    private var mTitles = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTitles = ArrayList(Arrays.asList(*resources.getStringArray(R.array.menuOptions)))

        mViewHolder = ViewHolder(drawer)

        handleMenu()

        handleDrawer()

        goToFragment(HomeFragment(), false)
        mMenuAdapter?.setViewSelected(0, true)
        val firstTitle: String = mTitles.get(0).split(",".toRegex()).toTypedArray().get(0)
        title = firstTitle

        menu.setOnClickListener {
            if (mViewHolder!!.mDuoDrawerLayout.isDrawerVisible) {
                mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
            } else {
                mViewHolder!!.mDuoDrawerLayout.openDrawer(GravityCompat.START)
            }
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

    override fun onFooterClicked() {

    }

    override fun onHeaderClicked() {

    }

    override fun onOptionClicked(position: Int, objectClicked: Any?) {
        val title: String = mTitles.get(position).split(",".toRegex()).toTypedArray().get(0)
        setTitle(title)

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
}