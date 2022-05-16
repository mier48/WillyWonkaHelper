package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.core.Utils
import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import javax.inject.Inject

class GetOompaLoompaById @Inject constructor(private val repository: OompaLoompaRepository) {

    suspend operator fun invoke(id: Int): OompaLoompa {
        return if (Utils.isOnline()) {
            repository.getOompaLoompaByIdFromApi(id)
        } else {
            repository.getOompaLoompaByIdFromDatabase(id)
        }
    }
}