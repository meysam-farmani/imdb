package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemTopMovieBinding
import com.marketkhoone.imdb.model.entity.NewMovieItem
import com.marketkhoone.imdb.model.entity.TopMovie
import com.marketkhoone.imdb.model.entity.TopMovieItem
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class TopMovieListAdapter(private  val topMovieList: ArrayList<TopMovieItem>, private val listener: ClickListener):
    RecyclerView.Adapter<TopMovieListAdapter.TopMovieViewHolder>(), TopMovieClickListener {

    interface ClickListener {
        fun onClickListener(id: String?)
    }

    fun updateTopList(newTopList: List<TopMovieItem>){
        topMovieList.clear()
        topMovieList.addAll(newTopList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemTopMovieBinding>(inflater,R.layout.item_top_movie, parent, false)
        return TopMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopMovieViewHolder, position: Int) {
        val topMovie = topMovieList[position]

        holder.view.topMovie = topMovie
        holder.view.listener = this

        topMovie.imDbRating?.let { rate ->
            if(rate == ""){
                holder.view.simpleRatingBarTopMovieItem.rating = 0f
            }else{
                holder.view.simpleRatingBarTopMovieItem.rating = rate.toFloat() / 2
            }
        }

    }

    override fun getItemCount() = topMovieList.size

    class TopMovieViewHolder(var view: ItemTopMovieBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        for(topMovie in topMovieList){
            if(v.tag == topMovie.id){
                listener.onClickListener(topMovie.id)
            }
        }
    }
}