package com.albertomier.willywonkahelper.domain.model

import com.albertomier.willywonkahelper.data.database.entities.OompaLoompaEntity
import com.albertomier.willywonkahelper.data.model.OompaLoompaModel

data class OompaLoompa(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var gender: String,
    var image: String,
    var profession: String,
    var email: String,
    var age: String,
    var country: String,
    var height: String
)

fun OompaLoompaModel.toDomain() = OompaLoompa(id, firstName, lastName, gender, image, profession, email, age, country, height)
fun OompaLoompaEntity.toDomain() = OompaLoompa(id, firstName, lastName, gender, image, profession, email, age, country, height)