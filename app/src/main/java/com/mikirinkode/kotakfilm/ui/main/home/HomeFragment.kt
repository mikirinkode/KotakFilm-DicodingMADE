package com.mikirinkode.kotakfilm.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikirinkode.kotakfilm.databinding.FragmentHomeBinding
import com.mikirinkode.kotakfilm.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter = UpcomingMovieAdapter()
    private val tvShowAdapter = UpcomingTvShowAdapter()
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding.rvUpcomingMovies.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieAdapter
            }

            findMovieList()

            binding.rvUpcomingTvShows.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = tvShowAdapter
            }

            findTvShowList()
        }
    }

    private fun findMovieList() {
        binding.apply {
            loadingUpcomingMovie.visibility = View.VISIBLE
            onFailMsg.visibility = View.GONE
            viewModel.getUpcomingMovies()
                .observe(viewLifecycleOwner, Observer { movieList ->
                    if (movieList != null) {
                        when (movieList.status) {
                            Status.LOADING -> {
                                loadingUpcomingMovie.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> {
                                movieList.data?.let { movieAdapter.setData(it) }
                                loadingUpcomingMovie.visibility = View.GONE
                            }
                            Status.ERROR -> {
                                loadingUpcomingMovie.visibility = View.GONE
                                onFailMsg.visibility = View.VISIBLE
                            }
                        }
                    }
                })
        }
    }

    private fun findTvShowList() {
        binding.apply {
            loadingAiringTvShow.visibility = View.VISIBLE
            btnTryAgain.visibility = View.GONE
            onFailMsg.visibility = View.GONE
            viewModel.getAiringTodayTvShows()
                .observe(viewLifecycleOwner, Observer { tvShowList ->
                    if (tvShowList != null) {
                        when (tvShowList.status) {
                            Status.LOADING -> {
                                loadingAiringTvShow.visibility = View.VISIBLE
                            }
                            Status.SUCCESS -> {
                                loadingAiringTvShow.visibility = View.GONE
                                tvShowList.data?.let { tvShowAdapter.setData(it) }
                            }
                            Status.ERROR -> {
                                loadingAiringTvShow.visibility = View.GONE
                                btnTryAgain.visibility = View.VISIBLE
                                onFailMsg.visibility = View.VISIBLE
                            }
                        }
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}