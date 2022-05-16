package com.albertomier.willywonkahelper.domain

import com.albertomier.willywonkahelper.WillyWonkaHelperApp
import com.albertomier.willywonkahelper.data.OompaLoompaRepository
import com.albertomier.willywonkahelper.domain.model.OompaLoompa
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOompaLoompaTest {

    @RelaxedMockK
    private lateinit var repository: OompaLoompaRepository

    lateinit var getOompaLoompa: GetOompaLoompa

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getOompaLoompa = GetOompaLoompa(repository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { repository.getAllOompaLoompasFromApi() } returns emptyList()

        //When
        getOompaLoompa()

        //Then
        coVerify(exactly = 1) { repository.getAllOompaLoompasFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(OompaLoompa(1, "nombre", "", "", "", "", "", "", "", ""))
        coEvery { repository.getAllOompaLoompasFromApi() } returns myList

        //When
        val result = getOompaLoompa()

        //Then
        coVerify(exactly = 1) { repository.clearAll() }
        coVerify(exactly = 1) { repository.addOompaLoompas(any()) }
        coVerify(exactly = 0) { repository.getAllOompaLoompasFromDatabase() }
        assert(myList == result)
    }
}