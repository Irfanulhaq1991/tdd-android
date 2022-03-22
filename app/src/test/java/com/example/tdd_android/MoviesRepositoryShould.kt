package com.example.tdd_android


import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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
    fun returnEmptyDataError() {
        val expected = UiState.EmptyDataError
       every { inMemMoviesService.fetchMovies() }.answers { emptyList() }

        val result = repo.fetchMoviesList()
        assertThat(result).isEqualTo(expected)
    }


    @Test
    fun returnMoviesList() {
        val movieList = DummyData.getDummyMoviesList()
        every { inMemMoviesService.fetchMovies() }.answers { movieList }

        val expected = UiState.Success(movieList)
        val result = repo.fetchMoviesList()

        assertThat(result).isEqualTo(expected)
    }

    @
}