package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import javax.inject.Inject

class GetOompaLoompaById @Inject constructor(private val repository: OompaLoompaRepository) {

    suspend operator fun invoke(id: Int): OompaLoompa {
        val result = repository.getOompaLoompaByIdFromApi(id)

        return if (result != null) {
            //update oompa loompa
            result
        } else {
            repository.getOompaLoompaByIdFromDatabase(id)
        }
    }
}