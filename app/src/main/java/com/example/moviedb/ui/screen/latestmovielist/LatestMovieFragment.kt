package com.example.moviedb.ui.screen.latestmovielist

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.SearchedMovie
import com.example.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.example.moviedb.ui.base.BaseListAdapter
import com.example.moviedb.ui.base.BaseLoadMoreRefreshFragment
import com.example.moviedb.ui.screen.moviedetail.MovieDetailFragment
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestMovieFragment :
    BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, LatestMovieViewModel, Movie>(),
    Filterable {
    override val viewModel: LatestMovieViewModel by viewModel()
    var movieList: ArrayList<Movie>? = null
    var filteredList: MutableList<Movie> = java.util.ArrayList()

    override val listAdapter: BaseListAdapter<Movie, out ViewDataBinding> by lazy {
        LatestMovieAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }

    override  val recentSelectedMovieAdapter: BaseListAdapter<SearchedMovie, out ViewDataBinding> by lazy {
        RecentMovieAdapter(
            searchedMovieItemClickListener = { toMovieDetailFromRecent(it) }
        )
    }


    override fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search_view?.addTextChangedListener(getTextWatcher(search_view))
        if (search_recycler_view?.adapter == null) {
            search_recycler_view?.adapter = recentSelectedMovieAdapter
        }
        cancel_image_view.setOnClickListener {
            search_view.text?.clear()
            container_recently_searched.visibility = View.GONE
            listAdapter.submitList(movieList)
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        container?.setBackgroundColor(Color.BLACK)
        viewModel.apply {
            movie.observe(viewLifecycleOwner, Observer {
                movieList = it as ArrayList<Movie>?
                if (it.isNotEmpty()) {
                    search_view.visibility = View.VISIBLE
                    cancel_image_view.visibility = View.VISIBLE

                } else {
                    search_view.visibility = View.GONE
                    cancel_image_view.visibility = View.GONE
                }
            })

            selectedMovie.observe(viewLifecycleOwner, Observer {
                var recentMovieList: MutableList<SearchedMovie> = java.util.ArrayList()
                if (it.isNotEmpty() && movie.value!!.isNotEmpty() && search_view.text!!.isEmpty() ) {
                    container_recently_searched.visibility = View.VISIBLE
                    if (it.size > 5) {
                        for (x in it.size - 1 downTo it.size - 5) {
                            recentMovieList.add(it[x])
                        }
                    } else {
                        for (element in it) {
                            recentMovieList.add(element)
                        }
                    }
                    recentSelectedMovieAdapter.submitList(recentMovieList)
                } else {
                    container_recently_searched.visibility = View.GONE

                }

            })
        }
    }


    private fun toMovieDetail(movie: Movie) {

        val searchedMovie = movie.toSearchedMovie()
        container_recently_searched.visibility = View.GONE

        viewModel.viewModelScope.launch {
            viewModel.movieRepository.insertSearchedMovieLocal(searchedMovie)
        }
        // go to detail page
        val fragment = MovieDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable(KEY_MOVIE_ID, movie)
        fragment.arguments = bundle
        replaceFragment(fragment, "", true, -1)
    }

    private fun toMovieDetailFromRecent(searchedMovie: SearchedMovie) {
        val movie = searchedMovie.toMovie()

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

    private fun getTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                when (editText) {
                    search_view -> if (s.isNotEmpty()) {
                        container_recently_searched.visibility = View.GONE
                        filter.filter(s)
                    } else if (s.isEmpty()) {
                        listAdapter.submitList(movieList)
                    }
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                filteredList.clear()
                filteredList = if (charString.isEmpty()) {
                    movieList!!
                } else {
                    movieList!!.filter {
                        if (charSequence.length == 1) {
                            it.title!!.startsWith(charSequence, 0, true)
                        } else {
                            charString.toLowerCase() in it.title!!.toLowerCase()
                        }
                    } as ArrayList<Movie>

                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                filteredList = filterResults.values as ArrayList<Movie>
                listAdapter.submitList(filteredList)
            }
        }
    }


}