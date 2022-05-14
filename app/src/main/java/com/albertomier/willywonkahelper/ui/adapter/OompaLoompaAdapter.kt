package com.albertomier.willywonkahelper.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.willywonkahelper.R
import com.albertomier.willywonkahelper.domain.model.OompaLoompa

class OompaLoompaAdapter(
    private val itemList: List<OompaLoompa>,
    private val onClickListener: (OompaLoompa) -> Unit
) : RecyclerView.Adapter<OompaLoompaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OompaLoompaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OompaLoompaViewHolder(layoutInflater.inflate(R.layout.oompa_loompa, parent, false))
    }

    override fun onBindViewHolder(holder: OompaLoompaViewHolder, position: Int) {
        val item = itemList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!itemList.isNullOrEmpty()) {
            return itemList.size
        }
        return 0
    }
}