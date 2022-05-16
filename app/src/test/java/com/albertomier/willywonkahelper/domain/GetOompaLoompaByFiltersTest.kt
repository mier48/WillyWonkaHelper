package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.WillyWonkaHelperApp
import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetOompaLoompaByFiltersTest {

    @RelaxedMockK
    private lateinit var repository: OompaLoompaRepository

    @RelaxedMockK
    private lateinit var app: WillyWonkaHelperApp

    lateinit var getOompaLoompaByFilters: GetOompaLoompaByFilters

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOompaLoompaByFilters = GetOompaLoompaByFilters(repository)
    }

    @Test
    fun `when the user check some filters then return filtered data`() = runBlocking {
        //Given
        val myList = listOf(OompaLoompa(1, "nombre", "", "F", "", "", "", "", "", ""))
        val filterList = listOf("F")
        coEvery { repository.getAllOompaLoompasFromDatabase() } returns myList

        //When
        val result = getOompaLoompaByFilters(filterList)

        //Then
        assert(myList == result)
    }
}