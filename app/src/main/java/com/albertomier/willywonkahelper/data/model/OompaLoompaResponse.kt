package com.albertomier.willywonkahelper.data.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaResponse(
    @SerializedName("current") var current: Int,
    @SerializedName("total") var total: Int,
    @SerializedName("results") var results: List<OompaLoompaModel>
)

