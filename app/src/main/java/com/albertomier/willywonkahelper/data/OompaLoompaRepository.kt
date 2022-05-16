package com.albertomier.willywonkahelper.data

import com.albertomier.willywonkahelper.data.database.dao.AppDao
import com.albertomier.willywonkahelper.data.database.entities.OompaLoompaEntity
import com.albertomier.willywonkahelper.data.model.OompaLoompaModel
import com.albertomier.willywonkahelper.data.network.AppService
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import com.albertomier.willywonkahelper.domain.model.toDomain
import javax.inject.Inject

class OompaLoompaRepository @Inject constructor(
    private val api: AppService,
    private val appDao: AppDao
) {
    suspend fun getAllOompaLoompasFromApi(): List<OompaLoompa> {
        val response: List<OompaLoompaModel> = api.getOompaLoompas()
        return response.map { it.toDomain() }
    }

    suspend fun getOompaLoompaByIdFromApi(id: Int): OompaLoompa {
        val response: OompaLoompaModel = api.getOompaLoompaById(id)
        return response.toDomain()
    }

    suspend fun getAllOompaLoompasFromDatabase(): List<OompaLoompa> {
        val response: List<OompaLoompaEntity> = appDao.getOompaLoompas()
        return response.map { it.toDomain() }
    }

    suspend fun getOompaLoompaByIdFromDatabase(id: Int): OompaLoompa {
        val response: OompaLoompaEntity = appDao.getOompaLoompaById(id)
        return response.toDomain()
    }

    suspend fun addOompaLoompas(oompaLoompas: List<OompaLoompaEntity>) {
        appDao.insertAll(oompaLoompas)
    }

    suspend fun clearAll() {
        appDao.deleteAll()
    }
}