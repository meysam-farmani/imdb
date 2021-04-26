package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemPagerCardsBinding
import com.marketkhoone.imdb.model.entity.NewMovieItem

class PagerCardsListAdapter(private  val newMovieItemList: ArrayList<NewMovieItem>, private val listener: ClickListener):
    RecyclerView.Adapter<PagerCardsListAdapter.PagerCardsViewHolder>(), PagerCardsClickListener {

    interface ClickListener {
        fun onClickListener(id: String?)
    }

    fun updatePagerCardsList(newMovieItems: List<NewMovieItem>){
        newMovieItemList.clear()
        newMovieItemList.addAll(newMovieItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerCardsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemPagerCardsBinding>(inflater,
            R.layout.item_pager_cards, parent, false)
        return PagerCardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerCardsViewHolder, position: Int) {
        val movieItem = newMovieItemList[position]
        holder.view.movieItem = movieItem

        holder.view.listener = this
    }

    override fun getItemCount() = newMovieItemList.size

    class PagerCardsViewHolder(var view: ItemPagerCardsBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        for(newMovieItem in newMovieItemList){
            if(v.tag == newMovieItem.id){
                listener.onClickListener(newMovieItem.id)
            }
        }
    }
}