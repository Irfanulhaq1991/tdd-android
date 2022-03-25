package com.example.tdd_android

object DummyData {
    fun getDummyJsonMoviesData(): String {
        return """
            {
               "movies":[
                  {
                     "title":"Movie1",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  },
                  {
                     "title":"Movie2",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  },
                  {
                     "title":"Movie3",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  },
                  {
                     "title":"Movie4",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  },
                  {
                     "title":"Movie5",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  },
                  {
                     "title":"Movie6",
                     "desc":"This is newly released",
                     "img":"https://picsum.photos/id/237/200/300"
                  }
               ]
            }
        """.trimIndent()
    }
    fun getDummyMoviesList():MutableList<Movie>{

        return mutableListOf(
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300"),
            Movie("Movie1","This is newly released","https://picsum.photos/id/237/200/300")
        )
    }

}