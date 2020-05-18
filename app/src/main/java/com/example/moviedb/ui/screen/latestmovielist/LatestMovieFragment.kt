package com.example.moviedb.ui.screen.latestmovielist

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Movie
import com.example.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.ui.base.BaseLoadMoreRefreshFragment
import com.example.moviedb.ui.screen.moviedetail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMovieFragment :
    BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, LatestMovieViewModel, Movie>() {

    override val viewModel: LatestMovieViewModel by viewModel()

    override val listAdapter: BaseListAdapter<Movie, out ViewDataBinding> by lazy {
        LatestMovieAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }


    override fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        container?.setBackgroundColor(Color.BLACK)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view?.layoutManager = getLayoutManager()

    }

    private fun toMovieDetail(movie: Movie) {
        // go to detail page
        val fragment = MovieDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable(KEY_MOVIE_ID, movie)
        fragment.arguments = bundle
        replaceFragment(fragment, "", true, -1)
    }

    companion object {
        const val KEY_MOVIE_ID = "movie"
        fun newInstance() = LatestMovieFragment()
    }
}