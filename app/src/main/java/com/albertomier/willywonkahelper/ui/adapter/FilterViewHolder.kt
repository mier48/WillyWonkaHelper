package com.albertomier.willywonkahelper.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.willywonkahelper.databinding.FilterBinding

class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FilterBinding.bind(view)

    fun render(filter: String, onClickListener: (String) -> Unit) {
        var strText: String = filter
        if (filter == "F") {
            strText = "Female"
        } else if (filter == "M") {
            strText = "Male"
        }
        binding.chip.text = strText

        binding.chip.setOnClickListener {
            onClickListener(filter)
        }
    }
}