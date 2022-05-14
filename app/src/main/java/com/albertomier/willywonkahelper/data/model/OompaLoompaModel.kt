package com.albertomier.willywonkahelper.data.model

import com.google.gson.annotations.SerializedName

data class OompaLoompaModel(
    @SerializedName("id") var id: Int,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("image") var image: String,
    @SerializedName("profession") var profession: String,
    @SerializedName("email") var email: String,
    @SerializedName("age") var age: String,
    @SerializedName("country") var country: String,
    @SerializedName("height") var height: String
)