package com.albertomier.willywonkahelper.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.albertomier.willywonkahelper.databinding.ActivityDetailBinding
import com.albertomier.willywonkahelper.ui.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (intent != null && intent.hasExtra("_id")) {
            id = intent.getIntExtra("_id", -1)
        }

        if (id >= 0) {
            detailViewModel.onCreate(id)
        }

        detailViewModel.dataModel.observe(this, Observer {
            val name = "${it.firstName} ${it.lastName}"

            supportActionBar?.title = name

            binding.name.text = name
            binding.profession.text = it.profession
            binding.country.text = it.country

            Glide.with(this@DetailActivity).load(it.image).centerCrop().into(binding.image);
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}