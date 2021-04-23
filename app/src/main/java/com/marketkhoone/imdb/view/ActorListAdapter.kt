package com.marketkhoone.imdb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.marketkhoone.imdb.R
import com.marketkhoone.imdb.databinding.ItemActorBinding
import com.marketkhoone.imdb.model.Actors

class ActorListAdapter(private  val actorList: ArrayList<Actors>):
    RecyclerView.Adapter<ActorListAdapter.AnimalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemActorBinding>(inflater,R.layout.item_actor, parent, false)
        return AnimalViewHolder(view)
    }

    fun updateActorList(newActorList: List<Actors>){
        actorList.clear()
        actorList.addAll(newActorList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val actor = actorList[position]
        holder.view.actor = actor

    }

    override fun getItemCount() = actorList.size

    class AnimalViewHolder(var view: ItemActorBinding): RecyclerView.ViewHolder(view.root)
}