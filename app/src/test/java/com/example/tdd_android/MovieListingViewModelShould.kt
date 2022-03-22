package com.example.tdd_android

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
 class MovieListingViewModelShould {

    @RelaxedMockK
   private lateinit var movieRepo:MoviesRepository
   private lateinit var viewModel:MovieListingViewModel

   @BeforeEach
   fun setUp(){
       viewModel = MovieListingViewModel(movieRepo)
   }

    @Test
    fun loadDataFromRepository() {
        viewModel.fetchMoviesList()
        verify { movieRepo.fetchMoviesList() }
    }
}