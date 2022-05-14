package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.data.database.entities.toDatabase
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import javax.inject.Inject

class GetOompaLoompa @Inject constructor(private val repository: OompaLoompaRepository) {

    suspend operator fun invoke(): List<OompaLoompa> {
        val result = repository.getAllOompaLoompasFromApi()

        return if (result.isNotEmpty()) {
            repository.clearAll()
            repository.addOompaLoompas(result.map { it.toDatabase() })
            result
        } else {
            repository.getAllOompaLoompasFromDatabase()
        }
    }
}