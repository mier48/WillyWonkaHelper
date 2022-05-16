package com.albertomier.willywonkahelper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.willywonkahelper.R

class FilterAdapter(
    private val filterList: List<String>,
    private val onClickListener: (String) -> Unit
) :
    RecyclerView.Adapter<FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilterViewHolder(layoutInflater.inflate(R.layout.filter, parent, false))
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val item = filterList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!filterList.isNullOrEmpty()) {
            return filterList.size
        }
        return 0
    }
}