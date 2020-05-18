package com.example.moviedb.ui.screen.moviedetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.moviedb.R
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentMovieDetailBinding
import com.example.moviedb.ui.base.BaseFragment
import com.example.moviedb.ui.screen.latestmovielist.LatestMovieFragment.Companion.KEY_MOVIE_ID
import com.example.moviedb.utils.setSingleClick
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override val layoutId: Int = R.layout.fragment_movie_detail

    override val viewModel: MovieDetailViewModel by viewModel()
    lateinit var bundle: Bundle
    private val castAdapter = CastAdapter()
    private val crewAdapter = CrewAdapter()
    private val similarMovieAdapter = SimilarMovieAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = requireArguments()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_back?.setSingleClick {
            requireActivity().onBackPressed()
        }
        viewModel.apply {
            val id = bundle.getParcelable<Movie>(KEY_MOVIE_ID)!!.id
            viewModel.movie.value=bundle.getParcelable(KEY_MOVIE_ID)
            loadCastAndCrew(id)
            loadSimilarMovie(id)
        }

        if (recycler_cast?.adapter == null) {
            recycler_cast?.adapter = castAdapter
        }
        if (recycler_crew?.adapter == null) {
            recycler_crew?.adapter = crewAdapter
        }
        if (recycler_similar_movie?.adapter == null) {
            recycler_similar_movie?.adapter = similarMovieAdapter
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.apply {
            cast.observe(viewLifecycleOwner, Observer {
                castAdapter.submitList(it)
            })
            crew.observe(viewLifecycleOwner, Observer {
                crewAdapter.submitList(it)
            })
            similarMovie.observe(viewLifecycleOwner, Observer {
                similarMovieAdapter.submitList(it)
            })
        }
    }

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

}