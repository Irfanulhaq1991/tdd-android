package com.example.tdd_android


import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.io.IOException

@ExtendWith(MockKExtension::class)
class MoviesRepositoryShould {

    private lateinit var repo: MoviesRepository

    @RelaxedMockK
    private lateinit var inMemMoviesService: InMemMoviesService

    @BeforeEach
    fun setup() {
        repo = MoviesRepository(inMemMoviesService)
    }

    @Test
    fun returnEmptyDataError() = runTest {
        val expected = UiState.EmptyDataError
        coEvery { inMemMoviesService.fetchMovies() } answers { emptyList() }

        val result = repo.fetchMoviesList()
        assertThat(result).isEqualTo(expected)
    }


    @Test
    fun returnMoviesList() = runTest {

        val movieList = DummyData.getDummyMoviesList()
        coEvery { inMemMoviesService.fetchMovies() } answers { movieList }

        val expected = UiState.Success(movieList)

        val result = repo.fetchMoviesList()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun returnOutOfInternetError()= runTest {
        val expected = UiState.OutOfinternetError
        coEvery { inMemMoviesService.fetchMovies() } throws IOException()

        val actual = repo.fetchMoviesList()
        assertThat(actual).isEqualTo(expected)
    }

}
