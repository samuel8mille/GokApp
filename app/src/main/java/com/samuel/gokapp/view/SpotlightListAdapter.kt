package com.samuel.gokapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.samuel.gokapp.R
import com.samuel.gokapp.model.gokrepository.SpotlightModel
import com.samuel.gokapp.databinding.ItemSpotlightBinding

class SpotlightListAdapter(private val spotlightList: ArrayList<SpotlightModel>) :
    RecyclerView.Adapter<SpotlightListAdapter.SpotlightListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpotlightListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemSpotlightBinding>(
                inflater,
                R.layout.item_spotlight,
                parent,
                false
            )
        return SpotlightListViewHolder(view)
    }

    override fun getItemCount() = spotlightList.size


    override fun onBindViewHolder(holder: SpotlightListViewHolder, position: Int) {
        holder.view.spotlight = spotlightList[position]
    }

    fun updateSpotlightList(newSpotlightList: List<SpotlightModel>) {
        spotlightList.clear()
        spotlightList.addAll(newSpotlightList)
        notifyDataSetChanged()
    }

    class SpotlightListViewHolder(var view: ItemSpotlightBinding) :
        RecyclerView.ViewHolder(view.root)

}