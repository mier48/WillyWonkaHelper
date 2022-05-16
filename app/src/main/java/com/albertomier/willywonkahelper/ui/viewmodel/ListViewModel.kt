package com.albertomier.willywonkahelper.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.willywonkahelper.domain.GetOompaLoompa
import com.albertomier.willywonkahelper.domain.GetOompaLoompaByFilters
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getOompaLoompa: GetOompaLoompa,
    private val getOompaLoompaByFilters: GetOompaLoompaByFilters
) : ViewModel() {

    var listModel = MutableLiveData<List<OompaLoompa>>()
    var filterModel = MutableLiveData<List<String>>()
    var filterList = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getOompaLoompa()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }

            filterModel.postValue(emptyList())

            isLoading.postValue(false)
        }
    }

    fun addFilter(filters: List<String>) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getOompaLoompaByFilters(filters)

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            } else {
                listModel.postValue(emptyList())
            }

            filterModel.postValue(filters)

            isLoading.postValue(false)
        }
    }

    fun getProfessionFilters() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getOompaLoompa()

            if (!result.isNullOrEmpty()) {
                val filters: MutableList<String> = mutableListOf()
                result.map {
                    if (!filters.contains(it.profession)) {
                        filters.add(it.profession)
                    }
                }
                filterList.postValue(filters)
            }

            isLoading.postValue(false)
        }
    }
}