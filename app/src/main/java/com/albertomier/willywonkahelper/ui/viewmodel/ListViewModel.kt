package com.albertomier.willywonkahelper.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.willywonkahelper.domain.GetOompaLoompa
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel@Inject constructor(
    private val getOompaLoompa: GetOompaLoompa
) : ViewModel() {

    var listModel = MutableLiveData<List<OompaLoompa>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getOompaLoompa()

            if (!result.isNullOrEmpty()) {
                listModel.postValue(result)
            }

            isLoading.postValue(false)
        }
    }
}