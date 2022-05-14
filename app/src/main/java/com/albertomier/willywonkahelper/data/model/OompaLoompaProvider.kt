package com.albertomier.willywonkahelper.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OompaLoompaProvider @Inject constructor() {
    var oompaLoompas: List<OompaLoompaModel> = emptyList()
}