package com.albertomier.willywonkahelper.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.willywonkahelper.databinding.OompaLoompaBinding
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import com.bumptech.glide.Glide

class OompaLoompaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = OompaLoompaBinding.bind(view)

    fun render(oompaLoompa: OompaLoompa, onClickListener: (OompaLoompa) -> Unit) {
        val context = binding.image.context

        binding.name.text = "${oompaLoompa.firstName} ${oompaLoompa.lastName}"
        binding.profession.text = oompaLoompa.profession
        binding.country.text = oompaLoompa.country
        if (oompaLoompa.gender == "M") {
            binding.gender.text = "Male"
        } else {
            binding.gender.text = "Female"
        }
        binding.ageCircle.text = oompaLoompa.age

        Glide.with(context).load(oompaLoompa.image).centerCrop().into(binding.image)

        itemView.setOnClickListener {
            onClickListener(oompaLoompa)
        }
    }
}