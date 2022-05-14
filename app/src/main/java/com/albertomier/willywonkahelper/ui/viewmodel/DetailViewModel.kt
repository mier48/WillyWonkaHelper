package com.albertomier.willywonkahelper.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.willywonkahelper.domain.GetOompaLoompa
import com.albertomier.willywonkahelper.domain.GetOompaLoompaById
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getOompaLoompaById: GetOompaLoompaById
) : ViewModel() {

    var dataModel = MutableLiveData<OompaLoompa>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getOompaLoompaById(id)
            dataModel.postValue(result)

            isLoading.postValue(false)
        }
    }
}