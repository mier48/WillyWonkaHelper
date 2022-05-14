package com.albertomier.willywonkahelper.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomier.willywonkahelper.databinding.FragmentHomeBinding
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import com.albertomier.willywonkahelper.ui.adapter.OompaLoompaAdapter
import com.albertomier.willywonkahelper.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = GridLayoutManager(activity, 1)
        binding.dataList.layoutManager = manager

        listViewModel.onCreate()

        listViewModel.listModel.observe(viewLifecycleOwner, Observer {
            binding.dataList.adapter =
                OompaLoompaAdapter(it, { beer -> onItemSelected(beer) })
        })

        listViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        return root
    }

    private fun onItemSelected(oompaLoompa: OompaLoompa) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("_id", oompaLoompa.id)

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}