package com.albertomier.willywonkahelper.data.network

import com.albertomier.willywonkahelper.data.model.OompaLoompaModel
import com.albertomier.willywonkahelper.data.model.OompaLoompaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AppService @Inject constructor(private val api: AppApiClient) {

    suspend fun getOompaLoompas(): List<OompaLoompaModel> {
        return withContext(Dispatchers.IO) {
            val response: Response<OompaLoompaResponse> = api.getAll()
            response.body()?.results ?: emptyList()
        }
    }

    suspend fun getOompaLoompaById(id: Int): OompaLoompaModel {
        return withContext(Dispatchers.IO) {
            api.getById(id)
        }
    }
}