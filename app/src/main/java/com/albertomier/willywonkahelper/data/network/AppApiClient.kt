package com.albertomier.willywonkahelper.data.network

import com.albertomier.willywonkahelper.data.model.OompaLoompaModel
import com.albertomier.willywonkahelper.data.model.OompaLoompaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApiClient {
    @GET("oompa-loompas")
    suspend fun getAll(): Response<OompaLoompaResponse>

    @GET("oompa-loompas/{id}")
    suspend fun getById(@Path("id") id: Int,): OompaLoompaModel
}