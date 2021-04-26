package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemPopularMovieBinding
import com.marketkhoone.imdb.model.entity.PopularMovieItem
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class PopularMovieListAdapter(private  val popularMovieList: ArrayList<PopularMovieItem>, private val listener: ClickListener):
    RecyclerView.Adapter<PopularMovieListAdapter.PopularMovieViewHolder>(), PopularMovieClickListener {

    interface ClickListener {
        fun onClickListener(id: String?)
    }

    fun updatePopularList(newPopularList: List<PopularMovieItem>){
        popularMovieList.clear()
        popularMovieList.addAll(newPopularList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemPopularMovieBinding>(inflater,R.layout.item_popular_movie, parent, false)
        return PopularMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val popularMovie = popularMovieList[position]

        holder.view.popularMovie = popularMovie
        holder.view.listener = this

        popularMovie.imDbRating?.let { rate ->
            if(rate == ""){
                holder.view.simpleRatingBarPopularMovieItem.rating = 0f
            }else{
                holder.view.simpleRatingBarPopularMovieItem.rating = rate.toFloat() / 2
            }
        }

    }

    override fun getItemCount() = popularMovieList.size

    class PopularMovieViewHolder(var view: ItemPopularMovieBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        for(popularMovie in popularMovieList){
            if(v.tag == popularMovie.id){
                listener.onClickListener(popularMovie.id)
            }
        }
    }
}