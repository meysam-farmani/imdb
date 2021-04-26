package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemBoxOfficeMovieBinding
import com.marketkhoone.imdb.model.entity.BoxOfficeMovieItem
import kotlinx.android.synthetic.main.fragment_movie_booking.*

class BoxOfficeMovieListAdapter(private  val boxOfficeMovieList: ArrayList<BoxOfficeMovieItem>, private val listener: ClickListener):
    RecyclerView.Adapter<BoxOfficeMovieListAdapter.BoxOfficeMovieViewHolder>(), BoxOfficeMovieClickListener {

    interface ClickListener {
        fun onClickListener(id: String?)
    }

    fun updateBoxOfficeList(newBoxOfficeList: List<BoxOfficeMovieItem>){
        boxOfficeMovieList.clear()
        boxOfficeMovieList.addAll(newBoxOfficeList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxOfficeMovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemBoxOfficeMovieBinding>(inflater,R.layout.item_box_office_movie, parent, false)
        return BoxOfficeMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoxOfficeMovieViewHolder, position: Int) {
        val boxOfficeMovie = boxOfficeMovieList[position]

        holder.view.boxOfficeMovie = boxOfficeMovie
        holder.view.listener = this

    }

    override fun getItemCount() = boxOfficeMovieList.size

    class BoxOfficeMovieViewHolder(var view: ItemBoxOfficeMovieBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        for(boxOfficeMovie in boxOfficeMovieList){
            if(v.tag == boxOfficeMovie.id){
                listener.onClickListener(boxOfficeMovie.id)
            }
        }
    }
}