package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import javax.inject.Inject

class GetOompaLoompaByFilters @Inject constructor(private val repository: OompaLoompaRepository) {

    suspend operator fun invoke(filters: List<String>): List<OompaLoompa> {
        val oompaLoompas = repository.getAllOompaLoompasFromDatabase()
        val result: MutableList<OompaLoompa> = arrayListOf()

        oompaLoompas.map {
            filters.map { filter ->
                if (it.gender.contentEquals(filter)) {
                    result.add(it)
                }
                if (it.profession.contentEquals(filter)) {
                    result.add(it)
                }
            }
        }

        return result
    }
}