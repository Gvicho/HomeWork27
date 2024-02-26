package com.example.homework27.presentation.screen.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework27.R

class DotsRecyclerAdapter : RecyclerView.Adapter<DotsRecyclerAdapter.DotViewHolder>() {

    private var dotsCount: Int = 0

    fun setDotsCount(count: Int) {
        dotsCount = count
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_orange_dot, parent, false)
        return DotViewHolder(view)
    }

    override fun onBindViewHolder(holder: DotViewHolder, position: Int) {
        // Nothing to bind in this case, as all dots are the same
    }

    override fun getItemCount(): Int = dotsCount

    class DotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}