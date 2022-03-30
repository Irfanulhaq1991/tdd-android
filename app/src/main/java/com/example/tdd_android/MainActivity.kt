package com.example.tdd_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity(), Observer<UiState> {

    private val viewModel: MovieListingViewModel by lazy {

        ViewModelProvider(
            this,
            viewModelFactory {
                MovieListingViewModel(
                    MoviesRepository(
                        InMemMoviesService(
                            DummyData.getDummyMoviesList()
                        )
                    )
                )
            }
        )[MovieListingViewModel::class.java]

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.movieListLiveData.observe(this, this)
        viewModel.fetchMoviesList()
    }


    override fun onChanged(t: UiState?) {
        when (t) {
            is UiState.ShowLoading -> {
                progress_circular.show()
                showMessage("Show Progress")
            }
            is UiState.HideLoading -> {
                progress_circular.hide()
                showMessage("hide Progress")
            }
            is UiState.Success -> {
                var names = ""
                (t.data as List<Movie>).forEach {
                    names  += "${it.title} \n"
                }
                tv_names.text = names
            }
            is UiState.OutOfInternetError -> {
                showMessage("No internet Error")
            }
            is UiState.EmptyDataError -> {
                showMessage("No Data Error")
            }
            else ->{
                showMessage("Unknown Error")
            }
        }
    }

    private fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}