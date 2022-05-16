package com.albertomier.willywonkahelper.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomier.willywonkahelper.R
import com.albertomier.willywonkahelper.databinding.FragmentHomeBinding
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import com.albertomier.willywonkahelper.ui.adapter.FilterAdapter
import com.albertomier.willywonkahelper.ui.adapter.OompaLoompaAdapter
import com.albertomier.willywonkahelper.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: ListViewModel by viewModels()
    private var professionFilters: List<String> = emptyList()
    private var filtersChecked: MutableList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.dataList.layoutManager = LinearLayoutManager(activity)
        binding.filterList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        listViewModel.onCreate()
        listViewModel.getProfessionFilters()

        listViewModel.listModel.observe(viewLifecycleOwner, Observer {
            binding.dataList.adapter =
                OompaLoompaAdapter(it) { oompaLoompa -> onItemSelected(oompaLoompa) }
        })

        listViewModel.filterList.observe(viewLifecycleOwner, Observer {
            this.professionFilters = it
        })

        listViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progress.isVisible = it
        })

        listViewModel.filterModel.observe(viewLifecycleOwner, Observer {
            binding.filterList.adapter =
                FilterAdapter(it) { filter -> onFilterSelected(filter) }
        })

        binding.filterIco.setOnClickListener {
            showDialog()
        }

        return root
    }

    private fun onItemSelected(oompaLoompa: OompaLoompa) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("_id", oompaLoompa.id)

        startActivity(intent)
    }

    private fun onFilterSelected(filter: String) {
        filtersChecked.remove(filter)
        if (filtersChecked.isEmpty()) {
            listViewModel.onCreate()
        } else {
            listViewModel.addFilter(filtersChecked)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.filters_layout, null)

        val radioGroupProfession: RadioGroup = dialogView.findViewById(R.id.radioGroupProfession)
        val radioGroupGender: RadioGroup = dialogView.findViewById(R.id.radioGroupGender)

        this.professionFilters.map {
            val radioButton: RadioButton = RadioButton(context)
            radioButton.id = View.generateViewId()
            radioButton.text = it

            filtersChecked.map { filter ->
                if (filter == it) {
                    radioButton.isChecked = true
                }
            }

            radioGroupProfession.addView(radioButton)
        }

        val maleRadioButton = RadioButton(context)
        maleRadioButton.id = View.generateViewId()
        maleRadioButton.text = getString(R.string.male)

        filtersChecked.map { filter ->
            if (filter == "M") {
                maleRadioButton.isChecked = true
            }
        }

        radioGroupGender.addView(maleRadioButton)

        val femaleRadioButton = RadioButton(context)
        femaleRadioButton.id = View.generateViewId()
        femaleRadioButton.text = getString(R.string.female)

        filtersChecked.map { filter ->
            if (filter == "F") {
                femaleRadioButton.isChecked = true
            }
        }

        radioGroupGender.addView(femaleRadioButton)

        val builder = AlertDialog.Builder(context).setView(dialogView)

        builder.setPositiveButton(getString(R.string.filter)) { _, _ ->
            filtersChecked = arrayListOf()

            if (maleRadioButton.isChecked) {
                filtersChecked.add("M")
            }
            if (femaleRadioButton.isChecked) {
                filtersChecked.add("F")
            }

            if (dialogView.findViewById<RadioButton>(radioGroupProfession.checkedRadioButtonId) != null) {
                val radioButton: RadioButton =
                    dialogView.findViewById(radioGroupProfession.checkedRadioButtonId)
                filtersChecked.add(radioButton.text.toString())
            }

            listViewModel.addFilter(filtersChecked)
        }

        builder.setNegativeButton(getString(R.string.cancel), null)
        builder.setCancelable(false)

        val dialog = builder.create()

        dialog.show()
    }
}