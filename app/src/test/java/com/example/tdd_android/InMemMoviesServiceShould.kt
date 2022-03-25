package com.example.tdd_android

class InMemMoviesServiceShould : ServiceContractTests() {

     override fun getServiceWith(list: MutableList<Movie>):IMovieService{
         return InMemMoviesService(list)
     }

    override fun getOfflineService():IMovieService{
        return InMemMoviesService(null)
    }
}