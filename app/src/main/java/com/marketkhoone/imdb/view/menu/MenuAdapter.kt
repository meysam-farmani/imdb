package com.marketkhoone.imdb.view.menu

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.marketkhoone.imdb.view.menu.views.DuoOptionView
import java.util.*

internal class MenuAdapter(options: ArrayList<String>) :
    BaseAdapter() {
    private var mOptions = ArrayList<String>()
    private val mOptionViews: ArrayList<DuoOptionView> = ArrayList<DuoOptionView>()
    override fun getCount(): Int {
        return mOptions.size
    }

    override fun getItem(position: Int): Any {
        return mOptions[position]
    }

    fun setViewSelected(position: Int, selected: Boolean) {

        // Looping through the options in the menu
        // Selecting the chosen option
        for (i in mOptionViews.indices) {
            if (i == position) {
                mOptionViews[i].setSelected(selected)
            } else {
                mOptionViews[i].setSelected(!selected)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val option = mOptions[position]

        // Using the DuoOptionView to easily recreate the demo
        val optionView: DuoOptionView
        if (convertView == null) {
            optionView = DuoOptionView(parent.context)
        } else {
            optionView = convertView as DuoOptionView
        }
        val iconString = option.split(",".toRegex()).toTypedArray()[1]
        val id = parent.context.resources.getIdentifier(
            iconString,
            "drawable",
            parent.context.packageName
        )
        val icon = parent.context.resources.getDrawable(id)
        // Using the DuoOptionView's default selectors
        optionView.bind(option, icon, null)

        // Adding the views to an array list to handle view selection
        mOptionViews.add(optionView)
        return optionView
    }

    init {
        mOptions = options
    }
}
