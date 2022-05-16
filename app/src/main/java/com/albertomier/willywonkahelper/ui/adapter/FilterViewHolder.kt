package com.albertomier.willywonkahelper.ui.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.willywonkahelper.R
import com.albertomier.willywonkahelper.databinding.FilterBinding

class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FilterBinding.bind(view)

    fun render(filter: String, onClickListener: (String) -> Unit) {
        val context: Context = binding.chip.context

        var strText: String = filter
        if (filter == "F") {
            strText = context.getString(R.string.female)
        } else if (filter == "M") {
            strText = context.getString(R.string.male)
        }
        binding.chip.text = strText

        binding.chip.setOnClickListener {
            onClickListener(filter)
        }
    }
}