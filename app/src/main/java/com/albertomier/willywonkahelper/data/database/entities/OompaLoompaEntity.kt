package com.albertomier.willywonkahelper.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import com.google.gson.annotations.SerializedName

@Entity(tableName = "oompa_loompa_table")
data class OompaLoompaEntity(
    @PrimaryKey(autoGenerate = false)
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

fun OompaLoompa.toDatabase() = OompaLoompaEntity(
    id,
    firstName,
    lastName,
    gender,
    image,
    profession,
    email,
    age,
    country,
    height
)