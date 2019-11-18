package com.example.popmovies_tmbd.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.MutableLiveData
import com.example.popmovies_tmbd.api.FetcherUseCase
import com.example.popmovies_tmbd.api.persistence.MoviesDataSource
import com.example.popmovies_tmbd.api.persistence.model.MovieModel
import com.example.popmovies_tmbd.api.repository.Repository
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

/**
1. Setting up the pre-act state: here you go through the function you are testing and determine the dependencies that will need to be mocked and faked.

2. Act: here you create the object you are testing (remember that means calling the constructor of the objects class)
then supply your mocks where they are needed, either in the constructor of your object or the function under test itself

3. Validate the Post-Act state: after calling your target function you are going to want to validate that you get what you were expecting back or happened
 */

class RepositoryTest {

    //member variables
    private val dataSource = mockk<MoviesDataSource>(relaxed = true)
    private val fetcherUseCase = mockk<FetcherUseCase>(relaxed = true)

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun getAllPopularMovies() {
//        Pre-act State setup
        val mockMoviesList = mockk<List<MovieModel>>()
        val lifecycleRegistry = LifecycleRegistry(mockkClass(LifecycleOwner::class))
        every { mockMoviesList.isNullOrEmpty() } returns false
        val repoMovieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()
        every { dataSource.getAllPopularMovies() } returns repoMovieLiveData

//        Act
        val repository = Repository(dataSource, fetcherUseCase)
        val popularMoviesLiveData = repository.getAllPopularMovies()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        popularMoviesLiveData.observe({ lifecycleRegistry }) {}
        repoMovieLiveData.postValue(mockMoviesList)

//        Post-act State validation
        Assert.assertEquals(mockMoviesList, popularMoviesLiveData.value)
    }

//    Finally... this test look almost identical to the previous case except that we want to trigger the code that will cause fetcherUseCases method to be run.
//    you need to read the code for getAllPopularMovies() line by line..... make a comment on what each line is doing
//    then evaluate what kind of pre-act conditions would be necessary to get it to behave in a different manner.
//    you just need to tweak one thing from your previous test and then learn how to use verify

    @Test
    fun fetchPopularMovies() {
//        Pre-act State setup
        val emptyMovieList = emptyList<MovieModel>()
        val lifecycleRegistry = LifecycleRegistry(mockkClass(LifecycleOwner::class))
        val repoMovieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()
        every { dataSource.getAllPopularMovies() } returns repoMovieLiveData


//        Act
        val repository = Repository(dataSource, fetcherUseCase)
        val popularMoviesLiveData = repository.getAllPopularMovies()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        popularMoviesLiveData.observe({ lifecycleRegistry }) {}
        repoMovieLiveData.postValue(emptyMovieList)


//        Post-act State validation
        verify(atMost = 1){ fetcherUseCase.fetchPopularMovies() }
        assert(repoMovieLiveData.value!!.isEmpty())
    }

}