package com.example.tdd_android


import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.google.common.truth.Truth.assertThat

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExtendWith(InstantTaskExecutorExtension::class)
class MovieListFeatureShould {

    private lateinit var inMemMoviesService: InMemMoviesService
    private lateinit var movieListingVieModel: MovieListingViewModel
    private lateinit var uiController: MovieListSpyUiController
    private lateinit var movieRepo: MoviesRepository


    @BeforeEach
    fun setup() {
        inMemMoviesService = InMemMoviesService(DummyData.getDummyMoviesList())
        movieRepo = MoviesRepository(inMemMoviesService)
        movieListingVieModel = MovieListingViewModel(movieRepo)
        uiController = MovieListSpyUiController().apply { viewModel = movieListingVieModel }
        uiController.onCreate()
    }

    @Test
    fun fetchAndShowList() = runTest{
        val expected = listOf(
            UiState.ShowLoading,
            UiState.Success(DummyData.getDummyMoviesList()),
            UiState.HideLoading
        )
        uiController.fetchMovieList()
        val result =  uiController.states
        assertThat(result).isEqualTo(expected)
    }
}

class MovieListSpyUiController : LifecycleOwner {

    lateinit var viewModel: MovieListingViewModel
    val states = mutableListOf<UiState>()
    private lateinit var registry: LifecycleRegistry
    private val countDownLatch: CountDownLatch = CountDownLatch(1)
    fun onCreate() {
        /*spy configuration*/
        registry = LifecycleRegistry(this)
        registry.currentState = Lifecycle.State.STARTED
        viewModel.movieListLiveData.observe(this,{
                states.add(it)
        })

    }

    override fun getLifecycle(): Lifecycle {
        return registry
    }

    fun fetchMovieList() {
        viewModel.fetchMoviesList()
        countDownLatch.await(100,TimeUnit.MILLISECONDS)
    }
}

