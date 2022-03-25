package com.example.tdd_android

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

abstract class ServiceContractTests {
    @Test
    fun returnNoMovie() = runTest {
        val inMemService = getServiceWith(DummyData.getDummyMoviesList().also { it.clear() })
        assertThrows<NoDataException> {
            inMemService.fetchMovies()
        }
    }

    @Test
    fun returnListOfMovie() = runTest {
        val expected = DummyData.getDummyMoviesList()
        val inMemService = getServiceWith(expected)
        val result = inMemService.fetchMovies()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun throwOutOfInternetException() = runTest {
        val inMemMoviesService = getOfflineService()
        assertThrows<OutOfInternetException> {
            inMemMoviesService.fetchMovies()
        }
    }

    abstract fun getServiceWith(list: MutableList<Movie>): IMovieService
    abstract fun getOfflineService(): IMovieService
}