package com.mikirinkode.kotakfilm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mikirinkode.kotakfilm.core.domain.model.Catalogue
import com.mikirinkode.kotakfilm.core.domain.usecase.MovieUseCase
import com.mikirinkode.kotakfilm.core.vo.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    fun getTrendingMovies(): LiveData<Resource<List<Catalogue>>> {
        return movieUseCase.getTrendingMovies().asLiveData()
    }

    fun getUpcomingMovies(): LiveData<Resource<List<Catalogue>>> {
        return movieUseCase.getUpcomingMovies().asLiveData()
    }

    fun getTopTvShowList(): LiveData<Resource<List<Catalogue>>> {
        return movieUseCase.getTopTvShowList().asLiveData()
    }

}