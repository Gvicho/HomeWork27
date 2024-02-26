package com.example.homework27.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework27.databinding.ItemVehicleSuggestionBinding
import com.example.homework27.presentation.model.VehicleUI

class VehiclesSuggestionsRecyclerAdapter : ListAdapter<VehicleUI, RecyclerView.ViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VehicleUI>() {
            override fun areItemsTheSame(oldItem: VehicleUI, newItem: VehicleUI): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: VehicleUI, newItem: VehicleUI): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class VehiclesViewHolder(private val binding: ItemVehicleSuggestionBinding): RecyclerView.ViewHolder(binding.root){
        private val dotsAdapter = DotsRecyclerAdapter()

        init {
            binding.dotsRecycler.adapter = dotsAdapter
        }

        fun bind(position: Int) {
            val vehicle = currentList[position]
            binding.tvName.text = vehicle.name
            dotsAdapter.setDotsCount(vehicle.parentsNum)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesViewHolder {
        return VehiclesViewHolder(ItemVehicleSuggestionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is VehiclesViewHolder)holder.bind(position)
    }


}