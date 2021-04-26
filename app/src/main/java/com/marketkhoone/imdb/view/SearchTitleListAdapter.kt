package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemSearchTitleBinding
import com.marketkhoone.imdb.model.entity.SearchTitleResult
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class SearchTitleListAdapter(private  val searchTitleResultList: ArrayList<SearchTitleResult>, private val listener: ClickListener):
    RecyclerView.Adapter<SearchTitleListAdapter.SearchTitleViewHolder>(), SearchTitleClickListener {

    interface ClickListener {
        fun onClickListener(id: String?)
    }

    fun updateSearchList(newPopularList: List<SearchTitleResult>){
        searchTitleResultList.clear()
        searchTitleResultList.addAll(newPopularList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTitleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemSearchTitleBinding>(inflater,R.layout.item_search_title, parent, false)
        return SearchTitleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchTitleViewHolder, position: Int) {
        val searchTitleReslt = searchTitleResultList[position]

        holder.view.searchTitleReslt = searchTitleReslt
        holder.view.listener = this

    }

    override fun getItemCount() = searchTitleResultList.size

    class SearchTitleViewHolder(var view: ItemSearchTitleBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        for(searchTitleResult in searchTitleResultList){
            if(v.tag == searchTitleResult.id){
                listener.onClickListener(searchTitleResult.id)
            }
        }
    }
}